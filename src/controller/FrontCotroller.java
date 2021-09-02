package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.faqAction.FaqDetailAction;
import action.faqAction.FaqListAction;
import action.index.adminProAction;
import action.memberAction.EMAILIdSearchAction;
import action.memberAction.LoginAction;
import action.memberAction.LogoutAction;
import action.memberAction.MemberDeleteAction;
import action.memberAction.MemberInfoDetailAction;
import action.memberAction.MemberModifyFormAction;
import action.memberAction.PHONEIdSearchAction;
import action.memberAction.PasswordSearchAction;
import action.memberAction.SignUpAction;
import action.noticeAction.NoticeDetailAction;
import action.noticeAction.NoticeListAction;
import action.programAction.CastleList;
import action.programAction.HikingList;
import action.programAction.PetTourList;
import action.programAction.ProgramDetailAction;
import action.programAction.ProgramList;
import action.programAction.Re_replyFormAction;
import action.programAction.Re_replyProAction;
import action.programAction.ReplyDeleteAction;
import action.programAction.ReplyModifyFormAction;
import action.programAction.ReplyModifyProAction;
import action.programAction.ReplyProAction;
import action.programAction.ReservationAction;
import action.programAction.RiddingList;
import action.qnaAction.QnaDeleteProAction;
import action.qnaAction.QnaDetailAction;
import action.qnaAction.QnaListAction;
import action.qnaAction.QnaModifyFormAction;
import action.qnaAction.QnaModifyProAction;
import action.qnaAction.QnaReplyFormAction;
import action.qnaAction.QnaViewProAction;
import action.qnaAction.QnaWriteProAction;
import action.regAction.RegDetailAction;
import action.regAction.RegListAction;
import action.regAction.RegModifyFormAction;
import action.regAction.RegModifyProAction;
import action.regAction.RegWriteProAction;
import action.reservationListAction.ReservationListAction;
import action.reservationListAction.ReservationListSearchAction;
import action.reviewAction.ReviewDeleteAction;
import action.reviewAction.ReviewDetailAction;
import action.reviewAction.ReviewListAction;
import action.reviewAction.ReviewModifyAction;
import action.reviewAction.ReviewModifyFormAction;
import action.reviewAction.ReviewWriteAction;
import admin.MemberAction.AdminClientModifyAction;
import admin.MemberAction.AdminMemberInfoDetailAction;
import admin.MemberAction.TestAction;
import admin.NoticeAction.NoticeDeleteProAction;
import admin.NoticeAction.NoticeModifyFormAction;
import admin.NoticeAction.NoticeModifyProAction;
import admin.NoticeAction.NoticeWriteProAction;
import admin.ProgramAction.ProgramAdminList;
import admin.ProgramAction.ProgramDeleteProAction;
import admin.ProgramAction.ProgramModifyFormAction;
import admin.ProgramAction.ProgramModifyProAction;
import admin.ProgramAction.ProgramSearchAction;
import admin.ReviewAction.AdminReviewDeleteAction;
import admin.ReviewAction.AdminReviewDeleteFormAction;
import admin.faqAction.FaqDeleteProAction;
import admin.faqAction.FaqModifyFormAction;
import admin.faqAction.FaqModifyProAction;
import admin.faqAction.FaqWriteProAction;
import admin.qnaRegAction.AdminQnaModifyFormAction;
import admin.qnaRegAction.AdminRegConfirmProAction;
import admin.qnaRegAction.QnaReplyProAction;
import util.FlexibleURL;
import vo.ActionForward;

@WebServlet("*.do")
public class FrontCotroller extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;

		// Member
		if (command.equals("/member/login.do")) {

			forward = new ActionForward();
			forward.setPath("/WEB-INF/member/login.jsp");

		} else if (command.equals("/adminindexpro.do")) {
			action = new adminProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/logout.do")) {
			forward = new ActionForward();
			forward.setPath("/WEB-INF/member/logout.jsp");
		}

		else if (command.equals("/member/ID.do")) {
			forward = new ActionForward();
			forward.setPath("/WEB-INF/member/ID.jsp");
		}

		else if (command.equals("/member/PASSWORD.do")) {
			forward = new ActionForward();
			forward.setPath("/WEB-INF/member/PASSWORD.jsp");
		} else if (command.equals("/signup.do")) {

			forward = new ActionForward();
			forward.setPath("/WEB-INF/member/signup.jsp");
		} else if (command.equals("/test.do")) {
			forward = new ActionForward();
			forward.setPath("/WEB-INF/adminMember/test.jsp");
		} else if (command.equals("/testaction.do")) {

			action = new TestAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/managerpass.do")) {

			forward = new ActionForward();
			forward.setPath("/WEB-INF/adminMember/managerPass.jsp");
		} else if (command.equals("/MemberModifyForm.do")) {
			action = new MemberInfoDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/signupaction.do")) {

			action = new SignUpAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/member/loginaction.do")) {
			System.out.println("경로 테스트");
			action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/member/emailId.do")) {
			System.out.println("중간경로");
			action = new EMAILIdSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/member/phoneId.do")) {
			System.out.println("중간경로");
			action = new PHONEIdSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/ModifyService.do")) {
			action = new MemberModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/logoutaction.do")) {
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/delete.do")) {
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/passwordsearch.do")) {
			action = new PasswordSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 프로그램
		} else if (command.equals("/programList.do")) {
			action = new ProgramList();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/programDetail.do")) {
			action = new ProgramDetailAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/hikingList.do")) {
			action = new HikingList();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/castleList.do")) {
			action = new CastleList();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/riddingList.do")) {
			action = new RiddingList();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/petTourList.do")) {
			action = new PetTourList();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		//프로그램 댓글
		} else if (command.equals("/replyForm.do"))

		{

			action = new ReplyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/replyDelete.do")) {
			action = new ReplyDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/replyModify.do")) {

			action = new ReplyModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/replyModifyPro.do")) {
			action = new ReplyModifyProAction();
			System.out.println("ProgramController - modify.do 1");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("ProgramController - modify.do 2");

		} else if (command.equals("/re_reply.do")) {
			action = new Re_replyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/re_replyPro.do")) {
			action = new Re_replyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 참가 신청
		} else if (command.equals("/reservation.do")) {
			action = new ReservationAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 가이드
		} else if (command.equals("/programGuide.do")) {
			forward = new ActionForward();
			forward.setPath("/WEB-INF/program/guide.jsp");
			// QnA
		} else if (command.equals("/qnaWriteForm.do")) {
			forward = new ActionForward();
			forward.setPath("/WEB-INF/qna_board/qna_board_write.jsp");
		} else if (command.equals("/qnaWritePro.do")) {
			action = new QnaWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/qnaViewpro.do")) {
			action = new QnaViewProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qna_board_viewPw.do")) {
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int qna_num = Integer.parseInt(request.getParameter("qna_num"));
			request.setAttribute("qna_num", qna_num);
			forward = new ActionForward();
			forward.setPath("/WEB-INF/qna_board/qna_board_viewPw.jsp");

		} else if (command.equals("/qnaList.do")) {
			action = new QnaListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// QnA 관리자
		} else if (command.equals("/qnaDetail.do")) {
			action = new QnaDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaReplyForm.do")) {
			action = new QnaReplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaReplyPro.do")) {
			action = new QnaReplyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaModifyForm.do")) {
			action = new QnaModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/qnaModifyPro.do")) {
			action = new QnaModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaDeleteForm.do")) {
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int qna_num = Integer.parseInt(request.getParameter("qna_num"));
			request.setAttribute("qna_num", qna_num);
			forward = new ActionForward();
			forward.setPath("/WEB-INF/qna_board/qna_board_delete.jsp");
		} else if (command.equals("/qnaDeletePro.do")) {
			action = new QnaDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Register
		} else if (command.equals("/regWriteForm.do")) {
			forward = new ActionForward();
			forward.setPath("/WEB-INF/reg_board/reg_board_write.jsp");
		} else if (command.equals("/regWritePro.do")) {
			action = new RegWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/regList.do")) {
			action = new RegListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/regDetail.do")) {
			action = new RegDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/regModifyForm.do")) {
			action = new RegModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/regModifyPro.do")) {
			action = new RegModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// register 관리자
		} else if (command.equals("/regConfirmPro.do")) {
			action = new AdminRegConfirmProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// review
		} else if (command.equals("/reviewList.do")) {
			action = new ReviewListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewDetail.do")) {
			action = new ReviewDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewWrite.do")) {
			forward = new ActionForward();
			forward.setPath("/WEB-INF/review/reviewWriteForm.jsp");
		} else if (command.equals("/reviewWritePro.do")) {
			action = new ReviewWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewModifyForm.do")) {
			action = new ReviewModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewModifyPro.do")) {
			action = new ReviewModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewDelete.do")) {
			action = new ReviewDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// FAQ
		} else if (command.equals("/faqList.do")) {
			action = new FaqListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/faqDetail.do")) {
			System.out.println("ddd");
			action = new FaqDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/faqModifyForm.do")) {
			action = new FaqModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/faqModifyPro.do")) {
			action = new FaqModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/faqDeletePro.do")) {
			action = new FaqDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// notice
		} else if (command.equals("/noticeList.do")) {
			action = new NoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/noticeDetail.do")) {
			action = new NoticeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// FAQ 관리자
		} else if (command.equals("/faqWriteForm.do")) {
			forward = new ActionForward();
			forward.setPath("/WEB-INF/adminFaq/adminFaqWriteForm.jsp");
		} else if (command.equals("/faqWritePro.do")) {
			action = new FaqWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// notice 관리자
		} else if (command.equals("/noticeWriteForm.do")) {
			forward = new ActionForward();
			forward.setPath("/WEB-INF/adminNotice/adminNoticeWriteForm.jsp");
		} else if (command.equals("/noticeWritePro.do")) {
			action = new NoticeWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/noticeModifyForm.do")) {
			action = new NoticeModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/noticeModifyPro.do")) {
			action = new NoticeModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/noticeDeletePro.do")) {
			action = new NoticeDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// review 관리자
		} else if (command.equals("/adminReviewDeleteForm.do")) {

			action = new AdminReviewDeleteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/adminReviewDeletePro.do")) {
			action = new AdminReviewDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// program 관리자
		} else if (command.equals("/programSearch.do")) {
			action = new ProgramSearchAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/modifyProgramForm.do")) {
			action = new ProgramModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/programModifyPro.do")) {
			action = new ProgramModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/deleteProgram.do")) {
			action = new ProgramDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/programAdminList.do")) {
			action = new ProgramAdminList();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// member 관리자
		} else if (command.equals("/clientmodify.do")) {
			action = new AdminClientModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/clientdetailinfo.do")) {
			forward = new ActionForward();
			forward.setPath("/WEB-INF/adminMember/clientDetailInfo.jsp");

		} else if (command.equals("/clientactionlog.do")) {
			forward = new ActionForward();
			forward.setPath("/WEB-INF/adminMember/clientActionLog.jsp");

		} else if (command.equals("/adminMemberDetail.do")) {

			action = new AdminMemberInfoDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// reservation 내역
		} else if (command.equals("/reservationList.do")) {
			action = new ReservationListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reservationSearch.do")) {
			action = new ReservationListSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// QnA 관리자
		} else if (command.equals("/qnaModifyFormadmin.do")) {
			action = new AdminQnaModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 세션
		else if (command.equals("/url.do")) {
			action = new FlexibleURL();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}
