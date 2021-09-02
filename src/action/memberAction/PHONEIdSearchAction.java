package action.memberAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.memberService.PhoneIdSearchService;
import vo.ActionForward;
import vo.MemberBean;

public class PHONEIdSearchAction implements Action{
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		ActionForward forward = null;
		HttpSession session = request.getSession(true);
		
		MemberBean memberBean = new MemberBean();
		
		System.out.println("아이디찾기 테스트1");
		
		System.out.println("이름 : " + request.getParameter("M_NAME2"));
		System.out.println("전화번호 : " + request.getParameter("PHONE"));
		
		memberBean.setM_NAME(request.getParameter("M_NAME2"));	
		memberBean.setPHONE(request.getParameter("PHONE"));
		
		PhoneIdSearchService id_search = new PhoneIdSearchService();
		String ID = id_search.idSearch(memberBean);
		
		session.setAttribute("M_NAME1", request.getParameter("M_NAME2"));
		session.setAttribute("M_ID1", ID);
		
		response.sendRedirect("ID.do");

		return forward;
	}
}
