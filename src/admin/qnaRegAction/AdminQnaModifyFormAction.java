package admin.qnaRegAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qnaService.QnaDetailService;
import vo.ActionForward;
import vo.QnaBean;

public class AdminQnaModifyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	ActionForward forward = new ActionForward();
			int qna_num=Integer.parseInt(request.getParameter("qna_num"));
			QnaDetailService qnaDetailService = new QnaDetailService();	
		   	QnaBean article =qnaDetailService.getArticle(qna_num);
		   	request.setAttribute("article", article);
	   		forward.setPath("/WEB-INF/adminQna/adminQnaModify.jsp");
	   		return forward;
	 }
}