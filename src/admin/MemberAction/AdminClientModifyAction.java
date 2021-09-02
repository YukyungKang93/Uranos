package admin.MemberAction;

import static util.JdbcUtil.getConnection;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import dao.MemberDAO;
import vo.ActionForward;
import vo.MemberBean;

public class AdminClientModifyAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberBean memberBean = new MemberBean();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		HttpSession session = request.getSession(true);
		
		String check = "성공";
		
		if(request.getParameter("button").equals("수정")) {
			
			memberBean.setM_PW(request.getParameter("password"));
			memberBean.setM_NAME(request.getParameter("name"));
			memberBean.setM_ID(request.getParameter("id"));
			memberBean.setADDR(request.getParameter("addr"));
			memberBean.setDETAIL_ADDR(request.getParameter("detail_addr"));
			memberBean.setREF_ADDR(request.getParameter("ref_addr"));
			memberBean.setEMAIL(request.getParameter("email"));
			memberBean.setPHONE(request.getParameter("phone"));
			memberBean.setGENDER(request.getParameter("gender"));
			memberBean.setBIRTH(request.getParameter("birth"));
			memberBean.setM_PW_CONFIRM(request.getParameter("button"));
			
			session.setAttribute("임시저장", memberBean);
			
			check = memberDAO.updateClient(request.getParameter("id"), memberBean);
			
		}else if(request.getParameter("button").equals("탈퇴")) {
			
			memberBean.setM_PW(request.getParameter("password"));
			memberBean.setM_ID(request.getParameter("id"));
			memberBean.setM_PW_CONFIRM(request.getParameter("button"));
			
			session.setAttribute("임시저장", memberBean);
			
			check = memberDAO.deleteClient(request.getParameter("id"), memberBean);
			
		}
		
		if(check=="실패") {
			
			memberBean = (MemberBean)session.getAttribute("임시저장");
						
			System.out.println("test : " + request.getAttribute("idModify"));
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/managerpass.do");
			
			return forward;
		}
		System.out.println("test로 가나??");
		
		session.removeAttribute("임시저장");
		
		response.sendRedirect("testaction.do");
		
		return forward;
	}

}
