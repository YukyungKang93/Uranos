package action.memberAction;

import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.memberService.SignupService;
import vo.ActionForward;
import vo.MemberBean;

public class SignUpAction implements Action{
	
	private static final int SALT_SIZE = 16;
		
		// SALT 값 생성  
		private String getSALT() throws Exception {
			SecureRandom rnd = new SecureRandom();
			byte[] temp = new byte[SALT_SIZE];
			rnd.nextBytes(temp);
			
			return Byte_to_String(temp);
			
		}
		
		
		// 바이트 값을 16진수로 변경해준다 
		private String Byte_to_String(byte[] temp) {
			StringBuilder sb = new StringBuilder();
			for(byte a : temp) {
				sb.append(String.format("%02x", a));
			}
			return sb.toString();
		}	
	
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		ActionForward forward = null;
		String ADDR = request.getParameter("sample6_address");
		String DETAIL_ADDR = request.getParameter("sample6_detailAddress");
		String REF_ADDR = request.getParameter("sample6_extraAddress");
		
		String EMAIL = request.getParameter("email1") + "@" + request.getParameter("email2");
		
		String year = request.getParameter("YEAR");
		String month = request.getParameter("MONTH");
		String day = request.getParameter("DAY");
		
		String birth = null;
		
		String SALT = getSALT();
		
		if((!year.equals("") && !month.equals("") && !day.equals(""))) {
			
			if(month.length() == 1) {
				month = "0" + month;
			}
			if(day.length() == 1) {
				day = "0" + day;
			}
			
			birth = year + "-" + month + "-" + day;
		}else {
			birth="";
		}
		
		if(DETAIL_ADDR == null) {
			DETAIL_ADDR = "";
		}
		if(REF_ADDR == null) {
			REF_ADDR = "";
		}
		
		MemberBean memberBean = new MemberBean();
		memberBean.setM_ID(request.getParameter("M_ID"));		memberBean.setM_PW(request.getParameter("M_PW"));
		memberBean.setM_NAME(request.getParameter("M_NAME"));	memberBean.setADDR(ADDR);		memberBean.setDETAIL_ADDR(DETAIL_ADDR);		memberBean.setREF_ADDR(REF_ADDR);		
		memberBean.setEMAIL(EMAIL);								memberBean.setPHONE(request.getParameter("PHONE"));		
		
		if(request.getParameter("GENDER") == null) {
			memberBean.setGENDER("");
		}else {
			memberBean.setGENDER(request.getParameter("GENDER"));
		}
		
		memberBean.setBIRTH(birth);	memberBean.setSALT(SALT);
		
		SignupService boardWriteProService = new SignupService();
		int registmember = boardWriteProService.registmember(memberBean);
		
		boolean registarticle = boardWriteProService.checkId(memberBean);
		
		if(registarticle == true) { // 중복되는 ID가 있을 경우
			
		}
		
		request.setAttribute("M_NAME", request.getParameter("M_NAME"));
		
		response.sendRedirect(request.getContextPath() + "/member/login.do");
		
		return forward;
	}
}
