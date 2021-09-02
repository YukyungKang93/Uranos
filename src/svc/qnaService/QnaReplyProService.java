package svc.qnaService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.Q_replyDAO;
import vo.Q_replyBean;

public class QnaReplyProService {

	public boolean replyArticle(Q_replyBean article) throws Exception{
		
		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection con = getConnection();
		Q_replyDAO replyDAO = Q_replyDAO.getInstance();
		replyDAO.setConnection(con);
		insertCount = replyDAO.insertReplyArticle(article);
		
		if(insertCount > 0){
			commit(con);
			isReplySuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isReplySuccess;
		
	}

}
