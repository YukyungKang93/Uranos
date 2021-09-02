package svc.adminReviewService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.ReviewBean;

public class AdminReviewDeleteService {
	public boolean deleteReview(ReviewBean review) throws Exception {

		boolean isModifySuccess = false;
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		int updateCount = reviewDAO.adminUpdateReview(review);

		if (updateCount > 0) {
			commit(conn);
			isModifySuccess = true;
		} else {
			rollback(conn);
		}

		close(conn);
		return isModifySuccess;

	}
}
