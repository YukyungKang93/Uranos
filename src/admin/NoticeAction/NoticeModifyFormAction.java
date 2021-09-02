package admin.NoticeAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.noticeService.NoticeDetailService;
import vo.ActionForward;
import vo.Notice;

public class NoticeModifyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	ActionForward forward = new ActionForward();
			int n_num=Integer.parseInt(request.getParameter("n_num"));
			NoticeDetailService noticeDetailService
			= new NoticeDetailService();	
		   	Notice article =noticeDetailService.getArticle(n_num);
		   	request.setAttribute("article", article);
	   		forward.setPath("WEB-INF/adminNotice/adminNoticeModifyForm.jsp");
	   		return forward;
	   		
	 }
	 
}