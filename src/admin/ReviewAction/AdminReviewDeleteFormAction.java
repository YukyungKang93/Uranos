package admin.ReviewAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.reviewService.ReviewDetailService;
import vo.ActionForward;
import vo.ReviewBean;

public class AdminReviewDeleteFormAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		int rev_num = Integer.parseInt(request.getParameter("Rev_num"));
		ReviewDetailService adminReviewDetailService = new ReviewDetailService();
		ReviewBean review = adminReviewDetailService.getReview(rev_num);
		request.setAttribute("review", review);
		forward.setPath("/WEB-INF/adminReview/adminReviewDelete.jsp");
		return forward;

	}
}
