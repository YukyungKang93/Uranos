package action.qnaAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qnaService.QnaModifyProService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaModifyProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		boolean isModifySuccess = false;
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String nowPage = request.getParameter("page");
		QnaBean article = new QnaBean();
		QnaModifyProService qnaModifyProService = new QnaModifyProService();
		boolean isRightUser = qnaModifyProService.isArticleWriter(qna_num, request.getParameter("m_pw"));

		if (!isRightUser) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			article.setQna_num(qna_num);
			article.setTitle(request.getParameter("title"));
			article.setM_id(request.getParameter("m_id"));
			String content=request.getParameter("qna_content");
			content = content.replace("\r\n","<br>");
			article.setQna_content(content);
			isModifySuccess = qnaModifyProService.modifyArticle(article);

			if (!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back()");
				out.println("</script>");
				
			} else {
				
				response.setContentType("text/html;charset=UTF-8");
	            PrintWriter out=response.getWriter();
	            out.println("<script>");
	            out.println("alert('수정완료');");
	            out.println("location.href='/qnaDetail.do?qna_num=" + article.getQna_num() + "&page=" + nowPage + "';"); 
	            out.println("</script>");
			}
		}
		return forward;
	}
}