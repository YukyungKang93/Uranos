package admin.faqAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adminFaqService.FaqModifyProService;
import vo.ActionForward;
import vo.Faq;

public class FaqModifyProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		boolean isModifySuccess = false;

		int f_num = Integer.parseInt(request.getParameter("f_num"));
		Faq faq = new Faq();
		FaqModifyProService faqModifyProService = new FaqModifyProService();

		faq.setF_num(f_num);
		faq.setF_writer(request.getParameter("f_writer"));
		faq.setF_title(request.getParameter("f_title"));
		String content=request.getParameter("f_content");
		content = content.replace("\r\n","<br>");
		faq.setF_content(content);
		faq.setF_image(request.getParameter("f_image"));
		isModifySuccess = faqModifyProService.modifyFaq(faq);

		if (!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패');");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("faqList.do?f_num=" + faq.getF_num());
		}
		return forward;
	}

}