package admin.faqAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.faqService.FaqDetailService;
import vo.ActionForward;
import vo.Faq;

public class FaqModifyFormAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		int f_num = Integer.parseInt(request.getParameter("f_num"));
		FaqDetailService faqDetailService = new FaqDetailService();
		Faq article = faqDetailService.getArticle(f_num);
		request.setAttribute("article", article);
		forward.setPath("WEB-INF/adminFaq/adminFaqModifyForm.jsp");
		return forward;

	}

}