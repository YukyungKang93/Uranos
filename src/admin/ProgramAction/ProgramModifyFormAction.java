package admin.ProgramAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.programService.ProgramDetailService;
import vo.ActionForward;
import vo.ProgramBean;

public class ProgramModifyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{	 
		 	ActionForward forward = new ActionForward();
			int p_num=Integer.parseInt(request.getParameter("p_num"));
			ProgramDetailService programDetailService
			= new ProgramDetailService();	
		   	ProgramBean program =programDetailService.getProgram(p_num);
		   	request.setAttribute("program", program);		   	
	   		forward.setPath("/WEB-INF/adminProgram/programModify.jsp");
	   		return forward;
	   		
	 }
	 
}