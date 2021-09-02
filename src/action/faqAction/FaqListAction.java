package action.faqAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.faqService.FaqListService;
import vo.ActionForward;
import vo.Faq;
import vo.PageInfo;

 public class FaqListAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 HttpSession session = request.getSession(true);
			String m_id= 
					(String) session.getAttribute("M_ID");
		ArrayList<Faq> articleList=new ArrayList<Faq>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		FaqListService faqListService = new FaqListService();
		int listCount=faqListService.getListCount();
		articleList = faqListService.getArticleList(page,limit);
   		int maxPage=(int)((double)listCount/limit+0.95);
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		ActionForward forward= new ActionForward();
		
	   	if (m_id == null) {
				forward.setPath("/WEB-INF/faq/faqList.jsp");
				}
		else if (m_id.equals("admin")) {
			forward.setPath("/WEB-INF/adminFaq/adminFaqList.jsp");
		} else {
			forward.setPath("/WEB-INF/faq/faqList.jsp");
		}
		 return forward;
		
	 }
	}
		
		
