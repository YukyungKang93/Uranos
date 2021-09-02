package action.qnaAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.qnaService.QnaWriteProService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaWriteProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		ActionForward forward=null;
		QnaBean qnaBean = null;
		String realFolder = "D:/Uranos/uranos/WebContent/upload";
		//String saveFolder = "/upload";
		int fileSize = 5 * 1024 * 1024;
//		ServletContext context = request.getServletContext();
//		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi=new MultipartRequest(request, realFolder, fileSize, "UTF-8", 
				new DefaultFileRenamePolicy());
		qnaBean = new QnaBean();
		qnaBean.setTitle(multi.getParameter("title"));
		qnaBean.setM_id(multi.getParameter("m_id"));
		qnaBean.setM_pw(multi.getParameter("m_pw"));
		String content=multi.getParameter("qna_content");
		content = content.replace("\r\n","<br>");
		qnaBean.setQna_content(content);
		QnaWriteProService qnaWriteProService = new QnaWriteProService();
		boolean isWriteSuccess = qnaWriteProService.registArticle(qnaBean);
		
		if(!isWriteSuccess){
			PrintWriter out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
			
		} else {
			
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('등록성공');");
            out.println("location.href='/qnaList.do';"); 
            out.println("</script>");
			
		}
		return forward;
		
	}  	
	
}