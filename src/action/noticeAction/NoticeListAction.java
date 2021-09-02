package action.noticeAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.noticeService.NoticeListService;
import vo.ActionForward;
import vo.Notice;
import vo.PageInfo;

 public class NoticeListAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 HttpSession session = request.getSession(true);
			String m_id= 
					(String) session.getAttribute("M_ID");
		ArrayList<Notice> articleList=new ArrayList<Notice>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		NoticeListService noticeListService = new NoticeListService();
		int listCount=noticeListService.getListCount();
		articleList = noticeListService.getArticleList(page,limit);
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
			forward.setPath("/WEB-INF/notice/noticeList.jsp");
			}
	else if (m_id.equals("admin")) {
		forward.setPath("/WEB-INF/adminNotice/adminNoticeList.jsp");
	} else {
		forward.setPath("/WEB-INF/notice/noticeList.jsp");
	}
	 return forward;
	
 }
}