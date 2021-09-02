package svc.programService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProgramDAO;
import util.JdbcUtil;
import vo.P_replyBean;
import vo.ProgramBean;

public class ProgramDetailService {
	public static ProgramBean getProgram(int p_num) throws Exception {

		ProgramBean program = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		int updateCount = programDAO.updateReadCount(p_num);

		if (updateCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		program = programDAO.selectDetail(p_num);
		close(con);
		return program;

	}

	public static int getListCount(int p_num) throws Exception {

		int listCount = 0;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		listCount = programDAO.selectReplyListCount(p_num);
		close(con);
		return listCount;

	}

	public static ArrayList<P_replyBean> getReplyList(int p_num, int page, int limit) throws Exception {

		ArrayList<P_replyBean> replyList = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		replyList = programDAO.selectReplyList(p_num, page, limit);
		close(con);
		return replyList;

	}

	public boolean replyArticle(P_replyBean reply) throws Exception {

		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection con;
		con = JdbcUtil.getConnection();
		// Connection con = getConnection();
		ProgramDAO replyDAO = ProgramDAO.getInstance();
		replyDAO.setConnection(con);
		insertCount = replyDAO.insertReplyArticle(reply);
		System.out.println(con);
		System.out.println("커밋성공");
		if (insertCount > 0) {
			commit(con);
			isReplySuccess = true;
		} else {
			rollback(con);
		}

		close(con);
		return isReplySuccess;

	}

	public static P_replyBean getReply(int reply_num) throws Exception {

		System.out.println("getReply 1 : " + reply_num);
		P_replyBean reply = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		System.out.println("getReply 2");
		programDAO.setConnection(con);
		System.out.println("getReply 3");
		reply = programDAO.selectReply(reply_num);
		System.out.println("getReply 4");
		close(con);
		return reply;

	}

}
