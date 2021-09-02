package svc.reviewService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.ReviewBean;

public class ReviewWriteService {
	public boolean registArticle(ReviewBean reviewBoard) throws Exception{
		boolean isWriteSuccess = false;
		
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		int insertCount = reviewDAO.insertReview(reviewBoard);
		
		if(insertCount > 0) {
			commit(conn);
			isWriteSuccess = true;
		}else {
			rollback(conn);
		}
		close(conn);
		return isWriteSuccess;
	}

}
