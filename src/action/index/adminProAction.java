package action.index;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.index.RegisterService;
import svc.index.IndexReservationService;
import vo.ActionForward;
import vo.PageInfo;
import vo.RegBean;
import vo.Reservation;

 public class adminProAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		ArrayList<Reservation> adminList=new ArrayList<Reservation>();
		ArrayList<RegBean> regList =new ArrayList<RegBean>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		IndexReservationService reservationService = new IndexReservationService();
		RegisterService registerService = new RegisterService();
		adminList = reservationService.selectReservationList(page,limit); //리스트를 받아옴.
		regList = registerService.selectRegisterList(page,limit);
		//총 페이지 수.
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
   	        int endPage = startPage+10-1;


   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setPage(page);
   		pageInfo.setStartPage(startPage);	
   		pageInfo.setEndPage(endPage);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("adminList", adminList);
		request.setAttribute("regList", regList);
		ActionForward forward= new ActionForward();
   		forward.setPath("/indexAdmin.jsp");
   		return forward;
   		
	 }
	 
 }