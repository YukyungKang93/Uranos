package admin.ReviewAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adminReviewService.AdminReviewDeleteService;
import vo.ActionForward;
import vo.ReviewBean;

public class AdminReviewDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		boolean isModifySuccess = false;
		int rev_num = Integer.parseInt(request.getParameter("Rev_num"));
		System.out.println(request.getParameter("Rev_num"));
		ReviewBean reviewAdmin = new ReviewBean();
		AdminReviewDeleteService adminReviewDeleteService = new AdminReviewDeleteService();

		reviewAdmin.setRev_num(rev_num);
		reviewAdmin.setScore(request.getParameter("Score"));
		reviewAdmin.setP_name(request.getParameter("P_name"));
		reviewAdmin.setM_id(request.getParameter("M_id"));
		reviewAdmin.setTitle(request.getParameter("Title"));
		reviewAdmin.setImage(request.getParameter("Image"));
		reviewAdmin.setRev_content(request.getParameter("Rev_content"));
		isModifySuccess = adminReviewDeleteService.deleteReview(reviewAdmin);

		if (!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패');");
			out.println("history.back()");
			out.println("</script>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 완료');");
			out.println("location.href='reviewDetail.do?Rev_num=" + reviewAdmin.getRev_num() + "';");
			System.out.println(reviewAdmin.getRev_num());
			out.println("</script>");
		}

		return forward;

	}
}

