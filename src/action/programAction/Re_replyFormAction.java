package action.programAction;

	import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.programService.ProgramDetailService;
import vo.ActionForward;
import vo.P_replyBean;

	public class Re_replyFormAction implements Action{
		
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Re_replyFormAction 1");
		   		String nowPage = request.getParameter("page");
		   		int reply_num=Integer.parseInt(request.getParameter("reply_num"));
		   		int p_num=Integer.parseInt(request.getParameter("p_num"));
		   		
				System.out.println("Re_replyFormAction 2 : " + reply_num);
				System.out.println("Re_replyFormAction 2 : " +p_num);
		   		ProgramDetailService programDetailService = new ProgramDetailService();
		   		P_replyBean reply=programDetailService.getReply(reply_num);	
		   		System.out.println("p_num:::" + reply.getP_num());
		   		
		   		
		   		
		   		
				System.out.println("Re_replyFormAction 3");
		   		request.setAttribute("reply", reply);
		   		request.setAttribute("page", nowPage);

				System.out.println("Re_replyFormAction 4");
				ActionForward forward = new ActionForward();
		   		forward.setPath("/WEB-INF/program/re_reply.jsp");
		   		return forward;
				
			}
		}