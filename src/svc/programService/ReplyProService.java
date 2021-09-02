package svc.programService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ProgramDAO;
import util.JdbcUtil;
import vo.P_replyBean;

public class ReplyProService {
	public boolean replyArticle(P_replyBean article) throws Exception{
		
		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection con;
		con = JdbcUtil.getConnection();
		//Connection con = getConnection();
		ProgramDAO replyDAO = ProgramDAO.getInstance();
		replyDAO.setConnection(con);
		insertCount = replyDAO.insertReplyArticle(article);
		System.out.println(con);
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
