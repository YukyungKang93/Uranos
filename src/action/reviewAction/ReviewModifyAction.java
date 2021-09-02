package action.reviewAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.reviewService.ReviewModifyService;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewModifyAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		boolean isModifySuccess = false;
		int rev_num = Integer.parseInt(request.getParameter("Rev_num"));
		ReviewBean review = new ReviewBean();
		ReviewModifyService reviewModifyService = new ReviewModifyService();

		review.setRev_num(rev_num);
		review.setScore(request.getParameter("Score"));
		review.setP_name(request.getParameter("P_name"));
		review.setM_id(request.getParameter("M_id"));
		review.setTitle(request.getParameter("Title"));
		review.setImage(request.getParameter("Image"));
		String content=request.getParameter("Rev_content");
		content = content.replace("\r\n","<br>");
		review.setRev_content(content);
		isModifySuccess = reviewModifyService.modifyReview(review);

		if (!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정실패');");
			out.println("history.back()");
			out.println("</script>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정완료');");
			out.println("location.href='/reviewDetail.do?Rev_num=" + review.getRev_num()+"';");
			out.println("</script>");
		}

		return forward;

	}
}
