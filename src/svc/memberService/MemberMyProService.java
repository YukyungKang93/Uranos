package svc.memberService;

import static util.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class MemberMyProService {
	public MemberBean isMyProSvc(String id) {
		
		MemberBean isMyProBean = new MemberBean();
		
		Connection con = getConnection();
		MemberDAO memberDao = MemberDAO.getInstance();
		memberDao.setConnection(con);
		
		isMyProBean.setProData(memberDao.myProSelectById(id));
		

		return isMyProBean;
	}
}
