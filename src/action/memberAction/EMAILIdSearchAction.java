package action.memberAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.memberService.EmailIdSearchService;
import vo.ActionForward;
import vo.MemberBean;

public class EMAILIdSearchAction implements Action{
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		ActionForward forward = null;
		HttpSession session = request.getSession(true);
		
		String EMAIL = request.getParameter("email1") + "@" + request.getParameter("email2");
		
		MemberBean memberBean = new MemberBean();
		
		memberBean.setM_NAME(request.getParameter("M_NAME1"));
		memberBean.setEMAIL(EMAIL);
		
		EmailIdSearchService id_search = new EmailIdSearchService();
		String ID = id_search.idSearch(memberBean);
		
		session.setAttribute("M_NAME1", (String)request.getParameter("M_NAME1"));
		session.setAttribute("M_NAME2", (String)request.getParameter("M_NAME2"));
		session.setAttribute("M_ID1", ID);
		
		response.sendRedirect("/member/ID.do");

		return forward;
	}
}
