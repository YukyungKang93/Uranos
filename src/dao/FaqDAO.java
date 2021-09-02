package dao;

import static util.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.Faq;
import vo.Notice;

public class FaqDAO {
	DataSource ds;
	Connection con;

	private FaqDAO() {
	}

	private static FaqDAO boardDAO;

	public static FaqDAO getInstance() {
		if (boardDAO == null) {
			boardDAO = new FaqDAO();
		}
		return boardDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			System.out.println("getConnection");
			pstmt = con.prepareStatement("select count(*) from faq");
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

	public ArrayList<Faq> selectFaqList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String faq_list_sql = "select * from faq " + " order by f_num desc limit ?, 10";
		ArrayList<Faq> articleList = new ArrayList<Faq>();
		int startrow = (page - 1) * 10; //읽기 시작할 row 번호..
		Faq faq = null;
		try {
			pstmt = con.prepareStatement(faq_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				faq = new Faq();
				faq.setF_num(rs.getInt("f_num"));
				faq.setF_title(rs.getString("f_title"));
				faq.setF_content(rs.getString("f_content"));
				faq.setF_image(rs.getString("f_image"));
				faq.setF_readcount(rs.getInt("f_readcount"));
				faq.setF_writer(rs.getString("f_writer"));
				articleList.add(faq);
			}
		} catch (Exception ex) {
			System.out.println("getBoardList : " + ex);

		} finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	
	public int updateReadCount(int f_num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;

		String sql = "update faq set f_readcount = f_readcount + 1" + " where f_num = " + f_num;
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

	public Faq selectArticle(int f_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Faq faq = null;
		try {
			pstmt = con.prepareStatement("select * from faq " + " where f_num = ?");
			pstmt.setInt(1, f_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				faq = new Faq();
				faq.setF_num(rs.getInt("f_num"));
				faq.setF_title(rs.getString("f_title"));
				faq.setF_content(rs.getString("f_content"));
				faq.setF_image(rs.getString("f_image"));
				faq.setF_readcount(rs.getInt("f_readcount"));
				faq.setF_writer(rs.getString("f_writer"));
			}
		} catch (Exception ex) {
			System.out.println("getBoardList : " + ex);

		} finally {
			close(rs);
			close(pstmt);
		}
		return faq;
	}
	//관리자에서 faq삭제
	public int deleteFaq(int f_num) {
		PreparedStatement pstmt = null;
		String faq_delete_sql = "delete from faq where f_num = ?";
		int deleteCount = 0;
		try {
			pstmt = con.prepareStatement(faq_delete_sql);

			pstmt.setInt(1, f_num);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("faqDelete: " + ex);
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}
	
	
	//관리자에서 faq수정
	public int updateFaq(Faq faq) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		String sql = "update faq set f_title = ?, f_content = ?, f_image = ? " + "where f_num = ?";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, faq.getF_title());
			pstmt.setString(2, faq.getF_content());
			pstmt.setString(3, faq.getF_image());
			pstmt.setInt(4, faq.getF_num());
			updateCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("FaqModify 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	//관리자 글작성
	public int insertFaq(Faq faq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into faq (f_title, f_content, f_image) values(?, ?, ?)";
		int insertCount = 0;
		try {
			pstmt = con.prepareStatement(sql);		
			pstmt.setString(1, faq.getF_title());
			pstmt.setString(2, faq.getF_content());
			pstmt.setString(3, faq.getF_image());
			insertCount = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("FaqInsert : " + ex);
		}finally {
			close(rs); close(pstmt);
		}
		return insertCount;
	}
	//인덱스에 Faq보여주는거 
	public ArrayList<Faq> selectIndexFaqList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String index_faq_list_sql = "select * from faq " + " order by f_num desc limit 5";
		ArrayList<Faq> indexFaqList = new ArrayList<Faq>();

		Faq faq = null;
		try {
			pstmt = con.prepareStatement(index_faq_list_sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				faq = new Faq();
				faq.setF_num(rs.getInt("f_num"));
				faq.setF_title(rs.getString("f_title"));
				faq.setF_content(rs.getString("f_content"));
				faq.setF_image(rs.getString("f_image"));
				faq.setF_readcount(rs.getInt("f_readcount"));
				faq.setF_writer(rs.getString("f_writer"));
				indexFaqList.add(faq);
			}
		} catch (Exception ex) {
			System.out.println("getFaqList : " + ex);

		} finally {
			close(rs);
			close(pstmt);
		}
		return indexFaqList;
	}
	
}
