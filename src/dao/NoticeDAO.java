package dao;

import static util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.Faq;
import vo.Notice;

public class NoticeDAO {
	DataSource ds;
	Connection con;

	private NoticeDAO() {
	}

	private static NoticeDAO boardDAO;

	public static NoticeDAO getInstance() {
		if (boardDAO == null) {
			boardDAO = new NoticeDAO();
		}
		return boardDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 공지게시판 페이지 숫자
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			System.out.println("getConnection");
			pstmt = con.prepareStatement("select count(*) from notice");
			rs = pstmt.executeQuery();
			if (rs.next())
				listCount = rs.getInt(1);
		} catch (Exception ex) {
			System.out.println("getListCount : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	// 공지게시판 페이지
	public ArrayList<Notice> selectNoticeList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String notice_list_sql = "select * from notice " + " order by n_num desc limit ?, 10";
		ArrayList<Notice> articleList = new ArrayList<Notice>();
		int startrow = (page - 1) * 10; // 읽기 시작할 row 번호..
		Notice notice = null;
		try {
			pstmt = con.prepareStatement(notice_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				notice = new Notice();
				notice.setN_num(rs.getInt("n_num"));
				notice.setN_title(rs.getString("n_title"));
				notice.setN_content(rs.getString("n_content"));
				notice.setN_readcount(rs.getInt("n_readcount"));
				notice.setN_writer(rs.getString("n_writer"));
				articleList.add(notice);
			}
		} catch (Exception ex) {
			System.out.println("getBoardList : " + ex);

		} finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}

	// 공지게시판 조회수
	public int updateReadCount(int n_num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;

		String sql = "update notice set n_readcount = n_readcount + 1" + " where n_num = " + n_num;
		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("setReadCountUpdate: " + ex);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	public Notice selectArticle(int n_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = null;
		try {
			pstmt = con.prepareStatement("select * from notice " + " where n_num = ?");
			pstmt.setInt(1, n_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				notice = new Notice();
				notice.setN_num(rs.getInt("n_num"));
				notice.setN_title(rs.getString("n_title"));
				notice.setN_content(rs.getString("n_content"));
				notice.setN_readcount(rs.getInt("n_readcount"));
				notice.setN_writer(rs.getString("n_writer"));
			}
		} catch (Exception ex) {
			System.out.println("getBoardList : " + ex);

		} finally {
			close(rs);
			close(pstmt);
		}
		return notice;
	}

	// 관리자에서 notice삭제
	public int deleteNotice(int n_num) {
		PreparedStatement pstmt = null;
		String notice_delete_sql = "delete from notice where n_num = ?";
		int deleteCount = 0;
		try {
			pstmt = con.prepareStatement(notice_delete_sql);

			pstmt.setInt(1, n_num);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("NoticeDelete: " + ex);
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	// 관리자에서 notice수정
	public int updateNotice(Notice notice) {
		int updateCount = 0;
		PreparedStatement pstmt = null;

		String sql = "update notice set n_title = ?, n_content = ? " + "where n_num = ?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, notice.getN_title());
			pstmt.setString(2, notice.getN_content());
			pstmt.setInt(3, notice.getN_num());
			updateCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("NoticeModify 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	// 관리자 글작성
	public int insertNotice(Notice notice) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into notice (n_title, n_content) values(?, ?)";
		int insertCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getN_title());
			pstmt.setString(2, notice.getN_content());
			insertCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("NoticeInsert : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}

	public ArrayList<Notice> selectIndexNoticeList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String index_notice_list_sql = "select * from notice " + " order by n_num desc limit 5";
		ArrayList<Notice> indexNoticeList = new ArrayList<Notice>();

		Notice notice = null;
		try {
			pstmt = con.prepareStatement(index_notice_list_sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				notice = new Notice();
				notice.setN_num(rs.getInt("n_num"));
				notice.setN_title(rs.getString("n_title"));
				notice.setN_content(rs.getString("n_content"));
				notice.setN_readcount(rs.getInt("n_readcount"));
				notice.setN_writer(rs.getString("n_writer"));
				indexNoticeList.add(notice);
			}
		} catch (Exception ex) {
			System.out.println("getBoardList : " + ex);

		} finally {
			close(rs);
			close(pstmt);
		}
		return indexNoticeList;
	}

}
