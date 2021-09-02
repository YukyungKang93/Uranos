package action.qnaAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qnaService.QnaViewProService;
import vo.ActionForward;

public class QnaViewProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 

		ActionForward forward = null;
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		String nowPage = request.getParameter("page");
		QnaViewProService boardViewProService = new QnaViewProService();
		boolean isArticleWriter =boardViewProService.isArticleWriter(qna_num, request.getParameter("m_pw"));

		if(!isArticleWriter){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		} else {
			boolean isDeleteSuccess = boardViewProService.removeArticle(qna_num);
			if(!isDeleteSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('상세내용보기실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("/qnaDetail.do?qna_num="+ qna_num + "&page=" + nowPage);
			}
		}
		return forward;
	}
}