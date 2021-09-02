package svc.programService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ProgramDAO;
import vo.P_replyBean;

public class ModifyReplyProService {
public boolean isArticleWriter(int reply_num, String m_id) throws Exception {
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		System.out.println("m_id::::::::::::::" + m_id);
		isArticleWriter = programDAO.isArticleBoardWriter(reply_num, m_id);
		close(con);
		return isArticleWriter;
		
	}

	public boolean modifyArticle(P_replyBean reply) throws Exception{
		boolean isModifySuccess = false;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();

		programDAO.setConnection(con);
		int updateCount = programDAO.updateArticle(reply);

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
