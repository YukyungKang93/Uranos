package admin.NoticeAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adminNoticeService.NoticeModifyProService;
import vo.ActionForward;
import vo.Notice;

public class NoticeModifyProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		boolean isModifySuccess = false;

		int n_num = Integer.parseInt(request.getParameter("n_num"));
		System.out.println(n_num);
		Notice notice = new Notice();
		NoticeModifyProService noticeModifyProService = new NoticeModifyProService();
		notice.setN_num(n_num);
		notice.setN_writer(request.getParameter("n_writer"));
		notice.setN_title(request.getParameter("n_title"));
		String content=request.getParameter("n_content");
		content = content.replace("\r\n","<br>");
		notice.setN_content(content);
		isModifySuccess = noticeModifyProService.modifyNotice(notice);

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
			forward.setPath("noticeList.do?f_num=" + notice.getN_num());
		}
		return forward;
	}

}