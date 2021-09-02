package admin.ProgramAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adminProgramService.ProgramSearchService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProgramBean;

public class ProgramSearchAction implements Action{
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 
		
		ArrayList<ProgramBean> programAdminList=new ArrayList<ProgramBean>();
	  	int page=1;
		int limit=6;		
		String category = request.getParameter("category");
		String item = request.getParameter("item");
		String searchText = request.getParameter("searchText");
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}	
		
		ProgramSearchService programSearchService = new ProgramSearchService();
		//바로 검색버튼 클릭
		 if (category.equals("categoryAll") && item.equals("itemAll") &&searchText.equals("")){
			
			int listCount=programSearchService.getListCount(); 
			programAdminList = programSearchService.getProgramAdminList(page,limit); 
			
	   		int maxPage=(int)((double)listCount/limit+0.95);
	   	
	   		int startPage = (((int) ((double)page / 5 + 0.9)) - 1) * 5 + 1;
	   		
	   	        int endPage = startPage+5-1;

	   		if (endPage> maxPage) endPage= maxPage;

	   		PageInfo pageInfo = new PageInfo();
	   		pageInfo.setEndPage(endPage);
	   		pageInfo.setListCount(listCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);	
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("programAdminList", programAdminList);
			ActionForward forward= new ActionForward();
	   		forward.setPath("/WEB-INF/adminProgram/programAdminList.jsp");
	   		return forward; 		
		 } //바로 검색어만 입력하고 검색버튼 클릭
			else if (item.equals("itemAll") && category.equals("categoryAll") && searchText != null) { 
				int listCount=programSearchService.getAllProgramSearchListCount(searchText);
				programAdminList = programSearchService.getAllProgramSearchList(page,limit,searchText);
						
			int maxPage=(int)((double)listCount/limit+0.95);	
	  		int startPage = (((int) ((double)page / 5 + 0.9)) - 1) * 5 + 1;		
	  	        int endPage = startPage+5-1;
	  		if (endPage> maxPage) endPage= maxPage;
	  		PageInfo pageInfo = new PageInfo();
	  		pageInfo.setEndPage(endPage);
	  		pageInfo.setListCount(listCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);	
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("programAdminList", programAdminList);		
			ActionForward forward= new ActionForward();
			forward.setPath("/WEB-INF/adminProgram/programAdminList.jsp");	
			return forward; 
			}
		//카테고리에서 체크안하고 item만 선택후 검색	
		 else if (category.equals("categoryAll")) { 		
			int listCount=programSearchService.getCategoryAllSearchListCount(item,searchText);
			programAdminList = programSearchService.getCategoryAllSearchList(page,limit,item,searchText);
					
		int maxPage=(int)((double)listCount/limit+0.95);	
  		int startPage = (((int) ((double)page / 5 + 0.9)) - 1) * 5 + 1;		
  	        int endPage = startPage+5-1;
  		if (endPage> maxPage) endPage= maxPage;
  		PageInfo pageInfo = new PageInfo();
  		pageInfo.setEndPage(endPage);
  		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("programAdminList", programAdminList);
		
		ActionForward forward= new ActionForward();
		forward.setPath("/WEB-INF/adminProgram/programAdminSearchList.jsp");
		return forward; 
		
		//카테고리만 선택하고 검색어 입력
		} else if (item.equals("itemAll")) { 
			int listCount=programSearchService.getCategorySearchListCount(category);
			programAdminList = programSearchService.getCategorySearchList(page,limit,category);
					
		int maxPage=(int)((double)listCount/limit+0.95);	
  		int startPage = (((int) ((double)page / 5 + 0.9)) - 1) * 5 + 1;		
  	        int endPage = startPage+5-1;
  		if (endPage> maxPage) endPage= maxPage;
  		PageInfo pageInfo = new PageInfo();
  		pageInfo.setEndPage(endPage);
  		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("programAdminList", programAdminList);		
		ActionForward forward= new ActionForward();
		forward.setPath("/WEB-INF/adminProgram/programAdminSearchList.jsp");	
		return forward; 
		} 
		 else {			 
			//카테고리,item 선택하고 검색
			int listCount=programSearchService.getSearchListCount(category,item,searchText); 
			programAdminList = programSearchService.getProgramSearchList(page,limit,category,item,searchText); 	
			int maxPage=(int)((double)listCount/limit+0.95);	
	  		int startPage = (((int) ((double)page / 5 + 0.9)) - 1) * 5 + 1;		
	  	        int endPage = startPage+5-1;
	  		if (endPage> maxPage) endPage= maxPage;
	  		PageInfo pageInfo = new PageInfo();
	  		pageInfo.setEndPage(endPage);
	  		pageInfo.setListCount(listCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);	
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("programAdminList", programAdminList);		
			ActionForward forward= new ActionForward();
			forward.setPath("/WEB-INF/adminProgram/programAdminSearchList.jsp");
			return forward; 
			} 
  }
}
									
		
		
		
		
		
		
		
		
		
		
