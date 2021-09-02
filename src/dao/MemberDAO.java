package dao;

import static util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import util.JdbcUtil;
import vo.MemberBean;
import vo.MemberListBean;

public class MemberDAO {
	DataSource ds;
	Connection con = JdbcUtil.getConnection();

	private static MemberDAO uranosDAO;

	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		if (uranosDAO == null) {
			uranosDAO = new MemberDAO();
		}
		return uranosDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<String> dataArrest(MemberBean memberbean) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<String> data = new ArrayList<String>();
		try {
			sql = "select m_name, m_id, addr, email, phone, gender, birth , regdate from member where"
					+ "(m_name like ? or m_id like ? or addr like ? or email like ? or "
					+ "phone like ? or gender like ? or birth like ? or regdate like ?) order by regdate desc";
			pstmt = con.prepareStatement(sql);
			// pstmt.setString(1, memberBen.get_search());
			pstmt.setString(1, "1");
			rs = pstmt.executeQuery();
			con.commit();
			boolean a = rs.next();
			int i = 0;
			while (rs.next()) {

				data.add(rs.getString("m_name"));
				i++;

			}
			memberbean.setCount(i);

		} catch (SQLException ex) {
			System.out.println("dataArrest 에러 : " + ex);

		} finally {
			try {
				if (con != null) {
					con.close();
					con = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return data;
	}

	public MemberBean memberInfoDetail(String id) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean bean = null;

		try {
			bean = new MemberBean();

			String sql = "";

			sql = "select m_name, m_id, addr, detail_addr, ref_addr, email, phone, gender, birth from member where m_id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			con.commit();

			if (rs.next()) {
				bean.setM_NAME(rs.getString("m_name"));
				bean.setM_ID(rs.getString("m_id"));
				bean.setADDR(rs.getString("addr"));
				bean.setDETAIL_ADDR(rs.getString("detail_addr"));
				bean.setREF_ADDR(rs.getString("ref_addr"));
				bean.setEMAIL(rs.getString("email"));
				bean.setPHONE(rs.getString("phone"));
				bean.setGENDER(rs.getString("gender"));
				bean.setBIRTH(rs.getString("birth"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("memberInfoDetail 메서드에서 에러" + se);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return bean;
	}

	public MemberBean searchAllData(MemberBean memberBen) {
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;

		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		ResultSet rs5 = null;

		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		ArrayList<MemberListBean> data = new ArrayList<MemberListBean>();

		System.out.println("test3");
		String value = "%" + memberBen.get_value() + "%";

		try {

			sql1 = "select m_name, m_id, addr, detail_addr, ref_addr, email, phone, gender, birth , regdate from member where"
					+ "(m_name like ? or m_id like ? or addr like ? or detail_addr like ? or ref_addr like ? or email like ? or "
					+ "phone like ? or gender like ? or birth like ? or regdate like ?) order by "
					+ memberBen.getOrder_By() + " " + memberBen.getArrange();
			pstmt1 = con.prepareStatement(sql1);
			// pstmt.setString(1, memberBen.get_search());
			pstmt1.setString(1, value);
			pstmt1.setString(2, value);
			pstmt1.setString(3, value);
			pstmt1.setString(4, value);
			pstmt1.setString(5, value);
			pstmt1.setString(6, value);
			pstmt1.setString(7, value);
			pstmt1.setString(8, value);
			pstmt1.setString(9, value);
			pstmt1.setString(10, value);
			rs1 = pstmt1.executeQuery();

			con.commit();

			int i = 0;
			while (rs1.next()) {
				i++;
			}
			memberBen.setCount(i);

			int page = (memberBen.getCurrent_page() - 1) * 10;
			int show_page = 10; // 한페이지에 보여줄 검색결과개수
			int resultcount = 0;
			if (memberBen.getCount() > show_page) { // 검색결과가 10개보다 많을 때
				if ((memberBen.getCount() - page) < show_page) { // 보여줄 결과가 10개보다 적을 때
					resultcount = memberBen.getCount() - page;
				} else { // 보여줄 결과가 10개보다 많을 때
					resultcount = show_page;
				}
			} else { // 검색결과가 10개보다 적을 때
				resultcount = memberBen.getCount();
			}

			sql2 = "select m_name, m_id, addr, detail_addr, ref_addr, email, phone, gender, birth , regdate from member where"
					+ "(m_name like ? or m_id like ? or addr like ? or detail_addr like ? or ref_addr like ? or email like ? or "
					+ "phone like ? or gender like ? or birth like ? or regdate like ?) order by "
					+ memberBen.getOrder_By() + " " + memberBen.getArrange() + " limit " + page + ", " + resultcount;
			pstmt2 = con.prepareStatement(sql2);
			// pstmt.setString(1, memberBen.get_search());
			pstmt2.setString(1, value);
			pstmt2.setString(2, value);
			pstmt2.setString(3, value);
			pstmt2.setString(4, value);
			pstmt2.setString(5, value);
			pstmt2.setString(6, value);
			pstmt2.setString(7, value);
			pstmt2.setString(8, value);
			pstmt2.setString(9, value);
			pstmt2.setString(10, value);
			rs2 = pstmt2.executeQuery();

			int showcount = 0;
			while (rs2.next()) {
				MemberListBean listBean = new MemberListBean();
				listBean.setM_ID(rs2.getString("m_id"));
				listBean.setM_NAME(rs2.getString("m_name"));
				listBean.setADDR(rs2.getString("addr"));
				listBean.setADDR(rs2.getString("addr"));
				listBean.setDETAIL_ADDR(rs2.getString("detail_addr"));
				listBean.setREF_ADDR(rs2.getString("ref_addr"));
				listBean.setEMAIL(rs2.getString("email"));
				listBean.setPHONE(rs2.getString("phone"));
				listBean.setGENDER(rs2.getString("gender"));
				listBean.setBIRTH(rs2.getString("birth"));
				listBean.setREGDATE(rs2.getString("regdate"));

				data.add(listBean);

				showcount++;
			}

			memberBen.setData(data);
			memberBen.setShow_count(showcount);

			ArrayList<String> myRevData = new ArrayList<String>();
			ArrayList<String> myRepData = new ArrayList<String>();
			ArrayList<String> myProData = new ArrayList<String>();

			for (int l = 0; l < data.size(); l++) { // 검색된 아이디만큼 반복
				System.out.println(data.get(l).getM_ID());
				String searchId = data.get(l).getM_ID();

				sql3 = "select reservation.p_name, reservation.startdate, program.image, reservation.p_num from reservation "
						+ "LEFT JOIN program ON reservation.p_num = program.p_num where program.m_id=? order by res_num";
				pstmt3 = con.prepareStatement(sql3);
				pstmt3.setString(1, searchId);
				rs3 = pstmt3.executeQuery();
				con.commit();

				while (rs3.next()) {
					myRevData.add(rs3.getString("p_name"));
					myRevData.add(rs3.getString("startdate"));
					myRevData.add(rs3.getString("image"));
					myRevData.add(rs3.getString("p_num"));
				}

				sql4 = "select program.p_name, p_reply.content, p_reply.reg_date, p_reply.up_date, p_reply.p_num"
						+ " from p_reply LEFT JOIN program ON p_reply.p_num = program.p_num where program.m_id= ? order by up_date";
				pstmt4 = con.prepareStatement(sql4);
				pstmt4.setString(1, searchId);
				rs4 = pstmt4.executeQuery();
				con.commit();
				while (rs4.next()) {
					myRepData.add(rs4.getString("p_name"));
					myRepData.add(rs4.getString("content"));
					myRepData.add(rs4.getString("reg_date"));
					myRepData.add(rs4.getString("up_date"));
					myRepData.add(rs4.getString("p_num"));
				}

				sql5 = "select category, p_name, reg_date, reg_state from reg_board where m_id = ?";
				pstmt5 = con.prepareStatement(sql5);
				pstmt5.setString(1, searchId);
				rs5 = pstmt5.executeQuery();
				con.commit();

				while (rs5.next()) {
					myProData.add(rs5.getString("category"));
					myProData.add(rs5.getString("p_name"));
					myProData.add(rs5.getString("reg_date"));
					myProData.add(rs5.getString("reg_state"));
				}

			}
			memberBen.setRevData(myRevData);
			memberBen.setRepData(myRepData);
			memberBen.setProData(myProData);

		} catch (SQLException ex) {
			System.out.println("searchAllData 에러 : " + ex);

		} finally {
			try {
				if (rs5 != null) {
					rs5.close();
				}
				if (rs4 != null) {
					rs4.close();
				}
				if (rs3 != null) {
					rs3.close();
				}
				if (rs2 != null) {
					rs2.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
				if (pstmt5 != null) {
					pstmt5.close();
				}
				if (pstmt4 != null) {
					pstmt4.close();
				}
				if (pstmt3 != null) {
					pstmt3.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return memberBen;
	}

	public MemberBean searchPartData(MemberBean memberBen) {

		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		ResultSet rs5 = null;

		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		ArrayList<MemberListBean> data = new ArrayList<MemberListBean>();
		System.out.println("test3");

		String Select = memberBen.get_search();
		String Value = "%" + memberBen.get_value() + "%";

		try {
			System.out.println(Select + "=" + Value);

			sql1 = "select m_name, m_id, addr, detail_addr, ref_addr, email, phone, gender, birth, regdate from member where "
					+ Select + " like ? order by " + memberBen.getOrder_By() + " " + memberBen.getArrange();
			pstmt1 = con.prepareStatement(sql1);
			// pstmt.setString(1, memberBen.get_search());
			pstmt1.setString(1, Value);
			rs1 = pstmt1.executeQuery();
			con.commit();

			int i = 0;

			while (rs1.next()) {

				i++;

			}

			memberBen.setCount(i);

			int page = (memberBen.getCurrent_page() - 1) * 10;
			int show_page = 10; // 한페이지에 보여줄 검색결과개수
			int resultcount = 0;
			if (memberBen.getCount() > show_page) { // 검색결과가 10개보다 많을 때
				if ((memberBen.getCount() - page) < show_page) { // 보여줄 결과가 10개보다 적을 때
					resultcount = memberBen.getCount() - page;
				} else { // 보여줄 결과가 10개보다 많을 때
					resultcount = show_page;
				}
			} else { // 검색결과가 10개보다 적을 때
				resultcount = memberBen.getCount();
			}

			sql2 = "select m_name, m_id, addr, detail_addr, ref_addr, email, phone, gender, birth, regdate from member where "
					+ Select + " like ? order by " + memberBen.getOrder_By() + " " + memberBen.getArrange() + " limit "
					+ page + ", " + resultcount;
			pstmt2 = con.prepareStatement(sql2);
			// pstmt.setString(1, memberBen.get_search());
			pstmt2.setString(1, Value);
			rs2 = pstmt2.executeQuery();
			con.commit();

			int showcount = 0;
			while (rs2.next()) {
				MemberListBean listBean = new MemberListBean();
				listBean.setM_ID(rs2.getString("m_id"));
				listBean.setM_NAME(rs2.getString("m_name"));
				listBean.setADDR(rs2.getString("addr"));
				listBean.setDETAIL_ADDR(rs2.getString("detail_addr"));
				listBean.setREF_ADDR(rs2.getString("ref_addr"));
				listBean.setEMAIL(rs2.getString("email"));
				listBean.setPHONE(rs2.getString("phone"));
				listBean.setGENDER(rs2.getString("gender"));
				listBean.setBIRTH(rs2.getString("birth"));
				listBean.setREGDATE(rs2.getString("regdate"));

				data.add(listBean);

				showcount++;
			}

			memberBen.setData(data);
			memberBen.setShow_count(showcount);

			ArrayList<String> myRevData = new ArrayList<String>();
			ArrayList<String> myRepData = new ArrayList<String>();
			ArrayList<String> myProData = new ArrayList<String>();

			for (int l = 0; l < data.size(); l++) { // 검색된 아이디만큼 반복
				System.out.println(data.get(l).getM_ID());
				String searchId = data.get(l).getM_ID();

				sql3 = "select reservation.p_name, reservation.startdate, program.image, reservation.p_num from reservation "
						+ "LEFT JOIN program ON reservation.p_num = program.p_num where program.m_id=? order by res_num";
				pstmt3 = con.prepareStatement(sql3);
				pstmt3.setString(1, searchId);
				rs3 = pstmt3.executeQuery();
				con.commit();

				while (rs3.next()) {
					myRevData.add(rs3.getString("p_name"));
					myRevData.add(rs3.getString("startdate"));
					myRevData.add(rs3.getString("image"));
					myRevData.add(rs3.getString("p_num"));
				}

				sql4 = "select program.p_name, p_reply.content, p_reply.reg_date, p_reply.up_date, p_reply.p_num"
						+ " from p_reply LEFT JOIN program ON p_reply.p_num = program.p_num where program.m_id= ? order by up_date";
				pstmt4 = con.prepareStatement(sql4);
				pstmt4.setString(1, searchId);
				rs4 = pstmt4.executeQuery();
				con.commit();
				while (rs4.next()) {
					myRepData.add(rs4.getString("p_name"));
					myRepData.add(rs4.getString("content"));
					myRepData.add(rs4.getString("reg_date"));
					myRepData.add(rs4.getString("up_date"));
					myRepData.add(rs4.getString("p_num"));
				}

				sql5 = "select category, p_name, reg_date, reg_state from reg_board where m_id = ?";
				pstmt5 = con.prepareStatement(sql5);
				pstmt5.setString(1, searchId);
				rs5 = pstmt5.executeQuery();
				con.commit();

				while (rs5.next()) {
					myProData.add(rs5.getString("category"));
					myProData.add(rs5.getString("p_name"));
					myProData.add(rs5.getString("reg_date"));
					myProData.add(rs5.getString("reg_state"));
				}

			}
			memberBen.setRevData(myRevData);
			memberBen.setRepData(myRepData);
			memberBen.setProData(myProData);

		} catch (SQLException ex) {
			System.out.println("searchPartData 에러 : " + ex);

		} finally {
			try {
				if (rs5 != null) {
					rs5.close();
				}
				if (rs4 != null) {
					rs4.close();
				}
				if (rs3 != null) {
					rs3.close();
				}
				if (rs2 != null) {
					rs2.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
				if (pstmt5 != null) {
					pstmt5.close();
				}
				if (pstmt4 != null) {
					pstmt4.close();
				}
				if (pstmt3 != null) {
					pstmt3.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return memberBen;
	}

	public int joinIdCheck(String id) {
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement("select * from member where m_id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// 4. 데이터처리

			if (rs.next()) {
				result = 0;
			} else {
				result = 1;
			}

			System.out.println("아이디 중복체크결과 : " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return result;
	}

	public int joinEmailCheck(String email1, String email2, String id) {
		int result = -1;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		String sumEmail = email1 + "@" + email2;
		try {
			con = JdbcUtil.getConnection();
			pstmt1 = con.prepareStatement("select * from member where email=?");
			pstmt1.setString(1, sumEmail);
			rs1 = pstmt1.executeQuery();

			// 4. 데이터처리

			if (rs1.next()) {
				result = 0;
			} else {
				result = 1;
			}

			if (!id.equals("admin")) {
				PreparedStatement pstmt2 = null;
				ResultSet rs2 = null;
				try {

					pstmt2 = con.prepareStatement("select email from member where m_id=?");
					pstmt2.setString(1, id);
					rs2 = pstmt2.executeQuery();

					if (rs2.next()) {
						String searchEmail = rs2.getString("email");
						if (searchEmail.equals(sumEmail)) {
							result = 2;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close(rs2);
					close(pstmt2);
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs1);
			close(pstmt1);
			close(con);
		}
		return result;
	}

	public String login(MemberBean memberBean) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String check = null;

		try {

			String password = null;

			con = JdbcUtil.getConnection();
			String sql = "";

			sql = "select m_name from member where m_id=? and m_pw=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getM_ID());
			pstmt.setString(2, memberBean.getM_PW());
			rs = pstmt.executeQuery();
			con.commit();

			if (rs.next()) {
				check = rs.getString("M_NAME");
				return check;
			}

		} catch (SQLException ex) {
			System.out.println("login 에러 : " + ex);

		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		check = "로그인실패";
		return check;
	}

	public boolean id_check(MemberBean boardBean) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = true;

		try {
			con = JdbcUtil.getConnection();
			String sql = "";

			sql = "select m_id from member where m_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardBean.getM_ID());

			rs = pstmt.executeQuery();
			con.commit();

			if (rs.next()) {
				check = false;
			}

		} catch (SQLException ex) {
			System.out.println("id_check 에러 : " + ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return check;
	}

	public String email_id_search(MemberBean boardBean) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			System.out.println(boardBean.getM_NAME());
			System.out.println(boardBean.getEMAIL());
			sql = "select m_id from member where m_name=? and email=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardBean.getM_NAME());
			pstmt.setString(2, boardBean.getEMAIL());

			rs = pstmt.executeQuery();
			con.commit();
			while (rs.next()) {
				id = rs.getString("m_id");
			}

		} catch (SQLException ex) {
			System.out.println("ID_SEARCH 에러 : " + ex);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return id;
	}

	public String phone_id_search(MemberBean boardBean) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			System.out.println("아이디찾기 테스트3");
			System.out.println(boardBean.getM_NAME());
			System.out.println(boardBean.getPHONE());

			sql = "select m_id from member where m_name=? and phone=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardBean.getM_NAME());
			pstmt.setString(2, boardBean.getPHONE());

			rs = pstmt.executeQuery();
			con.commit();
			while (rs.next()) {
				id = rs.getString("m_id");
			}

		} catch (SQLException ex) {
			System.out.println("ID_SEARCH 에러 : " + ex);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return id;
	}

	public static String getRamdomPassword(int len)

	{
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		int idx = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			idx = (int) (charSet.length * Math.random());
			sb.append(charSet[idx]);
		}
		String random_pass = sb.toString();
		return random_pass;
	}

	public String pass_search(MemberBean memberBean) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rd_pass = getRamdomPassword(6);

		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			sql = "update member set m_pw = ? where m_name=? and m_id=? and email=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rd_pass);
			pstmt.setString(2, memberBean.getM_NAME());
			pstmt.setString(3, memberBean.getM_ID());
			pstmt.setString(4, memberBean.getEMAIL());

			rs = pstmt.executeQuery();
			con.commit();

		} catch (SQLException ex) {
			System.out.println("pass_search 에러 : " + ex);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return rd_pass;
	}

	public int insertMember(MemberBean memberBean) {

		int InsertMember = 0;
		PreparedStatement pstmt = null;

		try {

			con = JdbcUtil.getConnection();
			String sql = "";
			System.out.println("확인1");

			sql = "insert into member values (?,?,?,?,?,?,?,?,?,?,now())";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getM_ID());
			pstmt.setString(2, memberBean.getM_PW());
			pstmt.setString(3, memberBean.getM_NAME());
			pstmt.setString(4, memberBean.getADDR());
			pstmt.setString(5, memberBean.getDETAIL_ADDR());
			pstmt.setString(6, memberBean.getREF_ADDR());
			pstmt.setString(7, memberBean.getEMAIL());
			pstmt.setString(8, memberBean.getPHONE());
			pstmt.setString(9, memberBean.getGENDER());
			pstmt.setString(10, memberBean.getBIRTH());

			pstmt.executeUpdate();
			con.commit();
			System.out.println("확인2");

		} catch (SQLException ex) {
			System.out.println("memberInsert 에러 : " + ex);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return InsertMember;
	}

	public String deletemember(MemberBean boardBean) {

		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		String Deletemember = null;

		try {

			con = JdbcUtil.getConnection();
			String sql1 = "";
			String sql2 = "";
			String sql3 = "";
			String sql4 = "";
			String sql5 = "";
			String sql6 = "";
			String sql7 = "";
			System.out.println("회원탈퇴1");
			System.out.println(boardBean.getM_ID());

			sql1 = "delete from member where M_ID=?";
			sql2 = "update reservation set m_name = '탈퇴한 회원' where m_id = ?";
			sql3 = "update reservation set m_id = '탈퇴한 회원' where m_id = ?";
			sql4 = "update reg_board set m_id = '탈퇴한 회원' where m_id = ?";
			sql5 = "update review_board set m_id = '탈퇴한 회원' where m_id = ?";
			sql6 = "update qna_board set m_pw = '탈퇴한 회원' where m_id = ?";
			sql7 = "update qna_board set m_id = '탈퇴한 회원' where m_id = ?";

			System.out.println("회원탈퇴2");

			pstmt1 = con.prepareStatement(sql1);
			pstmt2 = con.prepareStatement(sql2);
			pstmt3 = con.prepareStatement(sql3);
			pstmt4 = con.prepareStatement(sql4);
			pstmt5 = con.prepareStatement(sql5);
			pstmt6 = con.prepareStatement(sql6);
			pstmt7 = con.prepareStatement(sql7);

			pstmt1.setString(1, boardBean.getM_ID());
			pstmt2.setString(1, boardBean.getM_ID());
			pstmt3.setString(1, boardBean.getM_ID());
			pstmt4.setString(1, boardBean.getM_ID());
			pstmt5.setString(1, boardBean.getM_ID());
			pstmt6.setString(1, boardBean.getM_ID());
			pstmt7.setString(1, boardBean.getM_ID());

			pstmt1.executeUpdate();
			pstmt2.executeUpdate();
			pstmt3.executeUpdate();
			pstmt4.executeUpdate();
			pstmt5.executeUpdate();
			pstmt6.executeUpdate();
			pstmt7.executeUpdate();

			con.commit();

		} catch (SQLException ex) {
			System.out.println("delete 에러 : " + ex);

		} finally {
			try {
				if (pstmt1 != null || pstmt2 != null || pstmt3 != null || pstmt4 != null || pstmt5 != null
						|| pstmt6 != null || pstmt7 != null) {
					pstmt1.close();
					pstmt2.close();
					pstmt3.close();
					pstmt4.close();
					pstmt5.close();
					pstmt6.close();
					pstmt7.close();
					pstmt1 = null;
					pstmt2 = null;
					pstmt3 = null;
					pstmt4 = null;
					pstmt5 = null;
					pstmt6 = null;
					pstmt7 = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		Deletemember = "성공적으로 탈퇴되었습니다.";
		System.out.println(Deletemember);
		return Deletemember;
	}

	public String deleteClient(String idCheck, MemberBean memberBean) {

		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		PreparedStatement pstmt8 = null;
		ResultSet rs = null;
		String updateCount = "탈퇴성공";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql6 = "";
		String sql7 = "";
		String sql8 = "";
		int i = 0;

		try {
			sql1 = "select m_id from member where m_pw = ? and m_id='admin'";
			pstmt1 = con.prepareStatement(sql1);

			pstmt1.setString(1, memberBean.getM_PW());
			rs = pstmt1.executeQuery();
			System.out.println("값이 나오나???");
			while (rs.next()) {

				i++;
			}

			System.out.println("i값은?? : " + i);

			if (i == 1) {
				System.out.println("회원탈퇴");

				sql2 = "delete from member where M_ID=?";
				sql3 = "update reservation set m_name = '탈퇴한 회원' where m_id = ?";
				sql4 = "update reservation set m_id = '탈퇴한 회원' where m_id = ?";
				sql5 = "update reg_board set m_id = '탈퇴한 회원' where m_id = ?";
				sql6 = "update review_board set m_id = '탈퇴한 회원' where m_id = ?";
				sql7 = "update qna_board set m_pw = '탈퇴한 회원' where m_id = ?";
				sql8 = "update qna_board set m_id = '탈퇴한 회원' where m_id = ?";

				pstmt2 = con.prepareStatement(sql2);
				pstmt3 = con.prepareStatement(sql3);
				pstmt4 = con.prepareStatement(sql4);
				pstmt5 = con.prepareStatement(sql5);
				pstmt6 = con.prepareStatement(sql6);
				pstmt7 = con.prepareStatement(sql7);
				pstmt8 = con.prepareStatement(sql8);

				pstmt2.setString(1, idCheck);
				pstmt3.setString(1, idCheck);
				pstmt4.setString(1, idCheck);
				pstmt5.setString(1, idCheck);
				pstmt6.setString(1, idCheck);
				pstmt7.setString(1, idCheck);
				pstmt8.setString(1, idCheck);

				pstmt2.executeUpdate();
				pstmt3.executeUpdate();
				pstmt4.executeUpdate();
				pstmt5.executeUpdate();
				pstmt6.executeUpdate();
				pstmt7.executeUpdate();
				pstmt8.executeUpdate();

				con.commit();
			} else {
				updateCount = "실패";
			}

		} catch (Exception ex) {
			System.out.println("회원 정보 수정 중 DB연결 실패 에러 : " + ex);

		} finally {
			close(rs);
			close(pstmt8);
			close(pstmt7);
			close(pstmt6);
			close(pstmt5);
			close(pstmt4);
			close(pstmt3);
			close(pstmt2);
			close(pstmt1);
			close(con);
		}

		return updateCount;
	}

	public String updateClient(String idCheck, MemberBean memberBean) {

		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		String updateCount = "수정성공";
		String sql1 = "";
		
		int i = 0;

		try {
			sql1 = "select m_id from member where m_pw =? and m_id='admin'";
			pstmt1 = con.prepareStatement(sql1);

			pstmt1.setString(1, memberBean.getM_PW());
			rs = pstmt1.executeQuery();
			System.out.println("값이 나오나???");
			while (rs.next()) {

				i++;
			}

			System.out.println("i값은?? : " + i);

			if (i == 1) {
				PreparedStatement pstmt2 = null;
				String sql2 = "";
				sql2 = "update member set m_name=?, addr=?, detail_addr=?, ref_addr=?, email=?, phone=?, gender=?, birth=?"
						+ "where m_id=?";
				pstmt2 = con.prepareStatement(sql2);

				System.out.println("sql문 체크");
				System.out.println("전화번호 : " + memberBean.getPHONE());
				System.out.println("ID : " + memberBean.getM_ID());

				pstmt2.setString(1, memberBean.getM_NAME());
				pstmt2.setString(2, memberBean.getADDR());
				pstmt2.setString(3, memberBean.getDETAIL_ADDR());
				pstmt2.setString(4, memberBean.getREF_ADDR());
				pstmt2.setString(5, memberBean.getEMAIL());
				pstmt2.setString(6, memberBean.getPHONE());
				pstmt2.setString(7, memberBean.getGENDER());
				pstmt2.setString(8, memberBean.getBIRTH());
				pstmt2.setString(9, memberBean.getM_ID());
				pstmt2.executeQuery();
				con.commit();
				close(pstmt2);
				System.out.println("참고사항 : " + memberBean.getREF_ADDR());
				System.out.println("상세주소 : " + memberBean.getDETAIL_ADDR());
			} else {
				updateCount = "실패";
			}

		} catch (Exception ex) {
			System.out.println("회원 정보 수정 중 DB연결 실패 에러 : " + ex);

		} finally {
			close(rs);
			close(pstmt1);
			close(con);
		}

		return updateCount;
	}

	public int updateMember(String idCheck, MemberBean memberBean) {
		PreparedStatement pstmt = null;
		int updateCount = 0;

		try {
			con = JdbcUtil.getConnection();
			String sql = "update member set m_name=?, addr=?, detail_addr=?, ref_addr=?, email=?, phone=?, gender=?, birth=? where m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getM_NAME());
			pstmt.setString(2, memberBean.getADDR());
			pstmt.setString(3, memberBean.getDETAIL_ADDR());
			pstmt.setString(4, memberBean.getREF_ADDR());
			pstmt.setString(5, memberBean.getEMAIL());
			pstmt.setString(6, memberBean.getPHONE());
			pstmt.setString(7, memberBean.getGENDER());
			pstmt.setString(8, memberBean.getBIRTH());
			pstmt.setString(9, idCheck);
			pstmt.executeUpdate();
			con.commit();

			updateCount = 1;
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("회원 정보 수정 중 DB연결 실패 에러 : " + se);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		return updateCount;
	}

	public int updatePw(String id, String nPw) {

		int updateCount = 0;
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement("update member set m_pw = ? where m_id = ?");

			pstmt.setString(1, nPw);
			pstmt.setString(2, id);

			pstmt.executeUpdate();

			updateCount = 1;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}

		return updateCount;
	}

	public String selectById(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean memberBean = new MemberBean();
		String idCk = null;
		try {
			pstmt = con.prepareStatement("select * from member where m_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberBean.setM_ID(rs.getString("m_id"));
			}
			idCk = memberBean.getM_ID();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}
		return idCk;

	}

	public ArrayList<String> myRevSelectById(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<String> myRevData = new ArrayList<String>();
		try {
			sql = "select reservation.p_name, reservation.startdate, program.image, reservation.p_num from reservation LEFT JOIN program ON reservation.p_num = program.p_num where reservation.m_id=? order by res_num";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			con.commit();

			while (rs.next()) {
				myRevData.add(rs.getString("p_name"));
				myRevData.add(rs.getString("startdate"));
				myRevData.add(rs.getString("image"));
				myRevData.add(rs.getString("p_num"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("myRevSelectById 에러 : " + se);
		} finally {
			try {
				if (con != null) {
					con.close();
					con = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return myRevData;
	}

	public ArrayList<String> myRepSelectById(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<String> myRepData = new ArrayList<String>();
		System.out.println("에러 검사 : " + id);
		try {
			sql = "select program.p_name, p_reply.content, p_reply.reg_date, p_reply.up_date, p_reply.p_num from p_reply LEFT "
					+ "JOIN program ON p_reply.p_num = program.p_num where p_reply.m_id= ? order by up_date";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			con.commit();
			while (rs.next()) {
				myRepData.add(rs.getString("p_name"));
				myRepData.add(rs.getString("content"));
				myRepData.add(rs.getString("reg_date"));
				myRepData.add(rs.getString("up_date"));
				myRepData.add(rs.getString("p_num"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("myRepSelectById 에러 : " + se);
		} finally {
			try {
				if (con != null) {
					con.close();
					con = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		System.out.println(myRepData);
		return myRepData;
	}

	public ArrayList<String> myProSelectById(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<String> myProData = new ArrayList<String>();

		try {
			sql = "select category, p_name, reg_date, reg_state from reg_board where m_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			con.commit();

			while (rs.next()) {
				myProData.add(rs.getString("category"));
				myProData.add(rs.getString("p_name"));
				myProData.add(rs.getString("reg_date"));
				myProData.add(rs.getString("reg_state"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("myProSelectById 에러 : " + se);
		} finally {
			try {
				if (con != null) {
					con.close();
					con = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return myProData;
	}

	public int myRevDel(String id, String p_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int value = 1;

		try {
			con = JdbcUtil.getConnection();
			sql = "delete from reservation where m_id=? and p_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, p_num);
			rs = pstmt.executeQuery();
			con.commit();

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("myRepSelectById 에러 : " + se);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return value;
	}

	public int proCountUp(String p_num) {
		PreparedStatement pstmt = null;
		String sql = "";
		int value2 = 2;

		try {
			con = JdbcUtil.getConnection();
			sql = "UPDATE program SET count=count+1 WHERE p_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p_num);
			pstmt.executeQuery();
			con.commit();

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("proCountUp 에러 : " + se);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return value2;
	}

	public boolean deleteProtectRev(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean reRevCheck = false;

//	      System.out.println(id + " : 다오에서 id체크");

		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement("select * from reservation where m_id= ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			con.commit();

			if (rs.next()) {
				reRevCheck = true;
//	            System.out.println("deleteProtectRev 다오에서 reRevCheck = true 체크1 : " + reRevCheck);
			} else {
				reRevCheck = false;
//	            System.out.println("deleteProtectRev 다오에서 reRevCheck = false 체크2 : " + reRevCheck);
			}

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("deleteProtectRev 에러 : " + se);
		} finally {
			try {
				if (con != null) {
					con.close();
					con = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return reRevCheck;
	}
}