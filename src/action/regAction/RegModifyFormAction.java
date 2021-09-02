package action.regAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.regService.RegDetailService;
import vo.ActionForward;
import vo.RegBean;

public class RegModifyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	ActionForward forward = new ActionForward();
			int reg_num=Integer.parseInt(request.getParameter("reg_num"));
			RegDetailService regDetailService
			= new RegDetailService();	
		   	RegBean article =regDetailService.getArticle(reg_num);
		   	request.setAttribute("article", article);
	   		forward.setPath("/WEB-INF/reg_board/reg_board_modify.jsp");
	   		return forward;
	 }
}