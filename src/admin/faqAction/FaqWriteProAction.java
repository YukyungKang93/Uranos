package admin.faqAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.adminFaqService.FaqWriteProService;
import vo.ActionForward;
import vo.Faq;

public class FaqWriteProAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Faq faq = null;
		String realFolder = "D:/Uranos/Uranos/WebContent/upload";
		int fileSize = 5 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());
		faq = new Faq();
		faq.setF_title(multi.getParameter("f_title"));
		String content=multi.getParameter("f_content");
		content = content.replace("\r\n","<br>");
		faq.setF_content(content);
		faq.setF_image(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		FaqWriteProService faqWriteProService = new FaqWriteProService();
		boolean isWriteSuccess = faqWriteProService.registFaq(faq);

		if (!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록 실패');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("faqList.do");
		}

		return forward;
	}
}