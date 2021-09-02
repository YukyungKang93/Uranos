package action.programAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.programService.ReservationService;
import vo.ActionForward;
import vo.MemberBean;
import vo.ProgramBean;

public class ReservationAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 임시로 세션에 아이디 123으로 저장해서 사용했음. 로그인 통합시에 해당 아이디로 예약 되는지 확인필요
		HttpSession session = request.getSession(true);
		ActionForward forward = null;
		ProgramBean programBean = null;
		MemberBean member = null;
		programBean = new ProgramBean();
		member = new MemberBean();
		// programBean.setP_num((Integer)(request.getAttribute("p_num")));
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		session.setAttribute("p_num", p_num);
		
		if(session.getAttribute("M_ID")==null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인을 해주세요');");
			out.println("</script>");
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("url.do?URL=reservation.do"); // 예약 성공시 인덱스로 페이지 이동하게 해둠. >>이걸 회원 내정보 예약확인페이지로 하는게 좋을거 같음.
			
		return forward;
			
		}
		
		member.setM_ID((String) (session.getAttribute("M_ID")));
		ReservationService reservationService = new ReservationService();

		boolean isReservation = reservationService.reservation(member, p_num);
			forward = new ActionForward();
			forward.setRedirect(true);
			//forward.setPath("MemberModifyForm.do"); // 예약 성공시 인덱스로 페이지 이동하게 해둠. >>이걸 회원 내정보 예약확인페이지로 하는게 좋을거 같음.
			forward.setPath("MemberModifyForm.do?myAct="); // 안 가지길래 수정한 부분
			
			session.removeAttribute("p_num");
			session.removeAttribute("URL");

		return forward;
	}
}