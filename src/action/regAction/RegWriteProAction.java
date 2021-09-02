package action.regAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.regService.RegWriteProService;
import vo.ActionForward;
import vo.RegBean;

public class RegWriteProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		RegBean regBean = null;
		String realFolder = "D:/Uranos/uranos/WebContent/upload";
		// String saveFolder = "/upload";
		int fileSize = 5 * 1024 * 1024;
//		ServletContext context = request.getServletContext();
//		realFolder = context.getRealPath(saveFolder);	
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());
		regBean = new RegBean();
		regBean.setCategory(multi.getParameter("category"));
		regBean.setP_name(multi.getParameter("p_name"));
		regBean.setStartdate(multi.getParameter("startdate"));
		regBean.setM_id(multi.getParameter("m_id"));
		regBean.setM_name(multi.getParameter("m_name"));
		regBean.setImage(multi.getOriginalFileName("image"));
		String content=multi.getParameter("reg_content");
		content = content.replace("\r\n","<br>");
		regBean.setReg_content(content);
		regBean.setTotal_number(Integer.parseInt(multi.getParameter("total_number")));
		RegWriteProService regWriteProService = new RegWriteProService();
		boolean isWriteSuccess = regWriteProService.registArticle(regBean);

		if (!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('신청실패')");
			out.println("history.back();");
			out.println("</script>");
			
		} else {
			
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('신청완료');");
            out.println("location.href='MemberModifyForm.do?myApply=';"); 
            out.println("</script>");
		}

		return forward;

	}

}