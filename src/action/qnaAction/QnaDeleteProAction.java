package action.qnaAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qnaService.QnaDeleteProService;
import vo.ActionForward;

public class QnaDeleteProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String nowPage = request.getParameter("page");
		QnaDeleteProService boardDeleteProService = new QnaDeleteProService();

		boolean replycountSuccess = boardDeleteProService.replyArticle(qna_num);

		if (!replycountSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글이 있는건 삭제 불가');");
			out.println("history.back();");
			out.println("</script>");
			out.close();

		} else {

			boolean isDeleteSuccess = boardDeleteProService.removeArticle(qna_num);
			if (!isDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();

			} else {

				response.setContentType("text/html;charset=UTF-8");
	            PrintWriter out=response.getWriter();
	            out.println("<script>");
	            out.println("alert('삭제되었습니다');");
	            out.println("location.href='/qnaList.do?page=" + nowPage + "';"); 
	            out.println("</script>");
			}
		}

		return forward;
	}
}