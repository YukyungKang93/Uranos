package svc.memberService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class ChangePasswordService {

	private MemberDAO memberDao = MemberDAO.getInstance();

	public boolean changePasswordService(String id, String cPw, String nPw) {
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		boolean isPwUpdateSuccess=false;
		
		MemberBean member = new MemberBean();
		member.setM_PW(cPw);
		if(memberDAO.selectById(id) == null) {
			throw new RuntimeException();
		}
		if(!member.matchPassword(cPw)) {
			throw new RuntimeException();
		}
		
		member.changePassword(nPw);
		int updateCount = memberDAO.updatePw(id, nPw);
		
		
		
		
		if(updateCount > 0){
			
			isPwUpdateSuccess=true;
		}
		else{
			rollback(con);

		}
		
		commit(con);
		close(con);
		
		return isPwUpdateSuccess;
	}
}
