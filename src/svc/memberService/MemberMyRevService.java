package svc.memberService;

import static util.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class MemberMyRevService {
	public MemberBean isMyRevSvc(String id) {
		
		MemberBean isMyRevBean = new MemberBean();
		
		Connection con = getConnection();
		MemberDAO memberDao = MemberDAO.getInstance();
		memberDao.setConnection(con);
		
		isMyRevBean.setRevData(memberDao.myRevSelectById(id));

		return isMyRevBean;
	}
}
