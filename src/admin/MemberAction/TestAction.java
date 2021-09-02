package admin.MemberAction;

import static util.JdbcUtil.getConnection;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import dao.MemberDAO;
import vo.ActionForward;
import vo.MemberBean;
import vo.MemberPageInfo;

public class TestAction implements Action{
   public ActionForward execute(HttpServletRequest request,
         HttpServletResponse response) throws Exception{
      
      HttpSession session = request.getSession(true);
      
      MemberBean memberBean = new MemberBean();
      
      String loginId = (String)session.getAttribute("M_ID");
      String loginNAME = (String)session.getAttribute("M_NAME");
   
      int currentPage = 1;
      
      if(request.getParameter("page")!=null) {
         System.out.println("테스트액션 페이지 테스트1 : " + request.getParameter("page"));
         currentPage = Integer.parseInt(request.getParameter("page"));
         
      }if(request.getParameter("page")==null) {
          System.out.println("테스트액션 페이지 테스트1 : " + request.getParameter("page"));
          currentPage = 1;
          
       }
      
      System.out.println("테스트액션 페이지 테스트2 : " + request.getParameter("page"));
      
      memberBean.setCurrent_page(currentPage);
      
      System.out.println("테스트액션 멤버빈페이지 테스트1 : " + memberBean.getCurrent_page());
      
      ActionForward forward = null;
      Connection con = getConnection();
      
      MemberDAO uranosDAO = MemberDAO.getInstance();
      uranosDAO.setConnection(con);
      
      if(memberBean.get_search() == null || memberBean.get_value() == null) {
	     memberBean.set_search("");
         memberBean.set_value("%%");
      }
      
      if(session.getAttribute("data") == null) {
         
         memberBean.setArrange("desc");
         memberBean.setOrder_By("regdate");
         
         memberBean = uranosDAO.searchAllData(memberBean);
         
         System.out.println("테스트 액션 count1 : " + memberBean.getCount());
         
         MemberPageInfo listPage = new MemberPageInfo(memberBean.getCount(), currentPage);
         
         session.invalidate();
         HttpSession session1 = request.getSession(true);
         session1.setAttribute("M_NAME", loginNAME);
         session1.setAttribute("M_ID", loginId);         
         session1.setAttribute("data", memberBean);
         session1.setAttribute("list", listPage);
         
         response.sendRedirect("test.do");
      }else {
         
         
         if(request.getParameter("value")==null) {
            memberBean.set_value("");
         }else {
            memberBean.set_value(request.getParameter("value"));
         }
         
         memberBean.setArrange("desc");   
         memberBean.setOrder_By("regdate");
         if(request.getParameter("type") == "ALL" || request.getParameter("type") == "" || request.getParameter("type") == null) {
            memberBean =uranosDAO.searchAllData(memberBean);         
         }else {
            memberBean.set_search(request.getParameter("type"));
            memberBean =uranosDAO.searchPartData(memberBean);
         }
         
         System.out.println("테스트 액션 count2 : " + memberBean.getCount());
         
         MemberPageInfo listPage = new MemberPageInfo(memberBean.getCount(), currentPage);
         
         System.out.println("테스트액션 listPage : " + listPage.getCurrent_page());
         
         session.invalidate();
         HttpSession session1 = request.getSession(true);
         session1.setAttribute("M_NAME", loginNAME);
         session1.setAttribute("M_ID", loginId);
         session1.setAttribute("data", memberBean);
         session1.setAttribute("list", listPage);
         System.out.println("테스트 액션 페이지 : " + listPage.getCurrent_page());
         
         response.sendRedirect("test.do");
      }   
              
         
      return forward;
   }
}