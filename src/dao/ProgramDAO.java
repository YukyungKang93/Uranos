package dao;

import static util.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import util.JdbcUtil;
import vo.P_replyBean;
import vo.ProgramBean;

public class ProgramDAO {

	DataSource ds;
	Connection con;
	private static ProgramDAO programDAO;

	private ProgramDAO() {
	}

	public static ProgramDAO getInstance() {
		if (programDAO == null) {
			programDAO = new ProgramDAO();
		}
		return programDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 전체 프로그램 리스트카운트
	public int selectListCount() {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = con.prepareStatement("select count(*) from program");
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

	// 카테고리 선택된 프로그램들 페이지
	public int selectSelectedListCount(String category) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = con.prepareStatement("select count(*) from program where category = ?");
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getCastleListCount 에러: " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public ArrayList<ProgramBean> selectProgramList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String program_list_sql = "select * from program order by p_num desc limit ?,6";
		ArrayList<ProgramBean> programList = new ArrayList<ProgramBean>();
		ProgramBean program = null;
		int startrow = (page - 1) * 6;

		try {
			pstmt = con.prepareStatement(program_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setTotal_number(rs.getInt("total_number"));
				program.setReadcount(rs.getInt("readcount"));
				program.setM_id(rs.getString("m_id"));
				programList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return programList;

	}

	public ProgramBean selectDetail(int p_num) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProgramBean programBean = null;

		try {
			pstmt = con.prepareStatement("select * from program where p_num = ?");
			pstmt.setInt(1, p_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				programBean = new ProgramBean();
				programBean.setP_num(rs.getInt("p_num"));
				programBean.setCategory(rs.getString("category"));
				programBean.setP_name(rs.getString("p_name"));
				programBean.setStartdate(rs.getString("startdate"));
				programBean.setCount(rs.getInt("count"));
				programBean.setImage(rs.getString("image"));
				programBean.setContent(rs.getString("content"));
				programBean.setTotal_number(rs.getInt("total_number"));
				programBean.setReadcount(rs.getInt("readcount"));
				programBean.setM_id(rs.getString("m_id"));

			}

		} catch (Exception ex) {
			System.out.println("getDetail : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return programBean;

	}

	// 등산리스트
	public ArrayList<ProgramBean> selectHikingList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String hiking_list_sql = "select * from program where category=? order by p_num desc limit ?,6";
		ArrayList<ProgramBean> hikingList = new ArrayList<ProgramBean>();
		ProgramBean program = null;
		int startrow = (page - 1) * 6; // 읽기 시작할 row 번호..

		try {
			pstmt = con.prepareStatement(hiking_list_sql);
			pstmt.setString(1, "등산");
			pstmt.setInt(2, startrow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setContent(rs.getString("total_number"));
				program.setContent(rs.getString("readcount"));	
				program.setM_id(rs.getString("m_id"));
				hikingList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return hikingList;

	}

	// 성곽순례리스트
	public ArrayList<ProgramBean> selectCastleList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String castle_list_sql = "select * from program where category=? order by p_num desc limit ?,6";
		ArrayList<ProgramBean> castleList = new ArrayList<ProgramBean>();
		ProgramBean program = null;
		int startrow = (page - 1) * 6; // 읽기 시작할 row 번호..

		try {
			pstmt = con.prepareStatement(castle_list_sql);
			pstmt.setString(1, "성곽순례");
			pstmt.setInt(2, startrow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setContent(rs.getString("total_number"));
				program.setContent(rs.getString("readcount"));	
				program.setM_id(rs.getString("m_id"));
				castleList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return castleList;

	}

	// 마라톤리스트
	public ArrayList<ProgramBean> selectRiddingList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String ridding_list_sql = "select * from program where category=? order by p_num desc limit ?,6";
		ArrayList<ProgramBean> riddingList = new ArrayList<ProgramBean>();
		ProgramBean program = null;
		int startrow = (page - 1) * 6; // 읽기 시작할 row 번호..

		try {
			pstmt = con.prepareStatement(ridding_list_sql);
			pstmt.setString(1, "러닝/마라톤");
			pstmt.setInt(2, startrow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setContent(rs.getString("total_number"));
				program.setContent(rs.getString("readcount"));	
				program.setM_id(rs.getString("m_id"));
				riddingList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return riddingList;

	}

	// 펫산책리스트
	public ArrayList<ProgramBean> selectPetTourList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String pettour_list_sql = "select * from program where category=? order by p_num desc limit ?,6";
		ArrayList<ProgramBean> petTourList = new ArrayList<ProgramBean>();
		ProgramBean program = null;
		int startrow = (page - 1) * 6; // 읽기 시작할 row 번호..

		try {
			pstmt = con.prepareStatement(pettour_list_sql);
			pstmt.setString(1, "펫산책");
			pstmt.setInt(2, startrow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setContent(rs.getString("total_number"));
				program.setContent(rs.getString("readcount"));				
				program.setM_id(rs.getString("m_id"));
				petTourList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return petTourList;

	}

	// 댓글 카운트
	public int selectReplyListCount(int p_num) {
		int listCount = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select count(*) from p_reply where p_num=?");
			ps.setInt(1, p_num);
			rs = ps.executeQuery();
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러: " + ex);
		} finally {
			close(rs);
			close(ps);
		}

		return listCount;

	}

	// 댓글 리스트
	public ArrayList<P_replyBean> selectReplyList(int p_num, int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from p_reply where p_num=? order by group_num desc,reply_num limit ?,5";
		ArrayList<P_replyBean> replyList = new ArrayList<P_replyBean>();
		P_replyBean reply = null;
		int startrow = (page - 1) * 5;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_num);
			pstmt.setInt(2, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reply = new P_replyBean();
				reply.setP_num(rs.getInt("p_num"));
				reply.setReply_num(rs.getInt("reply_num"));
				reply.setGroup_num(rs.getInt("group_num"));
				reply.setM_id(rs.getString("m_id"));
				reply.setContent(rs.getString("content"));
				reply.setReg_date(rs.getDate("reg_date"));
				reply.setUp_date(rs.getDate("up_date"));

				replyList.add(reply);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return replyList;
	}

	// 댓글 대댓글 작성
	public int insertReplyArticle(P_replyBean reply) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProgramBean program = new ProgramBean();
		String board_max_sql = "SELECT MAX(reply_num) FROM p_reply";
		String sql = "";

		int insertCount = 0;
		int reply_num = 0;
		int group_num = reply.getGroup_num();

		try {

			pstmt = con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				reply_num = rs.getInt(1) + 1;
			else
				reply_num = 1;

			if (group_num == 0) {
				group_num = reply_num;
			}

			sql = "INSERT INTO p_reply(reply_num,group_num, p_num, m_id, content, reg_date)"
					+ "VALUES (?,?,?,?,?,current_timestamp)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reply_num);
			// pstmt.setInt(2, sec_num);
			pstmt.setInt(2, group_num);
			pstmt.setInt(3, reply.getP_num());
			pstmt.setString(4, reply.getM_id());
			pstmt.setString(5, reply.getContent());
			insertCount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("boardReply : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}

	// 댓글 수정
	public int updateArticle(P_replyBean reply) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE p_reply SET content=?,up_Date=now() WHERE reply_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reply.getContent());
			pstmt.setInt(2, reply.getReply_num());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardModify : " + e);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	// 대댓글갯수확인
	public boolean replyCount(int group_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String board_sql = "select count(*) from p_reply where group_num = ?";
		boolean replycount = true;

		try {
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, group_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
				System.out.println("카운트" + count);
				if (count > 1)
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

	// 댓글 삭제
	public int deleteArticle(int reply_num) {

		PreparedStatement pstmt = null;
		String board_delete_sql = "delete from p_reply where reply_num=?";
		int deleteCount = 0;

		try {
			pstmt = con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, reply_num);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("boardDelete 에러 : " + ex);
		} finally {
			close(pstmt);
		}

		return deleteCount;

	}

	// 아이디 일치 확인
	public boolean isArticleBoardWriter(int reply_num, String m_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String board_sql = "SELECT * FROM p_reply WHERE REPLY_NUM=?";
		boolean isWriter = false;

		try {
			ps = con.prepareStatement(board_sql);
			ps.setInt(1, reply_num);
			rs = ps.executeQuery();
			rs.next();
			if (m_id.equals(rs.getString("m_id"))) {
				isWriter = true;
			}
		} catch (Exception e) {
			System.out.println("m_id::::" + m_id);
			System.out.println("isBoardWriter : " + e);
		} finally {
			close(ps);
		}
		return isWriter;
	}

	public P_replyBean selectReply(int reply_num) {
		P_replyBean reply = null;

		try {

			PreparedStatement pstmt = null;
			String sql = "SELECT * FROM p_reply WHERE reply_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reply_num);
			ResultSet rs = null;
			rs = pstmt.executeQuery();

			pstmt.setInt(1, reply_num);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				reply = new P_replyBean();
				reply.setReply_num(rs.getInt("reply_num"));
				// reply.setSec_num(rs.getInt("sec_num"));
				reply.setGroup_num(rs.getInt("group_num"));
				reply.setP_num(rs.getInt("p_num"));
				reply.setM_id(rs.getString("m_id"));
				reply.setContent(rs.getString("content"));
				reply.setReg_date(rs.getDate("reg_date"));
				reply.setUp_date(rs.getDate("up_date"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());

		} finally {
			close(con);
		}
		return reply;
	}

	// 관리자 수정
	public int updateProgram(ProgramBean program) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update program set category = ?, p_name = ?, startdate = ?, count = ?, ";
		sql += "image = ?, content = ?, total_number = ? where p_num = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, program.getCategory());
			pstmt.setString(2, program.getP_name());
			pstmt.setString(3, program.getStartdate());
			pstmt.setInt(4, program.getCount());
			pstmt.setString(5, program.getImage());
			pstmt.setString(6, program.getContent());
			pstmt.setInt(7, program.getTotal_number());
			pstmt.setInt(8, program.getP_num());
			updateCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("programModify 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	// 관리자 삭제
	public int deleteProgram(int p_num) {
		PreparedStatement pstmt = null;
		String program_delete_sql = "delete from program where p_num = ?";
		int deleteCount = 0;
		try {
			pstmt = con.prepareStatement(program_delete_sql);
			pstmt.setInt(1, p_num);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("programDelete: " + ex);
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	// 관리자 카테고리랑 항목체크하고 검색 때 쓰는 리스트카운트용
	public int selectSearchListCount(String category, String item, String searchText) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer like = new StringBuffer();
		String sql = "select count(*) from program where category = ? and " + item + " like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, "%" + searchText + "%");
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
		System.out.println(listCount);
		return listCount;
	}

	// 관리자 카테고리랑 항목체크하고 검색때쓰는 프로그램리스트용
	public ArrayList<ProgramBean> selectProgramSearchList(int page, int limit, String category, String item,
			String searchText) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String program_admin_list_sql = "select * from program where category = ? and " + item
				+ " like ? order by p_num desc limit ?,6";

		ArrayList<ProgramBean> programAdminList = new ArrayList<ProgramBean>();
		ProgramBean program = null;
		int startrow = (page - 1) * 6;

		try {
			pstmt = con.prepareStatement(program_admin_list_sql);
			pstmt.setString(1, category);
			pstmt.setString(2, "%" + searchText + "%");
			pstmt.setInt(3, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setTotal_number(rs.getInt("total_number"));
				program.setM_id(rs.getString("m_id"));
				programAdminList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return programAdminList;
	}

	// 관리자에서 전체 리스트 나오는거
	public ArrayList<ProgramBean> selectProgramAdminList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String program_admin_list_sql = "select * from program order by p_num desc limit ?,6";

		ArrayList<ProgramBean> programAdminList = new ArrayList<ProgramBean>();
		ProgramBean program = null;
		int startrow = (page - 1) * 6;

		try {
			pstmt = con.prepareStatement(program_admin_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setTotal_number(rs.getInt("total_number"));
				program.setM_id(rs.getString("m_id"));
				programAdminList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return programAdminList;

	}

	// category, item, 검색어 다 입력해서 찾는거
	public ArrayList<ProgramBean> selectProgramCategorySearchList(int page, int limit, String category, String item,
			String searchText) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String program_admin_list_sql = "select * from program where category = ? and " + item
				+ " like ? order by p_num desc limit ?,6";

		ArrayList<ProgramBean> programAdminList = new ArrayList<ProgramBean>();
		ProgramBean program = null;
		int startrow = (page - 1) * 6;

		try {
			pstmt = con.prepareStatement(program_admin_list_sql);
			pstmt.setString(1, category);
			pstmt.setString(2, "%" + searchText + "%");
			pstmt.setInt(3, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setTotal_number(rs.getInt("total_number"));
				program.setM_id(rs.getString("m_id"));
				programAdminList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return programAdminList;
	}

	// category, item, 검색어 다 입력해서 찾는거 카운트
	public int selectCategorySearchListCount(String category, String item, String searchText) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer like = new StringBuffer();
		String sql = "select count(*) from program where category = ? and " + item + " like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, "%" + searchText + "%");
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
		System.out.println(listCount);
		return listCount;
	}

	// 카테고리에서 체크안하고 item만 선택후 검색 카운트
	public int selectCategoryAllSearchListCount(String item, String searchText) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from program where " + item + " like ? or " + item + " like ? or " + item
				+ " like ? or " + item + " like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchText + "%");
			pstmt.setString(2, "%" + searchText + "%");
			pstmt.setString(3, "%" + searchText + "%");
			pstmt.setString(4, "%" + searchText + "%");
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
		System.out.println(listCount);
		return listCount;
	}

	// 카테고리에서 체크안하고 item만 선택후 검색리스트
	public ArrayList<ProgramBean> selectCategoryAllSearchList(int page, int limit, String item, String searchText) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String program_admin_listSearch_sql = "select * from program where " + item + " like ? or " + item
				+ " like ? or " + item + " like ? or " + item + " like ?";
		program_admin_listSearch_sql += " order by p_num desc limit ?,6";
		ArrayList<ProgramBean> programAdminList = new ArrayList<ProgramBean>();
		ProgramBean program = null;
		int startrow = (page - 1) * 6;

		try {
			pstmt = con.prepareStatement(program_admin_listSearch_sql);
			pstmt.setString(1, "%" + searchText + "%");
			pstmt.setString(2, "%" + searchText + "%");
			pstmt.setString(3, "%" + searchText + "%");
			pstmt.setString(4, "%" + searchText + "%");
			pstmt.setInt(5, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setTotal_number(rs.getInt("total_number"));
				program.setM_id(rs.getString("m_id"));
				programAdminList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return programAdminList;
	}

	// 카테고리만 선택하고 검색어 입력카운트
	public int selectCategorySearchListCount(String category) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from program where category = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);

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

	// 카테고리만 선택하고 검색어 입력
	public ArrayList<ProgramBean> selectCategorySearchList(int page, int limit, String category) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String program_admin_listSearch_sql = "select * from program where category = ? order by p_num desc limit ?,6";
		ArrayList<ProgramBean> programAdminList = new ArrayList<ProgramBean>();
		ProgramBean program = null;
		int startrow = (page - 1) * 6;

		try {
			pstmt = con.prepareStatement(program_admin_listSearch_sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setTotal_number(rs.getInt("total_number"));
				program.setM_id(rs.getString("m_id"));
				programAdminList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return programAdminList;
	}

	// 바로 검색어만 입력하고 검색버튼 클릭
	public int selectProgramSearchListCount(String searchText) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from program where p_name like ? or category like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchText + "%");
			pstmt.setString(2, "%" + searchText + "%");

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

	// 바로 검색어만 입력하고 검색버튼 클릭
	public ArrayList<ProgramBean> selectProgramAdminList(int page, int limit, String searchText) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String program_admin_listSearch_sql = "select * from program where p_name like ? or category like ? order by p_num desc limit ?,6";
		ArrayList<ProgramBean> programAdminList = new ArrayList<ProgramBean>();
		ProgramBean program = null;
		int startrow = (page - 1) * 6;

		try {
			pstmt = con.prepareStatement(program_admin_listSearch_sql);
			pstmt.setString(1, "%" + searchText + "%");
			pstmt.setString(2, "%" + searchText + "%");
			pstmt.setInt(3, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setTotal_number(rs.getInt("total_number"));
				program.setM_id(rs.getString("m_id"));
				programAdminList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return programAdminList;
	}

	public ArrayList<ProgramBean> IndexSelectProgramList() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String program_list_sql = "select * from program order by readcount desc limit 5";
		ArrayList<ProgramBean> programList = new ArrayList<ProgramBean>();
		ProgramBean program = null;

		try {
			pstmt = con.prepareStatement(program_list_sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setTotal_number(rs.getInt("total_number"));
				program.setReadcount(rs.getInt("readcount"));
				program.setM_id(rs.getString("m_id"));
				programList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return programList;

	}

	public int updateReadCount(int p_num) {

		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update program set readcount = " + "readcount + 1 where p_num = " + p_num;

		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("setReadCountUpdate 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	// 인덱스에서 최신프로그램용
	public ArrayList<ProgramBean> selectLatestProgramList() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String program_list_sql = "select * from program order by p_num desc limit 5";
		ArrayList<ProgramBean> latestProgramList = new ArrayList<ProgramBean>();
		ProgramBean program = null;

		try {
			pstmt = con.prepareStatement(program_list_sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				program = new ProgramBean();
				program.setP_num(rs.getInt("p_num"));
				program.setCategory(rs.getString("category"));
				program.setP_name(rs.getString("p_name"));
				program.setStartdate(rs.getString("startdate"));
				program.setCount(rs.getInt("count"));
				program.setImage(rs.getString("image"));
				program.setContent(rs.getString("content"));
				program.setTotal_number(rs.getInt("total_number"));
				program.setReadcount(rs.getInt("readcount"));
				program.setM_id(rs.getString("m_id"));
				latestProgramList.add(program);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return latestProgramList;

	}// 인덱스에서 최신프로그램용

	public int selectLatestProgramListCount() {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = con.prepareStatement("select count(*) from program ");
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
