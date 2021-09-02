package admin.qnaRegAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adminRegService.AdminRegConfirmProService;
import svc.adminRegService.AdminRegConfirmService;
import svc.adminRegService.AdminRegConfirmcheckService;
import vo.ActionForward;
import vo.RegBean;

public class AdminRegConfirmProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		boolean isModifySuccess = false;
		boolean check = false;
		int reg_num = Integer.parseInt(request.getParameter("reg_num"));
		RegBean article = new RegBean();
		AdminRegConfirmService adminConfirmProService = new AdminRegConfirmService();
		AdminRegConfirmProService adminRegConfirmProService = new AdminRegConfirmProService();
		AdminRegConfirmcheckService adminRegConfirmcheckService = new AdminRegConfirmcheckService();

		article.setReg_num(reg_num);
		article.setCategory(request.getParameter("category"));
		article.setM_id(request.getParameter("m_id"));
		article.setP_name(request.getParameter("p_name"));
		article.setStartdate(request.getParameter("startdate"));
		article.setP_name(request.getParameter("p_name"));
		article.setImage(request.getParameter("image"));
		article.setReg_content(request.getParameter("reg_content"));
		article.setTotal_number(Integer.parseInt(request.getParameter("total_number")));
		check = adminRegConfirmcheckService.checkArticle(reg_num);
		isModifySuccess = adminConfirmProService.programArticle(article);
		isModifySuccess = adminRegConfirmProService.ConfirmArticle(reg_num);

		if (!check) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('승인은 한번만 가능');");
			out.println("location.href='/regList.do'");
			out.println("</script>");
		} else {
			
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('승인완료');");
            out.println("location.href='/regDetail.do?reg_num=" + article.getReg_num() + "';"); 
            out.println("</script>");
		}
		return forward;
	}
}