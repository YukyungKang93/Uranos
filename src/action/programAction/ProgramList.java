package action.programAction;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.programService.ProgramListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProgramBean;


public class ProgramList implements Action{
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 
		
		ArrayList<ProgramBean> programList=new ArrayList<ProgramBean>();
	  	int page=1;
		int limit=6;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		ProgramListService programListService = new ProgramListService();

		int listCount=programListService.getListCount(); 
		programList = programListService.getProgramList(page,limit); 
		
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
		request.setAttribute("programList", programList);
		ActionForward forward= new ActionForward();
   		forward.setPath("/WEB-INF/program/programList.jsp");
   		return forward;
   		
	 }
	 
 }