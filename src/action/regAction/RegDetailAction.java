package action.regAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.regService.RegDetailService;
import vo.ActionForward;
import vo.RegBean;

 public class RegDetailAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		int reg_num=Integer.parseInt(request.getParameter("reg_num"));
		String page = request.getParameter("page");
		RegDetailService regDetailService = new RegDetailService();
		RegBean article = regDetailService.getArticle(reg_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
   		forward.setPath("/WEB-INF/reg_board/reg_board_view.jsp");
   		return forward;
	 }
}