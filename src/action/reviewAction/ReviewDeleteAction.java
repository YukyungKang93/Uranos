package action.reviewAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.reviewService.ReviewDeleteService;
import vo.ActionForward;

public class ReviewDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		int rev_num = Integer.parseInt(request.getParameter("Rev_num"));
		String nowPage = request.getParameter("page");
		ReviewDeleteService reviewDeleteService = new ReviewDeleteService();
		boolean isRemoveSuccess = reviewDeleteService.removeReview(rev_num);

		if (!isRemoveSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("reviewList.do");
		}
		return forward;
	}
}
