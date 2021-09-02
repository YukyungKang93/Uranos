package svc.memberService;

import static util.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class MemberInfoDetailService {
	
	public MemberBean isDetailInfoSvc(String id) {
		Connection con = getConnection();
		MemberDAO memberDao = MemberDAO.getInstance();
		memberDao.setConnection(con);
		
		MemberBean mb = new MemberBean();
		
		mb = memberDao.memberInfoDetail(id);

		return mb;
	}
	

}
