package dao;

import static util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.MemberBean;
import vo.ProgramBean;
import vo.Reservation;

public class ReservationDAO {
	DataSource ds;
	Connection con;
	ProgramBean programBean;
	MemberBean member;

	private static ReservationDAO reservationDAO;

	public static ReservationDAO getInstance() {
		if (reservationDAO == null) {
			reservationDAO = new ReservationDAO();
		}
		return reservationDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// member테이블에서 bean에 id,name 저장
	public MemberBean selectById(MemberBean member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select * from member " + " where m_id = ?");
			pstmt.setString(1, member.getM_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberBean();
				member.setM_ID(rs.getString("m_id"));
				member.setM_NAME(rs.getString("m_name"));
			}
		} catch (Exception ex) {
			System.out.println("selectById : " + ex);

		} finally {
			close(rs);
			close(pstmt);
		}
		return member;
	}

	public ProgramBean selectByProgramNum(int p_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select * from program where p_num = ?");
			pstmt.setInt(1, p_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				programBean = new ProgramBean();
				programBean.setP_num(rs.getInt("p_num"));
				programBean.setP_name(rs.getString("p_name"));
				programBean.setStartdate(rs.getString("startdate"));

			}
		} catch (Exception ex) {
			System.out.println("selectByProgramNum : " + ex);

		} finally {
			close(rs);
			close(pstmt);
		}
		return programBean;
	}

	// reservation 테이블에 insert
	public int Reservation(MemberBean member, ProgramBean programBean) {
		PreparedStatement pstmt = null;
		int insertCount = 0;

		String sql;

		try {
			sql = "insert into reservation (m_id, m_name, p_num, p_name, startdate, resdate)"
					+ " values(?, ?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, (String) member.getM_ID());
			pstmt.setString(2, (String) member.getM_NAME());
			pstmt.setInt(3, programBean.getP_num());
			pstmt.setString(4, (String) programBean.getP_name());
			pstmt.setString(5, (String) programBean.getStartdate());
			insertCount = pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("Reservation : " + ex);
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	// program count컬럼 숫자 변경. (참가가능인원)
	public void updateCount(int p_num) {
		PreparedStatement pstmt = null;

		String sql = "update program set count = count-1 where p_num = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_num);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("countUpdate : " + ex);
		} finally {
			close(pstmt);
		}
	}

	// 관리자 글보기
	public ArrayList<Reservation> selectReservationList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reservation order by res_num desc limit ?, 5";
		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
		Reservation reservation = null;
		int startrow = (page - 1) * 10;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservation = new Reservation();
				reservation.setRes_num(rs.getInt("res_num"));
				reservation.setP_name(rs.getString("p_name"));
				reservation.setM_id(rs.getString("m_id"));
				reservation.setStartdate(rs.getDate("startdate"));
				reservation.setResdate(rs.getDate("resdate"));
				reservationList.add(reservation);
			}
		} catch (Exception ex) {
			System.out.println("rs:::" + rs);
			System.out.println("pstmt:::" + pstmt);
			System.out.println("getreservationList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return reservationList;
	}

	// 글의 개수 구하기.
	public int adminListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select count(*) from reservation");
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

	// 관리자에서 전체예약현황 리스트카운트 검색버튼바로
	public int selectAdminReservationListCount() {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = con.prepareStatement("select count(*) from reservation");
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

	// 관리자에서 전체예약현황리스트 검색버튼 바로
	public ArrayList<Reservation> selectAdminReservationList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reservation order by res_num desc limit ?,6";
		ArrayList<Reservation> reservationAllList = new ArrayList<Reservation>();
		Reservation reservation = null;
		int startrow = (page - 1) * 6;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservation = new Reservation();
				reservation.setP_num(rs.getInt("res_num"));
				reservation.setM_id(rs.getString("m_id"));
				reservation.setM_name(rs.getString("m_name"));
				reservation.setP_num(rs.getInt("p_num"));
				reservation.setP_name(rs.getString("p_name"));
				reservation.setStartdate(rs.getDate("startdate"));
				reservation.setResdate(rs.getDate("resdate"));
				reservationAllList.add(reservation);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return reservationAllList;

	}

	// 바로 검색어만 입력하고 검색버튼 클릭 카운트
	public int selectReservationSearchListCount(String searchText) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from reservation where p_name like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchText + "%");

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

	// 바로 검색어만 입력하고 검색버튼 클릭 리스트
	public ArrayList<Reservation> selectReservationAdminList(int page, int limit, String searchText) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reservation where p_name like ?  order by p_name desc limit ?,6";
		ArrayList<Reservation> reservationSearchList = new ArrayList<Reservation>();
		Reservation reservation = null;
		int startrow = (page - 1) * 6;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchText + "%");
			pstmt.setInt(2, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservation = new Reservation();
				reservation.setM_id(rs.getString("m_id"));
				reservation.setM_name(rs.getString("m_name"));
				reservation.setP_num(rs.getInt("p_num"));
				reservation.setP_name(rs.getString("p_name"));
				reservation.setStartdate(rs.getDate("startdate"));
				reservation.setResdate(rs.getDate("resdate"));
				reservationSearchList.add(reservation);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return reservationSearchList;
	}

	// item 선택후 검색 카운트
	public int selectItemSearchListCount(String item, String searchText) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from reservation where " + item + " like ? or " + item + " like ? or " + item
				+ " like ? or " + item + " like ? or " + item + " like ? or " + item + " like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchText + "%");
			pstmt.setString(2, "%" + searchText + "%");
			pstmt.setString(3, "%" + searchText + "%");
			pstmt.setString(4, "%" + searchText + "%");
			pstmt.setString(5, "%" + searchText + "%");
			pstmt.setString(6, "%" + searchText + "%");
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

	// item 선택후 검색리스트
	public ArrayList<Reservation> selectItemSearchList(int page, int limit, String item, String searchText) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reservation where " + item + " like ? or " + item + " like ? or " + item
				+ " like ? or " + item + " like ? or " + item + " like ? or " + item + " like ?";
		sql += " order by " + item + " desc limit ?,6";
		ArrayList<Reservation> reservationSearchList = new ArrayList<Reservation>();
		Reservation reservation = null;
		int startrow = (page - 1) * 6;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchText + "%");
			pstmt.setString(2, "%" + searchText + "%");
			pstmt.setString(3, "%" + searchText + "%");
			pstmt.setString(4, "%" + searchText + "%");
			pstmt.setString(5, "%" + searchText + "%");
			pstmt.setString(6, "%" + searchText + "%");
			pstmt.setInt(7, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservation = new Reservation();
				reservation.setM_id(rs.getString("m_id"));
				reservation.setM_name(rs.getString("m_name"));
				reservation.setP_num(rs.getInt("p_num"));
				reservation.setP_name(rs.getString("p_name"));
				reservation.setStartdate(rs.getDate("startdate"));
				reservation.setResdate(rs.getDate("resdate"));
				reservationSearchList.add(reservation);
			}

		} catch (Exception ex) {
			System.out.println("getProgramList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return reservationSearchList;
	}

	// 중복신청 못하게 막는거
	public Reservation checkReservationList(String user_id, int p_num) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reservation where m_id = ? and p_num = ?";
		Reservation checkReservation = new Reservation();

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, p_num);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				checkReservation = new Reservation();
				checkReservation.setM_id(rs.getString("m_id"));
				checkReservation.setM_name(rs.getString("m_name"));
				checkReservation.setP_num(rs.getInt("p_num"));
				checkReservation.setP_name(rs.getString("p_name"));
				checkReservation.setStartdate(rs.getDate("startdate"));
				checkReservation.setResdate(rs.getDate("resdate"));
			}

		} catch (Exception ex) {
			System.out.println("checkReservation 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return checkReservation;

	}

}