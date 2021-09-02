package admin.qnaRegAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qnaService.QnaReplyProService;
import vo.ActionForward;
import vo.Q_replyBean;

public class QnaReplyProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		Q_replyBean article = new Q_replyBean();
		article.setQna_num(Integer.parseInt(request.getParameter("qna_num")));
		article.setM_id(request.getParameter("m_id"));
		String content = request.getParameter("content");
		content = content.replace("\r\n", "<br>");
		article.setContent(content);
		QnaReplyProService qnaReplyProService = new QnaReplyProService();
		qnaReplyProService.replyArticle(article);

		response.setContentType("text/html;charset=UTF-8");
		forward.setPath("/qnaDetail.do");
		return forward;

	}

}