package action.programAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.programService.ProgramDetailService;
import svc.programService.ModifyReplyProService;
import vo.ActionForward;
import vo.P_replyBean;
import vo.ProgramBean;

public class ReplyModifyProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("ReplyModifyAction - 1");
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int reply_num = Integer.parseInt(request.getParameter("reply_num"));
		String m_id = request.getParameter("m_id");
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		String nowPage = request.getParameter("page");
		ProgramDetailService programDetailService = new ProgramDetailService();
		P_replyBean reply = programDetailService.getReply(reply_num);
		ModifyReplyProService modifyProService = new ModifyReplyProService();

		boolean isArticleWriter = modifyProService.isArticleWriter(reply_num, request.getParameter("m_id"));
		if (!isArticleWriter) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('작성할 권한이 없습니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		} else {
			String content=request.getParameter("content");
			content = content.replace("\r\n","<br>");
			reply.setContent(content);
			isModifySuccess = modifyProService.modifyArticle(reply);

			if (!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정완료');");
				out.println("location.href='/programDetail.do?p_num=" + p_num + "';");
				out.println("</script>");

			}
		}

		return forward;
	}
}