package action.reviewAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.reviewService.ReviewDetailService;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewDetailAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		 	
		int rev_num=Integer.parseInt(request.getParameter("Rev_num"));
		String page = request.getParameter("page");
		ReviewDetailService reviewDetailService = new ReviewDetailService();
		ReviewBean review = reviewDetailService.getReview(rev_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("review", review);
  		forward.setPath("/WEB-INF/review/reviewDetail.jsp");
  		return forward;

	 }
	 
}
