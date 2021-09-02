package svc.qnaService;

import static util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.qnaAction.QnaReplyListAction;
import dao.Q_replyDAO;
import util.JdbcUtil;
import vo.ActionForward;
import vo.Q_replyBean;

public class QnaReplyListService implements Action {
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		
		ActionForward forward = null;
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		Q_replyBean reBean = null;
		QnaReplyListService replyListService = new QnaReplyListService();
		QnaReplyListAction reaction = replyListService.getMessageList(qna_num);
		return forward;
	}
	
	private static final int MESSAGE_COUNT_PER_PAGE = 1;

	public QnaReplyListAction getMessageList(int qna_num) {
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			Q_replyDAO messageDao = Q_replyDAO.getInstance();
			int messageTotalCount = messageDao.selectCount(con);

			List<Q_replyBean> messageList = null;
			int firstRow = 0;
			int endRow = 0;
			if (messageTotalCount > 0) {
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE - 1;
				messageList = messageDao.selectList(con, firstRow, endRow, qna_num);
			} else {
				messageList = Collections.emptyList();
			}
			return new QnaReplyListAction(messageList, messageTotalCount);
		} catch (SQLException e) {
			throw new ServiceException("목록 구하기 실패: " + e.getMessage(), e);
		} finally {
			close(con);
		}
	}
}
