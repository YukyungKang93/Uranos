package svc.memberService;

import static util.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class MemberMyRepService {
	public MemberBean isMyRepSvc(String id) {
		
		MemberBean isMyRepBean = new MemberBean();
		
		Connection con = getConnection();
		MemberDAO memberDao = MemberDAO.getInstance();
		memberDao.setConnection(con);
		
		isMyRepBean.setRepData(memberDao.myRepSelectById(id));
		

		return isMyRepBean;
	}
}
