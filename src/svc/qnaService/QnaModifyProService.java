package svc.qnaService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.QnaDAO;
import vo.QnaBean;

public class QnaModifyProService {

	public boolean isArticleWriter(int qna_num, String pass) throws Exception {
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		isArticleWriter = qnaDAO.isArticleBoardWriter(qna_num, pass);
		close(con);
		return isArticleWriter;
		
	}

	public boolean modifyArticle(QnaBean article) throws Exception {
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		int updateCount = qnaDAO.updateArticle(article);
		
		if(updateCount > 0){
			commit(con);
			isModifySuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isModifySuccess;
		
	}

}
