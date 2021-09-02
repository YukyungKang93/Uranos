package dao;

import static util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.RegBean;

public class RegDAO {

	DataSource ds;
	Connection con;
	private static RegDAO boardDAO;

	private RegDAO() {
	}

	public static RegDAO getInstance() {
		if (boardDAO == null) {
			boardDAO = new RegDAO();
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
			pstmt = con.prepareStatement("select count(*) from reg_board");
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
	public ArrayList<RegBean> selectArticleList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reg_board order by reg_num desc limit ?,10";
		ArrayList<RegBean> articleList = new ArrayList<RegBean>();
		RegBean board = null;
		int startrow = (page - 1) * 10; // 읽기 시작할 row 번호..

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new RegBean();
				board.setReg_num(rs.getInt("reg_num"));
				board.setCategory(rs.getString("category"));
				board.setP_name(rs.getString("p_name"));
				board.setM_id(rs.getString("m_id"));
				board.setM_name(rs.getString("m_name"));
				board.setImage(rs.getString("image"));
				board.setReg_content(rs.getString("reg_content"));
				board.setReg_date(rs.getDate("reg_date"));
				board.setReg_readcount(rs.getInt("reg_readcount"));
				board.setReg_state(rs.getString("reg_state"));
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
	public int insertArticle(RegBean article) {
		PreparedStatement pstmt = null;
		String sql = "";
		int insertCount = 0;
		try {
			sql = "insert into reg_board(category, p_name, startdate, m_id, m_name, image, reg_content, total_number, reg_date, reg_readcount, reg_state) "
					+ "values (?,?,?,?,?,?,?,?,current_timestamp,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getCategory());
			pstmt.setString(2, article.getP_name());
			pstmt.setString(3, article.getStartdate());
			pstmt.setString(4, article.getM_id());
			pstmt.setString(5, article.getM_name());
			pstmt.setString(6, article.getImage());
			pstmt.setString(7, article.getReg_content());
			pstmt.setInt(8, article.getTotal_number());
			pstmt.setInt(9, 0);
			pstmt.setString(10, "승인요청");
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("신청 실패 : " + e);
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	// 글 내용 보기.
	public RegBean selectArticle(int reg_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RegBean board = null;
		try {
			pstmt = con.prepareStatement("select * from reg_board where reg_num = ?");
			pstmt.setInt(1, reg_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new RegBean();
				board.setReg_num(rs.getInt("reg_num"));
				board.setCategory(rs.getString("category"));
				board.setP_name(rs.getString("p_name"));
				board.setStartdate(rs.getString("startdate"));
				board.setM_id(rs.getString("m_id"));
				board.setM_name(rs.getString("m_name"));
				board.setImage(rs.getString("image"));
				board.setReg_content(rs.getString("reg_content"));
				board.setTotal_number(rs.getInt("total_number"));
				board.setReg_date(rs.getDate("reg_date"));
				board.setReg_readcount(rs.getInt("reg_readcount"));
				board.setReg_state(rs.getString("reg_state"));
			}
		} catch (Exception ex) {
			System.out.println("getDetail 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	}

	// 글 수정.
	public int updateArticle(RegBean article) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update reg_board set category=?,p_name=?,image=?,reg_content=? where reg_num=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getCategory());
			pstmt.setString(2, article.getP_name());
			pstmt.setString(3, article.getImage());
			pstmt.setString(4, article.getReg_content());
			pstmt.setInt(5, article.getReg_num());
			updateCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("boardModify 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	// 승인확인
	public boolean confirmCheckCount(int reg_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String board_sql = "select * from reg_board where reg_num = ? and reg_state = ?";
		boolean checkCount = true;

		try {
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, reg_num);
			pstmt.setString(2, "승인완료");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);

				if (count > 0)
					checkCount = false;
			}

		} catch (SQLException ex) {
			System.out.println("checkCount 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return checkCount;
	}

	// 승인
	public int ConfirmArticle(int reg_num) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update reg_board set reg_state=? where reg_num=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "승인완료");
			pstmt.setInt(2, reg_num);
			updateCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("ConfirmArticle 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	// 글쓴이인지 확인.
	public boolean isArticleBoardWriter(int reg_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql = "select * from reg_board where reg_num=?";
		boolean isWriter = false;

		try {
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, reg_num);
			rs = pstmt.executeQuery();
			rs.next();

		} catch (SQLException ex) {
			System.out.println("isBoardWriter 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return isWriter;
	}

	// 글 삭제.
	public int deleteArticle(int reg_num) {

		PreparedStatement pstmt = null;
		String board_delete_sql = "delete from reg_board where reg_num=?";
		int deleteCount = 0;

		try {
			pstmt = con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, reg_num);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("boardDelete 에러 : " + ex);
		} finally {
			close(pstmt);
		}

		return deleteCount;

	}

	// 프로그램 추가
	public int insertProgram(RegBean article) {
		PreparedStatement pstmt = null;
		String sql = "";
		int insertCount = 0;
		try {
			sql = "insert into program(category, p_name, startdate, count, image, content, total_number, m_id) "
					+ "values (?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getCategory());
			pstmt.setString(2, article.getP_name());
			pstmt.setString(3, article.getStartdate());
			pstmt.setInt(4, article.getTotal_number());
			pstmt.setString(5, article.getImage());
			pstmt.setString(6, article.getReg_content());
			pstmt.setInt(7, article.getTotal_number());
			pstmt.setString(8, article.getM_id());
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("프로그램추가 실패 : " + e);
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	// 관리자 글보기
	public ArrayList<RegBean> selectRegList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reg_board where reg_state= '승인요청' order by reg_num desc limit ?, 5";
		ArrayList<RegBean> registerList = new ArrayList<RegBean>();
		RegBean reg = null;
		int startrow = (page - 1) * 10;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reg = new RegBean();
				reg.setReg_num(rs.getInt("reg_num"));
				reg.setCategory(rs.getString("category"));
				reg.setP_name(rs.getString("p_name"));
				reg.setM_id(rs.getString("m_id"));
				reg.setStartdate(rs.getString("startdate"));
				reg.setReg_date(rs.getDate("reg_date"));
				registerList.add(reg);
			}
		} catch (Exception ex) {
			System.out.println(rs);
			System.out.println(pstmt);
			System.out.println("getreservationList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return registerList;
	}

	// 글의 개수 구하기.
	public int adminListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select count(*) from reg_board");
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

}
