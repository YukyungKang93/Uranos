package action.noticeAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.noticeService.NoticeDetailService;
import vo.ActionForward;
import vo.Notice;

 public class NoticeDetailAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		 HttpSession session = request.getSession(true);
			String m_id= 
					(String) session.getAttribute("M_ID");
		int n_num =Integer.parseInt(request.getParameter("n_num"));
		String page = request.getParameter("page");
		NoticeDetailService noticeDetailService = new NoticeDetailService();
		Notice article = noticeDetailService.getArticle(n_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
	 	if (m_id == null) {
			forward.setPath("/WEB-INF/notice/noticeDetail.jsp");
			}
	else if (m_id.equals("admin")) {
		forward.setPath("/WEB-INF/adminNotice/adminNoticeDetail.jsp");
	} else {
		forward.setPath("/WEB-INF/notice/noticeDetail.jsp");
	}
	 return forward;
	
 }
}