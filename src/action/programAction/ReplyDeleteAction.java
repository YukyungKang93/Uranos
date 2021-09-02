package action.programAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.programService.DeleteReplyProService;
import vo.ActionForward;
import vo.P_replyBean;
import vo.ProgramBean;

public class ReplyDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{	 

		System.out.println("ReplyDeleteAction - 1");
		ActionForward forward = null;
	int reply_num=Integer.parseInt(request.getParameter("reply_num"));
	int group_num=Integer.parseInt(request.getParameter("group_num"));
	System.out.println("ReplyDeleteAction - Group_num : "+ group_num);
	int p_num=Integer.parseInt(request.getParameter("p_num"));
	System.out.println("ReplyDeleteAction - p_num : " + p_num);
	String nowPage = request.getParameter("page");
	DeleteReplyProService DeleteProService = new DeleteReplyProService();
	
	boolean isArticleWriter =DeleteProService.isArticleWriter(reply_num, request.getParameter("m_id"));
	   ProgramBean program = (ProgramBean) request.getAttribute("program");
	   P_replyBean reply = (P_replyBean) request.getAttribute("reply");
if(!isArticleWriter){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('삭제할 권한이 없습니다');");
		out.println("history.back();");
		out.println("</script>");
		out.close();
	} else {

		boolean replycountSuccess =  DeleteProService.replyArticle(group_num);
		
		if (!replycountSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글이 있는건 삭제 불가');");
			out.println("history.back();");
			out.println("</script>");
			out.close();



		} else {
		boolean isDeleteSuccess = DeleteProService.removeArticle(reply_num);
		if(!isDeleteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		else{
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('삭제성공');");
            out.println("location.href='/programDetail.do?p_num=" + p_num + "';"); 
            out.println("</script>");

		}}
}
	return forward;
}
}