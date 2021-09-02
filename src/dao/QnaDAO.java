package dao;

import static util.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.QnaBean;

public class QnaDAO {

	DataSource ds;
	Connection con;
	private static QnaDAO boardDAO;

	private QnaDAO() {
	}

	public static QnaDAO getInstance() {
		if (boardDAO == null) {
			boardDAO = new QnaDAO();
		}
		return boardDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 글의 개수 구하기.
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select count(*) from qna_board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러: " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	// 글 목록 보기.
	public ArrayList<QnaBean> selectArticleList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qna_board order by qna_num desc limit ?,10";
		ArrayList<QnaBean> articleList = new ArrayList<QnaBean>();
		QnaBean board = null;
		int startrow = (page - 1) * 10; // 읽기 시작할 row 번호..

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new QnaBean();
				board.setQna_num(rs.getInt("qna_num"));
				board.setTitle(rs.getString("title"));
				board.setM_id(rs.getString("m_id"));
				board.setM_pw(rs.getString("m_pw"));
				board.setQna_content(rs.getString("qna_content"));
				board.setQna_readcount(rs.getInt("qna_readcount"));
				board.setQna_date(rs.getDate("qna_date"));
				articleList.add(board);
			}
		} catch (Exception ex) {
			System.out.println("getBoardList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}

	// 글 추가
	public int insertArticle(QnaBean article) {
		PreparedStatement pstmt = null;
		String sql = "";
		int insertCount = 0;
		try {

			sql = "insert into qna_board(title, m_id, m_pw,";
			sql += "qna_content, qna_readcount, qna_date) " + "values (?,?,?,?,?,current_timestamp)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getM_id());
			pstmt.setString(3, article.getM_pw());
			pstmt.setString(4, article.getQna_content());
			pstmt.setInt(5, article.getQna_readcount());

			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert 실패 : " + e);
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	// 글 내용 보기.
	public QnaBean selectArticle(int qna_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnaBean board = null;
		try {
			pstmt = con.prepareStatement("select * from qna_board where qna_num = ?");
			pstmt.setInt(1, qna_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new QnaBean();
				board.setQna_num(rs.getInt("qna_num"));
				board.setTitle(rs.getString("title"));
				board.setM_id(rs.getString("m_id"));
				board.setM_pw(rs.getString("m_pw"));
				board.setQna_content(rs.getString("qna_content"));
				board.setQna_readcount(rs.getInt("qna_readcount"));
				board.setQna_date(rs.getDate("qna_date"));
			}
		} catch (Exception ex) {
			System.out.println("getDetail 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	}

	// 조회수 업데이트.
	public int updateReadCount(int qna_num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		try {
			String sql = "update qna_board set qna_readcount = qna_readcount +1 where qna_num = " + qna_num;
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setReadCountUpdate 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	// 글 수정.
	public int updateArticle(QnaBean article) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update qna_board set title=?, m_id=?, qna_content=? where qna_num=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getM_id());
			pstmt.setString(3, article.getQna_content());
			pstmt.setInt(4, article.getQna_num());
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

	// 댓글확인
	public boolean replyCount(int qna_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String board_sql = "select qna_num from qna_reply where qna_num = ?";
		boolean replycount = true;

		try {
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, qna_num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
				
				if (count > 0)
					replycount = false;
			}

		} catch (SQLException ex) {
			System.out.println("replycount 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return replycount;
	}

	// 글 삭제.
	public int deleteArticle(int qna_num) {

		PreparedStatement pstmt = null;
		int deleteCount = 0;

		try {

			String sql = "delete from qna_board where qna_num=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("boardDelete 에러 : " + ex);
		} finally {
			close(pstmt);
		}

		return deleteCount;

	}

}
