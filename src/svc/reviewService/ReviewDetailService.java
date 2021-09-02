package svc.reviewService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.ReviewBean;

public class ReviewDetailService {

	public ReviewBean getReview(int rev_num) throws Exception{
		
		ReviewBean review = null;
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		int updateCount = reviewDAO.updateReadCount(rev_num);
		
		if(updateCount > 0){
			commit(conn);
		}
		else{
			rollback(conn);
		}
		
		review = reviewDAO.selectReview(rev_num);
		close(conn);
		return review;
		
	}
}
