package svc.qnaService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.QnaDAO;

public class QnaDeleteProService {

	public boolean replyArticle(int qna_num) throws Exception {

		boolean replySuccess = false;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		replySuccess = qnaDAO.replyCount(qna_num);
		close(con);
		return replySuccess;
	}

	public boolean removeArticle(int qna_num) throws Exception {

		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		int deleteCount = qnaDAO.deleteArticle(qna_num);

		if (deleteCount > 0) {
			commit(con);
			isRemoveSuccess = true;
		} else {
			rollback(con);
		}

		close(con);
		return isRemoveSuccess;
	}

}
