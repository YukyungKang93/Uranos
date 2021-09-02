package admin.ProgramAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adminProgramService.ProgramDeleteProService;
import vo.ActionForward;

public class ProgramDeleteProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 

		ActionForward forward = null;
		int p_num=Integer.parseInt(request.getParameter("p_num"));

		ProgramDeleteProService boardDeleteProService = new ProgramDeleteProService();
		boolean isDeleteSuccess = boardDeleteProService.removeArticle(p_num);

			if(!isDeleteSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("programList.do");
			}
		return forward;
	}

}