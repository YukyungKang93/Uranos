package admin.ProgramAction;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.adminProgramService.ProgramModifyService;
import vo.ActionForward;
import vo.ProgramBean;

public class ProgramModifyProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		ActionForward forward = null;
		ProgramBean program = null;
		boolean isModifySuccess = false;
		String realFolder = "D:/Uranos/Uranos/WebContent/upload";
		//String saveFolder = "/upload";
//		ServletContext context = request.getServletContext();
//		realFolder = context.getRealPath(saveFolder);
		int fileSize = 5 * 1024 * 1024;
		MultipartRequest multi = 
				new MultipartRequest(request, realFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());
		program = new ProgramBean();
		program.setP_num(Integer.parseInt(multi.getParameter("p_num")));
		program.setP_name(multi.getParameter("p_name"));
		program.setCategory(multi.getParameter("category"));
		String content=multi.getParameter("content");
		content = content.replace("\r\n","<br>");
		program.setContent(content);
		program.setImage(multi.getOriginalFileName(
				(String) multi.getFileNames().nextElement()));
		program.setStartdate(multi.getParameter("startdate"));
		program.setCount(Integer.parseInt(multi.getParameter("count")));
		program.setTotal_number(Integer.parseInt(multi.getParameter("total_number")));
		ProgramModifyService programModifyService = new ProgramModifyService();				
		isModifySuccess = programModifyService.modifyProgram(program);		
											

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("programDetail.do?p_num="+program.getP_num()); 
			}
		return forward;
	}

}