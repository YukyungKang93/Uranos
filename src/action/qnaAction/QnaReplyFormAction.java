package action.qnaAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qnaService.QnaDetailService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaReplyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 
		 	ActionForward forward = new ActionForward();
	   		String nowPage = request.getParameter("page");
	   		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
	   		QnaDetailService qnaDetailService = new QnaDetailService();
	   		QnaBean article=qnaDetailService.getArticle(qna_num);	
	   		request.setAttribute("article", article);
	   		request.setAttribute("page", nowPage);
	   		forward.setPath("/WEB-INF/qna_board/qna_board_reply.jsp");
	   		return forward;
	   		
	}
	 
}