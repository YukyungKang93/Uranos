package svc.qnaService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;
import dao.QnaDAO;
import vo.QnaBean;

public class QnaViewProService {

	public boolean isArticleWriter(int qna_num, String pass) throws Exception {
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		isArticleWriter = qnaDAO.isArticleBoardWriter(qna_num, pass);
		close(con);
		return isArticleWriter;
		
	}

	public boolean removeArticle(int qna_num) throws Exception{
		
		boolean isRemoveSuccess = true;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		qnaDAO.selectArticle(qna_num);
		close(con);
		return isRemoveSuccess;
	}

}
