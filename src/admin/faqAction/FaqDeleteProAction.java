package admin.faqAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adminFaqService.FaqDeleteProService;
import vo.ActionForward;

public class FaqDeleteProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 

		ActionForward forward = null;
		int f_num=Integer.parseInt(request.getParameter("f_num"));
		String nowPage = request.getParameter("page");
		FaqDeleteProService faqDeleteProService = new FaqDeleteProService();
		boolean isDeleteSuccess = faqDeleteProService.removeArticle(f_num);

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
				forward.setPath("faqList.do?page=" + nowPage);
			}
	
		return forward;
	}

}
