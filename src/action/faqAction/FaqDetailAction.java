package action.faqAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.faqService.FaqDetailService;
import vo.ActionForward;
import vo.Faq;

 public class FaqDetailAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		 HttpSession session = request.getSession(true);
			String m_id= 
					(String) session.getAttribute("M_ID");
		 
		int f_num =Integer.parseInt(request.getParameter("f_num"));
		String page = request.getParameter("page");
		FaqDetailService boardDetailService = new FaqDetailService();
		Faq article = boardDetailService.getArticle(f_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
	   	
	   	if (m_id == null) {
			forward.setPath("/WEB-INF/faq/faqDetail.jsp");
			}
	else if (m_id.equals("admin")) {
		forward.setPath("/WEB-INF/adminFaq/adminFaqDetail.jsp");
	} else {
		forward.setPath("/WEB-INF/faq/faqDetail.jsp");
	}
	 return forward;
	
 }
}