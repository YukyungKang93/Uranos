package action.programAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.programService.ProgramDetailService;
import svc.reviewService.ReviewListService;
import vo.ActionForward;
import vo.P_replyBean;
import vo.PageInfo;
import vo.ProgramBean;
import vo.ReviewBean;

public class ProgramDetailAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);

		String m_id = (String) session.getAttribute("M_ID");

		int p_num = Integer.parseInt(request.getParameter("p_num"));
		ProgramDetailService programDetailService = new ProgramDetailService();
		
		ProgramBean program = ProgramDetailService.getProgram(p_num);
		request.setAttribute("program", program);
		request.setAttribute("p_num", p_num);

		ArrayList<P_replyBean> replyList = new ArrayList<P_replyBean>();
		int page = 1;
		int limit = 5;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int listCount = ProgramDetailService.getListCount(p_num); // 총 리스트 수를 받아옴.
		replyList = ProgramDetailService.getReplyList(p_num, page, limit); // 리스트를 받아옴.
		// 총 페이지 수.
		int maxPage = (int) ((double) listCount / limit + 0.95); // 0.95를 더해서 올림 처리.
		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startPage = (((int) ((double) page / 5 + 0.9)) - 1) * 5 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
		int endPage = startPage + 5 - 1;

		ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
		ReviewListService reviewListService = new ReviewListService();
		reviewList = reviewListService.getDetailReviewList(); // 리스트를 받아옴.
		
		ProgramBean programCount = programDetailService.getProgram(p_num);

		if (endPage > maxPage)
			endPage = maxPage;

		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("replyList", replyList);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("programCount", programCount);
		request.setAttribute("program", program);

		ActionForward forward = new ActionForward();
		if (m_id == null) {
			forward.setPath("/WEB-INF/program/programDetail.jsp");
		} else if (m_id.equals("admin")) {
			forward.setPath("/WEB-INF/adminProgram/programAdminDetail.jsp");
		} else {
			forward.setPath("/WEB-INF/program/programDetail.jsp");
		}
		return forward;
	}
}