package svc.memberService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class MemberModifyService {

	public boolean isArticleWriter(int board_num, String pass) throws Exception {
		// TODO Auto-generated method stub

		boolean isArticleWriter = false;
		Connection con = getConnection();
		MemberDAO boardDAO = MemberDAO.getInstance();
		boardDAO.setConnection(con);
//		isArticleWriter = boardDAO.isArticleBoardWriter(board_num, pass);
		close(con);
		return isArticleWriter;

	}

	public boolean modifyService(String idCheck, MemberBean memberBean) throws Exception {

		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		boolean isUpdateSuccess=false;

		
		


		int updateCount = memberDAO.updateMember(idCheck, memberBean);
	
		if(updateCount > 0){
			
			isUpdateSuccess=true;
		}
		else{
			rollback(con);
		}
		
		commit(con);
		close(con);
		
		return isUpdateSuccess;

	}
}
