package admin.NoticeAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adminNoticeService.NoticeWriteProService;
import vo.ActionForward;
import vo.Notice;

public class NoticeWriteProAction implements Action {
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Notice notice = null;
		notice = new Notice();
		notice.setN_title(request.getParameter("n_title"));
		String content=request.getParameter("n_content");
		content = content.replace("\r\n","<br>");
		notice.setN_content(content);
		NoticeWriteProService noticeWriteProService = new NoticeWriteProService();
		boolean isWriteSuccess = noticeWriteProService.registNotice(notice);
		
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
			forward.setPath("noticeList.do");
		}
		
		return forward;
	}
}