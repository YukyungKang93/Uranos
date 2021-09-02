package action.regAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.regService.RegModifyProService;
import vo.ActionForward;
import vo.RegBean;

public class RegModifyProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		boolean isModifySuccess = false;
		int reg_num = Integer.parseInt(request.getParameter("reg_num"));
		RegBean article = new RegBean();
		RegModifyProService regModifyProService = new RegModifyProService();
		boolean isRightUser = regModifyProService.isArticleWriter(reg_num);

		article.setReg_num(reg_num);
		article.setCategory(request.getParameter("category"));
		article.setP_name(request.getParameter("p_name"));
		article.setReg_content(request.getParameter("reg_content"));
		article.setImage(request.getParameter("image"));
		isModifySuccess = regModifyProService.modifyArticle(article);

		if (!isModifySuccess) {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정실패');");
			out.println("history.back()");
			out.println("</script>");
			
		} else {
			
			System.out.println(article.getReg_num());
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('수정완료');");
            out.println("location.href='regDetail.do?reg_num=" + article.getReg_num() + "';"); 
            out.println("</script>");
		
		}
		return forward;
	}
}