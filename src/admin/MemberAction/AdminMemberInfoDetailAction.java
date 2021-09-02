package admin.MemberAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import dao.MemberDAO;
import svc.memberService.MemberInfoDetailService;
import svc.memberService.MemberMyProService;
import svc.memberService.MemberMyRepService;
import svc.memberService.MemberMyRevService;
import vo.ActionForward;
import vo.MemberBean;

public class AdminMemberInfoDetailAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		
		HttpSession session = request.getSession(true);
		System.out.println("action에 오나????");
		String idCheck = request.getParameter("check");
		System.out.println("action에서 어떻게 찍힘??? : " + idCheck);
		ActionForward forward = null;
		
		//내정보 : 상세정보
		MemberInfoDetailService mids = new MemberInfoDetailService();
		MemberBean memberInfoDetailBean = mids.isDetailInfoSvc(idCheck);
		
		//내정보 : 예약정보
		MemberMyRevService mrds = new MemberMyRevService();
		MemberBean memberMyRevBean = mrds.isMyRevSvc(idCheck);
		System.out.println("memberMyRevBean에 값있어?? : " + memberMyRevBean.getRevData().size());
		
		//내정보 : 신청정보
		MemberMyProService mmps = new MemberMyProService();
		MemberBean memberMyProBean = mmps.isMyProSvc(idCheck);
		
		//내정보 : 댓글정보
		MemberMyRepService mmrs = new MemberMyRepService();
		MemberBean memberMyRepBean = mmrs.isMyRepSvc(idCheck);
		
		session.setAttribute("rev", memberMyRevBean.getRevData());
		request.setAttribute("revData", memberMyRevBean.getRevData());
		request.setAttribute("proData", memberMyProBean.getProData());
		request.setAttribute("repData", memberMyRepBean.getRepData());

		ArrayList<String> repData = null;
		repData = (ArrayList<String>)request.getAttribute("repData");
	
		
		request.setAttribute("name", memberInfoDetailBean.getM_NAME());
		request.setAttribute("id", memberInfoDetailBean.getM_ID());
		request.setAttribute("addr", memberInfoDetailBean.getADDR());
		request.setAttribute("detail_addr", memberInfoDetailBean.getDETAIL_ADDR());
		request.setAttribute("ref_addr", memberInfoDetailBean.getREF_ADDR());
		request.setAttribute("email1", memberInfoDetailBean.getEMAIL());
		request.setAttribute("phone", memberInfoDetailBean.getPHONE());
		request.setAttribute("gender", memberInfoDetailBean.getGENDER());
		request.setAttribute("birth", memberInfoDetailBean.getBIRTH());
		
		String p_num_revDel = request.getParameter("p_num_revDel");
				
		MemberDAO mDao = MemberDAO.getInstance();
		
		mDao.myRevDel(idCheck, p_num_revDel);
		mDao.proCountUp(p_num_revDel);
		
		if(request.getAttribute("deleteProtect") != null) {
			boolean delPro = (boolean)request.getAttribute("deleteProtect");
					request.setAttribute("delPro", delPro);
		}else if(request.getAttribute("deleteProtect") == null){
		}
		
		String myDetail = request.getParameter("myDetail");
		String myAct = request.getParameter("myAct");
		String myApply = request.getParameter("myApply");
		String myReply = request.getParameter("myReply");
		String myMod = request.getParameter("myMod");
		
		boolean a = session.getAttribute("data")!=null;
		boolean check = memberMyRevBean.getRevData()==null;
		
		System.out.println("check : " + check);
		
		if(request.getParameter("type").equals("정보수정")) {
			request.setAttribute("check1", check);
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("clientdetailinfo.do");
			return forward;

		}else if(request.getParameter("type").equals("활동내역")) {
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("clientactionlog.do");
			return forward;

		}
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("WEB-INF/adminMember/clientDetailInfo.jsp");
		return forward;
		
	}
}
