package action.memberAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.MemberDAO;
import svc.memberService.MemberInfoDetailService;
import svc.memberService.MemberMyProService;
import svc.memberService.MemberMyRepService;
import svc.memberService.MemberMyRevService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberInfoDetailAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
	
		String idCheck = request.getSession().getAttribute("M_ID").toString();
	
		ActionForward forward = null;
		
		//내정보 : 상세정보
		MemberInfoDetailService mids = new MemberInfoDetailService();
		MemberBean memberInfoDetailBean = mids.isDetailInfoSvc(idCheck);
		
		//내정보 : 예약정보
		MemberMyRevService mrds = new MemberMyRevService();
		MemberBean memberMyRevBean = mrds.isMyRevSvc(idCheck);
		
		//내정보 : 신청정보
		MemberMyProService mmps = new MemberMyProService();
		MemberBean memberMyProBean = mmps.isMyProSvc(idCheck);
		
		//내정보 : 댓글정보
		MemberMyRepService mmrs = new MemberMyRepService();
		MemberBean memberMyRepBean = mmrs.isMyRepSvc(idCheck);
		
		String email = memberInfoDetailBean.getEMAIL();
		String email1 = email.substring(0,(email.indexOf("@")));
		String email2 = email.substring((email.lastIndexOf("@")+1));
		
		request.setAttribute("revData", memberMyRevBean.getRevData());
		request.setAttribute("proData", memberMyProBean.getProData());
		request.setAttribute("repData", memberMyRepBean.getRepData());
		
		
//		ArrayList<String> revData = null;
//		revData = memberMyRevBean.getRevData();		
//		System.out.println(revData + ": 멤버인포디테일액션에서 예약정보 확인");
//
//		ArrayList<String> repData = null;
//		repData = memberMyRepBean.getRepData();
//		System.out.println(repData + ": 멤버인포디테일액션에서 댓글정보 확인");
//		
//		ArrayList<String> proData = null;
//		proData = memberMyProBean.getProData();
//		System.out.println(proData + ": 멤버인포디테일액션에서 신청정보 확인");

		request.setAttribute("M_NAME", memberInfoDetailBean.getM_NAME());
		request.setAttribute("M_ID", memberInfoDetailBean.getM_ID());
		request.setAttribute("ADDR", memberInfoDetailBean.getADDR());
		request.setAttribute("DETAIL_ADDR", memberInfoDetailBean.getDETAIL_ADDR());
		request.setAttribute("REF_ADDR", memberInfoDetailBean.getREF_ADDR());
		request.setAttribute("EMAIL1", email1);
		request.setAttribute("EMAIL2", email2);
		request.setAttribute("PHONE", memberInfoDetailBean.getPHONE());
		request.setAttribute("GENDER", memberInfoDetailBean.getGENDER());
		request.setAttribute("BIRTH", memberInfoDetailBean.getBIRTH());
		
		String revDeleteCheck = request.getParameter("revDeleteCheck");			
		if(revDeleteCheck != null) {
			if(revDeleteCheck.equals("1")) {
				String p_num_revDel = request.getParameter("p_num_revDel");
				MemberDAO mDao = MemberDAO.getInstance();
				mDao.myRevDel(idCheck, p_num_revDel);
				mDao.proCountUp(p_num_revDel);
				
				String myAct = "myAct"; 
				response.sendRedirect("MemberModifyForm.do?myAct="+myAct);
				return forward;
				
			}
		}
		
		if(request.getAttribute("deleteProtect") != null) {
			boolean delPro = (boolean)request.getAttribute("deleteProtect");
					request.setAttribute("delPro", delPro);
		}else if(request.getAttribute("deleteProtect") == null){
		}
		
		String myDetail = (String)request.getParameter("myDetail");
		String myAct = (String)request.getParameter("myAct");
		String myApply = (String)request.getParameter("myApply");
		String myReply = (String)request.getParameter("myReply");
		String myMod = (String)request.getParameter("myMod");
		
		if(myDetail != null || myMod != null || myAct != null || myApply != null || myReply != null) {
			if(myDetail != null) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("WEB-INF/member/MemberDetailForm.jsp");
				return forward;

			}else if(myMod != null){
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("WEB-INF/member/MemberModifyForm.jsp");
				return forward;

			}else if(myApply != null){
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("WEB-INF/member/MemberApplyForm.jsp");
				return forward;

			}else if(myReply != null){
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("WEB-INF/member/MemberReplyForm.jsp");
				return forward;

			}else {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("WEB-INF/member/MemberActivityForm.jsp");
				return forward;
			}
		}

		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("WEB-INF/member/MemberDetailForm.jsp");
		return forward;
	}

}
