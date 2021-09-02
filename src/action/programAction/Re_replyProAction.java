package action.programAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.programService.ProgramDetailService;
import vo.ActionForward;
import vo.P_replyBean;
import vo.ProgramBean;

public class Re_replyProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		P_replyBean reply = new P_replyBean();
		int p_num = Integer.parseInt(request.getParameter("p_num"));

		reply.setM_id(request.getParameter("m_id"));
		String content = request.getParameter("content");
		content = content.replace("\r\n", "<br>");
		reply.setContent(content);
		reply.setGroup_num(Integer.parseInt(request.getParameter("group_num")));
		reply.setP_num(Integer.parseInt(request.getParameter("p_num")));

		ProgramDetailService rereplyProService = new ProgramDetailService();

		boolean isReplySuccess = rereplyProService.replyArticle(reply);
		ProgramBean program = (ProgramBean) request.getAttribute("program");

		if (!isReplySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");

		} else {

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록완료');");
			out.println("location.href='/programDetail.do?p_num=" + p_num + "';");
			out.println("</script>");
		}

		return forward;

	}

}
