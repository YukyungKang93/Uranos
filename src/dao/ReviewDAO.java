package dao;

import static util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.ReviewBean;

public class ReviewDAO {
	DataSource ds;
	Connection conn;
	private static ReviewDAO reviewDAO;
	
	private ReviewDAO() {
	}
	
	public static ReviewDAO getInstance() {
		if(reviewDAO == null) {
			reviewDAO = new ReviewDAO();
		}
		return reviewDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println("get Connection");
			pstmt = conn.prepareStatement("select count(*) from review_board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("getListCount 에러: " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<ReviewBean> selectReviewList(int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String Review_list_sql = "select * from review_board order by rev_num desc limit 9 offset ?";
		ArrayList<ReviewBean> reviewList  = new ArrayList<ReviewBean>();
		ReviewBean review = null;
		int startRow = (page-1)*9;
		
		try {
			pstmt = conn.prepareStatement(Review_list_sql);
			pstmt.setInt(1, startRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				review = new ReviewBean();
				review.setRev_num(rs.getInt("rev_num"));
				review.setP_name(rs.getString("p_name"));
				review.setTitle(rs.getString("title"));
				review.setM_id(rs.getString("m_id"));
				review.setImage(rs.getString("image"));
				review.setRev_content(rs.getString("rev_content"));
				review.setRev_readcount(rs.getInt("rev_readcount"));
				review.setRev_date(rs.getDate("rev_date"));
				review.setScore(rs.getString("score"));
				reviewList.add(review);
			}
		}catch (Exception ex) {
			System.out.println("getReviewList 에러: " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return reviewList;
	}
	
	public ReviewBean selectReview(int Rev_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewBean reviewBean = null;
		
		try {
			pstmt = conn.prepareStatement("select * from review_board where rev_num = ?");
			pstmt.setInt(1, Rev_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reviewBean = new ReviewBean();
				reviewBean.setRev_num(rs.getInt("rev_num"));
				reviewBean.setP_name(rs.getString("p_name"));
				reviewBean.setTitle(rs.getString("title"));
				reviewBean.setM_id(rs.getString("m_id"));
				reviewBean.setImage(rs.getString("image"));
				reviewBean.setRev_content(rs.getString("rev_content"));
				reviewBean.setRev_readcount(rs.getInt("rev_readcount"));
				reviewBean.setRev_date(rs.getDate("rev_date"));
				reviewBean.setScore(rs.getString("score"));
			}
		}catch(Exception ex) {
			System.out.println("getDetail 에러: " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return reviewBean;
	}
	
	public int insertReview(ReviewBean review) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		String img="";
		int insertCount=0;
		
		try {
			pstmt = conn.prepareStatement("select max(rev_num) from review_board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1)+1;
			}else {
				num = 1;
			}
			
			sql = "insert into review_board(P_name, Title, M_id, image, rev_content, rev_readcount, score, rev_date)"
					+ " values(?,?,?,?,?,?,?,current_timestamp)";
			
			pstmt = conn.prepareStatement(sql);
			
			if(review.getImage()==null) {
				img = "logo.png";
			} else {
				img = review.getImage();
			}
			
			pstmt.setString(1, review.getP_name());
			pstmt.setString(2, review.getTitle());
			pstmt.setString(3, review.getM_id());
			pstmt.setString(4, img);
			pstmt.setString(5, review.getRev_content());
			pstmt.setInt(6, 0);
			pstmt.setString(7, review.getScore());
			
			insertCount = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("ReviewInsert 에러: " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	public int updateReview(ReviewBean review) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update review_board set P_name = ? , Score = ? , Title = ? , image = ? , rev_content = ? where rev_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getP_name());
			pstmt.setString(2, review.getScore());
			pstmt.setString(3, review.getTitle());
			pstmt.setString(4, review.getImage());
			pstmt.setString(5, review.getRev_content());
			pstmt.setInt(6, review.getRev_num());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("reviewModify 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public int adminUpdateReview(ReviewBean reviewAdmin) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update review_board set P_name = ? , Score = ?,  M_id = ?, Title = ? , Image = ? , Rev_content = ? where Rev_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reviewAdmin.getP_name());
			pstmt.setString(2, reviewAdmin.getScore());
			pstmt.setString(3, reviewAdmin.getM_id());
			pstmt.setString(4, reviewAdmin.getTitle());
			pstmt.setString(5, reviewAdmin.getImage());
			pstmt.setString(6, reviewAdmin.getRev_content());
			pstmt.setInt(7, reviewAdmin.getRev_num());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("AdminReviewModify 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	
	public int deleteReview(int rev_num) {
		
		PreparedStatement pstmt = null;
		String review_delete_sql = "delete from review_board where rev_num=?";
		int deleteCount = 0;
		
		try {
			pstmt = conn.prepareStatement(review_delete_sql);
			pstmt.setInt(1, rev_num);
			deleteCount = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("reviewDelete 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}
	
	public int updateReadCount(int rev_num) {
		
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update review_board set rev_readcount = " + 
					"rev_readcount + 1 where rev_num = "+rev_num;
		
		try {
			pstmt = conn.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("setReadCountUpdate 에러 : " + ex);
		}
		finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public ArrayList<ReviewBean> DetailReviewList(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String Review_list_sql = "select * from review_board order by rev_num desc";
		ArrayList<ReviewBean> reviewList  = new ArrayList<ReviewBean>();
		ReviewBean review = null;
		
		try {
			pstmt = conn.prepareStatement(Review_list_sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				review = new ReviewBean();
				review.setRev_num(rs.getInt("rev_num"));
				review.setP_name(rs.getString("p_name"));
				review.setTitle(rs.getString("title"));
				review.setM_id(rs.getString("m_id"));
				review.setImage(rs.getString("image"));
				review.setRev_content(rs.getString("rev_content"));
				review.setRev_readcount(rs.getInt("rev_readcount"));
				review.setRev_date(rs.getDate("rev_date"));
				review.setScore(rs.getString("score"));
				reviewList.add(review);
			}
		}catch (Exception ex) {
			System.out.println("getDetailReviewList 에러: " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return reviewList;
	}

}
