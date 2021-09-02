package action.memberAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import dao.MemberDAO;
import svc.memberService.MemberDeleteService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberDeleteAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		ActionForward forward = null;
		
		MemberBean memberBean = new MemberBean();
		String id = (String)session.getAttribute("M_ID");

		memberBean.setM_ID(id);
		MemberDAO mDao = MemberDAO.getInstance();
		
		
		//예약정보가 있다면 alert()
		if(mDao.deleteProtectRev(id)) {
			boolean deleteProtect = mDao.deleteProtectRev(id);
			request.setAttribute("deleteProtect", deleteProtect);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("MemberModifyForm.do");
			return forward;
		}else {
			MemberDeleteService deleteService = new MemberDeleteService();
			String Delete = deleteService.delete(memberBean);
			session.invalidate();
			response.sendRedirect("index.jsp");
			return forward;
		}
		
	}
}
