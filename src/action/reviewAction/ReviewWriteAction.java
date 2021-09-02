package action.reviewAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.reviewService.ReviewWriteService;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewWriteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		ReviewBean reviewBean = null;
		String realFolder = "D:/Uranos/uranos/WebContent/upload";
		//String saveFolder = "/upload";
		int fileSize = 5 * 1024 * 1024;
//		ServletContext context = request.getServletContext();
//		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());
		reviewBean = new ReviewBean();
		reviewBean.setM_id(multi.getParameter("M_id"));
		reviewBean.setP_name(multi.getParameter("P_name"));
		reviewBean.setTitle(multi.getParameter("Title"));
		String content=multi.getParameter("Rev_content");
		content = content.replace("\r\n","<br>");
		reviewBean.setRev_content(content);
		reviewBean.setScore(multi.getParameter("Score"));
		reviewBean.setImage(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		ReviewWriteService reviewWriteService = new ReviewWriteService();
		boolean isWriteSuccess = reviewWriteService.registArticle(reviewBean);
		
		if (!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록완료')");
			out.println("location.href='reviewList.do';");
			out.println("</script>");
		}

		return forward;

	}
}
