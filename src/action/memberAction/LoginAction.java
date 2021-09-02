package action.memberAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.memberService.LoginService;
import vo.ActionForward;
import vo.MemberBean;

public class LoginAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		
		ActionForward forward = null;

		MemberBean SALT = new MemberBean();
		SALT.setM_ID(request.getParameter("M_ID"));

		MemberBean memberBean = new MemberBean();
		memberBean.setM_ID(request.getParameter("M_ID"));
		memberBean.setM_PW(request.getParameter("M_PW"));

		LoginService loginService = new LoginService();
		String Login = loginService.logIn(memberBean);
		String url = "";
		int p_num = 0;

		if (Login.equals("로그인실패")) {

			session.setAttribute("fail", "아이디나 비밀번호가 틀렸습니다.");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/member/login.do");

			return forward;
		}
		session.setAttribute("M_NAME", Login);
		session.setAttribute("M_ID", request.getParameter("M_ID"));
		session.setAttribute("M_PW", request.getParameter("M_PW"));
		request.getSession().setAttribute("member", memberBean);
		if(session.getAttribute("URL")!=null) {
			if((Integer)session.getAttribute("p_num")!=-1) {
				System.out.println("예약액션노트로 가나???");
				url = (String)session.getAttribute("URL");
				p_num = (Integer)session.getAttribute("p_num");
				session.removeAttribute("URL");
				session.removeAttribute("p_num");
				response.sendRedirect(request.getContextPath() + "/" + url + "?p_num=" + p_num);
				
			}else {
				url = (String)session.getAttribute("URL");
				session.removeAttribute("URL");
				response.sendRedirect(request.getContextPath() + "/" + url);
			}
			
		}else if(session.getAttribute("URL") == null){
			if (session.getAttribute("M_ID").equals("admin")) {
				response.sendRedirect(request.getContextPath() + "/adminindexpro.do");
			} else {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
		}
		return forward;

	}
}
