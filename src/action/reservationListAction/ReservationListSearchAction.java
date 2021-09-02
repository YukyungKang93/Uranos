package action.reservationListAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adminReservationListService.ReservationSearchService;
import vo.ActionForward;
import vo.PageInfo;
import vo.Reservation;

public class ReservationListSearchAction implements Action{
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 
		
		ArrayList<Reservation> reservationSearchList=new ArrayList<Reservation>();
	  	int page=1;
		int limit=6;		
		String item = request.getParameter("item");
		String searchText = request.getParameter("searchText");
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}	
		
		ReservationSearchService reservationSearchService = new ReservationSearchService();
		//바로 검색버튼 클릭   전체보기 효과
		 if (item.equals("itemAll") &&searchText.equals(null)){
			
			int listCount=reservationSearchService.getListCount(); 
			reservationSearchList = reservationSearchService.getReservationList(page,limit); 
			
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
			request.setAttribute("reservationSearchList", reservationSearchList);
			ActionForward forward= new ActionForward();
	   		forward.setPath("/WEB-INF/adminReservation/reservationAdminList.jsp");
	   		return forward; 		
		 } //바로 검색어만 입력하고 검색버튼 클릭
			else if (item.equals("itemAll") && searchText != null) { 
				int listCount=reservationSearchService.getAllReservationSearchListCount(searchText);
				reservationSearchList = reservationSearchService.getAllReservationSearchList(page,limit,searchText);
						
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
			request.setAttribute("reservationSearchList", reservationSearchList);		
			ActionForward forward= new ActionForward();
			forward.setPath("/WEB-INF/adminReservation/reservationAdminSearchList.jsp");	
			return forward; 
			}
		 else {			 
			//item 선택하고 검색
			int listCount=reservationSearchService.getItemSearchListCount(item,searchText); 
			reservationSearchList = reservationSearchService.getItemSearchList(page,limit,item,searchText); 	
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
			request.setAttribute("reservationSearchList", reservationSearchList);		
			ActionForward forward= new ActionForward();
			forward.setPath("/WEB-INF/adminReservation/reservationAdminSearchList.jsp");
			return forward; 
			} 
  }
}
									
		
		
		
		
		
		
		
		
		
		
