package svc.qnaService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.QnaDAO;
import vo.QnaBean;


public class QnaDetailService {

	public QnaBean getArticle(int qna_num) throws Exception{
		QnaBean article = null;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		int updateCount = qnaDAO.updateReadCount(qna_num);
		
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		article = qnaDAO.selectArticle(qna_num);
		close(con);
		return article;
	}
}
