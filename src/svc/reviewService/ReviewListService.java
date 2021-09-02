package svc.reviewService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewDAO;
import vo.ReviewBean;

public class ReviewListService {
	public int getListCount() throws Exception {

		int listCount = 0;
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		listCount = reviewDAO.selectListCount();
		close(conn);
		return listCount;

	}

	public ArrayList<ReviewBean> getReviewList(int page, int limit) throws Exception {

		ArrayList<ReviewBean> reviewList = null;
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		reviewList = reviewDAO.selectReviewList(page, limit);
		close(conn);
		return reviewList;

	}

	public ArrayList<ReviewBean> getDetailReviewList() throws Exception {

		ArrayList<ReviewBean> detailReviewList = null;
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		detailReviewList = reviewDAO.DetailReviewList();
		close(conn);
		return detailReviewList;
	}
}
