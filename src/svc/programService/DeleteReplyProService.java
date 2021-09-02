package svc.programService;


import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ProgramDAO;
import dao.QnaDAO;

public class DeleteReplyProService {

	public boolean isArticleWriter(int reply_num, String m_id) throws Exception {
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		isArticleWriter = programDAO.isArticleBoardWriter(reply_num, m_id);

		close(con);
		return isArticleWriter;
		
	}
	public boolean replyArticle(int group_num) throws Exception {

		boolean replySuccess = false;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		replySuccess = programDAO.replyCount(group_num);
		close(con);
		return replySuccess;
	}

	public boolean removeArticle(int reply_num) throws Exception{
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();

		programDAO.setConnection(con);
		int deleteCount = programDAO.deleteArticle(reply_num);

		if(deleteCount > 0){
			commit(con);
			isRemoveSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}
	}
