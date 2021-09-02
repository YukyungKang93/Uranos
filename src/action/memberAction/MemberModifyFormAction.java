package action.memberAction;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.memberService.ChangePasswordService;
import svc.memberService.MemberModifyService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberModifyFormAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idCheck = request.getSession().getAttribute("M_ID").toString();

		ActionForward forward = null;

		String m_name = request.getParameter("modName");
		String addr = request.getParameter("address"); 
		String detail_address = request.getParameter("detailAddress");
		String ref_address = request.getParameter("extraAddress");
		String email1 = request.getParameter("modEmail1");
		String email2 = request.getParameter("modEmail2");
		String email = email1 + "@" + email2;
		String phone = request.getParameter("modPhone");
		String gender = request.getParameter("modGen");

		String year = request.getParameter("YEAR");
		String month = request.getParameter("MONTH");
		String day = request.getParameter("DAY");

		String birth = null;

		if (year != null && month != null && day != null) {

			if (month.length() == 1) {
				month = "0" + month;
			}
			if (day.length() == 1) {
				day = "0" + day;
			}

			birth = year + "-" + month + "-" + day;
		}


		String curM_pw = request.getParameter("curPw");
		String newM_pw = request.getParameter("newPw");



		MemberBean memberBean = new MemberBean();
		memberBean.setM_NAME(m_name);
		memberBean.setADDR(addr);
		memberBean.setDETAIL_ADDR(detail_address);
		memberBean.setREF_ADDR(ref_address);
		memberBean.setEMAIL(email);
		memberBean.setPHONE(phone);
		if (gender == null) {
			memberBean.setGENDER("");
		} else {
			memberBean.setGENDER(gender);
		}

		memberBean.setBIRTH(birth);

		MemberModifyService modifyService = new MemberModifyService();
		boolean isModifySuccecc = modifyService.modifyService(idCheck, memberBean);

		ChangePasswordService changePasswordService = new ChangePasswordService();
		boolean isModifyPwSuccecc = changePasswordService.changePasswordService(idCheck, curM_pw, newM_pw);
		// boolean isModifyPwSuccecc =
		// changePasswordService.changePasswordService(test_id, curM_pw, newM_pw);

		if (!isModifySuccecc) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('액션에서 문제가 발생했습니다 ');");
			out.println("history.back();");
			out.println("</script>");
		}

		if (!isModifyPwSuccecc) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('액션에서 문제가 발생했습니다 ');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			HttpSession session = request.getSession(true);
			session.setAttribute("M_PW", newM_pw);
		}
		 
		response.sendRedirect("MemberModifyForm.do?myDetail=myDetail");
		return forward;

	}
}
