package action.programAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.programService.ProgramDetailService;
import vo.ActionForward;
import vo.P_replyBean;

public class ReplyModifyFormAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		String nowPage = request.getParameter("page");
		int reply_num=Integer.parseInt(request.getParameter("reply_num"));
		int p_num=Integer.parseInt(request.getParameter("p_num"));
		String m_id=request.getParameter("m_id");
		ProgramDetailService programDetailService = new ProgramDetailService();
   		P_replyBean reply=programDetailService.getReply(reply_num);	
		System.out.println("replyModifyformAction complete!!!!");
		request.setAttribute("reply", reply);
	   	request.setAttribute("page", nowPage);
	   	forward.setPath("/WEB-INF/program/replyModify.jsp");
   		return forward;
	
	}
}


