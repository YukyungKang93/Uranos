package action.regAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.regService.RegListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.RegBean;

 public class RegListAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		ArrayList<RegBean> articleList=new ArrayList<RegBean>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		RegListService regListService = new RegListService();
		int listCount = regListService.getListCount(); //총 리스트 수를 받아옴.
		articleList = regListService.getArticleList(page,limit); //리스트를 받아옴.
		//총 페이지 수.
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95를 더해서 올림 처리.
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setPage(page);
   		pageInfo.setMaxPage(maxPage);
   		pageInfo.setStartPage(startPage);	
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		ActionForward forward= new ActionForward();
   		forward.setPath("/WEB-INF/reg_board/reg_board_list.jsp");
   		return forward;
   		
	 }
	 
 }