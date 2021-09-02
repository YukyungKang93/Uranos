package action.qnaAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qnaService.QnaDetailService;
import vo.ActionForward;
import vo.QnaBean;

 public class QnaDetailAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		String page = request.getParameter("page");
		QnaDetailService boardDetailService = new QnaDetailService();
		QnaBean article = boardDetailService.getArticle(qna_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
   		forward.setPath("/WEB-INF/qna_board/qna_board_view.jsp");
   		return forward;
	 }
}