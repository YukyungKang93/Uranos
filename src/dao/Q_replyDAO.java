package dao;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import vo.Q_replyBean;
import vo.QnaBean;

public class Q_replyDAO {

	DataSource ds;
	Connection con;
	private static Q_replyDAO boardDAO;

	private Q_replyDAO() {
	}

	public static Q_replyDAO getInstance() {
		if (boardDAO == null) {
			boardDAO = new Q_replyDAO();
		}
		return boardDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

//	 글 내용 보기.
	public Q_replyBean selectArticle(int qna_num) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Q_replyBean board = null;
		try {
			pstmt = con.prepareStatement("select * from qna_reply " 
					+ "inner join qna_board "
					+ "on qna_reply.qna_num = qna_board.qna_num " 
					+ "where qna_reply.qna_num = ?");
			pstmt.setInt(1, qna_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new Q_replyBean();
				board.setQna_num(rs.getInt("qna_num"));
				board.setM_id(rs.getString("m_id"));
				board.setContent(rs.getString("content"));
				board.setReg_date(rs.getDate("reg_date"));
			}
		} catch (Exception ex) {
			System.out.println("getBoardList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	}

	// 글 수정.
	public int updateArticle(QnaBean article) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update qna_board set title=?,qna_content=? where qna_num=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getQna_content());
			pstmt.setInt(3, article.getQna_num());
			updateCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("boardModify 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	// 글쓴이인지 확인.
	public boolean isArticleBoardWriter(int qna_num, String pass) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql = "select * from qna_board where qna_num=?";
		boolean isWriter = false;

		try {
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, qna_num);
			rs = pstmt.executeQuery();
			rs.next();

			if (pass.equals(rs.getString("m_pw"))) {
				isWriter = true;
			}
		} catch (SQLException ex) {
			System.out.println("isBoardWriter 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return isWriter;
	}

	// 글 삭제.
	public int deleteArticle(int qna_num) {

		PreparedStatement pstmt = null;
		String board_delete_sql = "delete from qna_board where qna_num=?";
		int deleteCount = 0;

		try {
			pstmt = con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, qna_num);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("boardDelete 에러 : " + ex);
		} finally {
			close(pstmt);
		}

		return deleteCount;

	}

	// 글 답변.
	public int insertReplyArticle(Q_replyBean article) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_max_sql = "select qna_num from qna_board where qna_num = ?";
		String sql = "";
		int num = 0;
		int insertCount = 0;

		try {
			pstmt = con.prepareStatement(board_max_sql);
			pstmt.setInt(1, article.getQna_num());
			rs = pstmt.executeQuery();
			if (rs.next())
				num = rs.getInt(1);
			else
				num = 1;
			sql = "insert into qna_reply(qna_num, m_id, content, reg_date) " + "values (?,?,?,current_timestamp)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getM_id());
			pstmt.setString(3, article.getContent());
			int updateCount = pstmt.executeUpdate();

			if (updateCount > 0) {
				commit(con);
			}
		} catch (SQLException ex) {
			System.out.println("boardReply 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}

	public List<Q_replyBean> selectList(Connection con, int firstRow, int endRow, int qna_num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select * from qna_reply "
					+ "inner join qna_board "
					+ "on qna_reply.qna_num = qna_board.qna_num "
					+ "where qna_reply.qna_num = ?");
			pstmt.setInt(1, qna_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				List<Q_replyBean> messageList = new ArrayList<Q_replyBean>();
				do {
					messageList.add(makeMessageFromResultSet(rs));
				} while (rs.next());

				return messageList;
			} else {
				return Collections.emptyList();
			}
		} finally {
			close(rs);
			close(pstmt);
		}
	}

	private Q_replyBean makeMessageFromResultSet(ResultSet rs) throws SQLException {
		Q_replyBean reply = new Q_replyBean();
		reply.setQna_num(rs.getInt("qna_num"));
		reply.setM_id(rs.getString("m_id"));
		reply.setContent(rs.getString("content"));
		reply.setReg_date(rs.getDate("reg_date"));
		return reply;
	}

	public int selectCount(Connection con) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select count(*) from qna_reply");
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);

		} finally {
			close(rs);
			close(pstmt);
		}
	}
}
