package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class FlexibleURL implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		HttpSession session = request.getSession(true);
		ActionForward forward = null;
		
		
		session.setAttribute("URL", request.getParameter("URL"));
		System.out.println("URL뭐받아???? : " + request.getParameter("URL"));
		System.out.println("그럼 세션에서도 뭔가 받아???? : " + session.getAttribute("p_num"));
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("member/login.do");

		return forward;
	}
}
