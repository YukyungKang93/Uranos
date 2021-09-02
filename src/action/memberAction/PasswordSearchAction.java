package action.memberAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.memberService.PasswordSearchService;
import vo.ActionForward;
import vo.MemberBean;

public class PasswordSearchAction implements Action{
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		ActionForward forward = null;
		HttpSession session = request.getSession(true);
		
		String EMAIL = request.getParameter("email1") + "@" + request.getParameter("email2");
		
		MemberBean memberBean = new MemberBean();
		memberBean.setM_NAME(request.getParameter("M_NAME"));
		memberBean.setM_ID(request.getParameter("M_ID"));
		memberBean.setEMAIL(EMAIL);
		
		PasswordSearchService  passSearch= new PasswordSearchService();
		String password = passSearch.passwordSearch(memberBean);
		
		session.setAttribute("M_NAME2", (String)request.getParameter("M_NAME"));
		session.setAttribute("S_PASS", password);

		response.sendRedirect("/member/PASSWORD.do");
		
		return forward;
	}
}
