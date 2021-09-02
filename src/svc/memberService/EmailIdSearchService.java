package svc.memberService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class EmailIdSearchService {
	public String idSearch(MemberBean memberbean)throws Exception{

		Connection con = getConnection();
		MemberDAO uranosDAO = MemberDAO.getInstance();
		uranosDAO.setConnection(con);
		
		String id = uranosDAO.email_id_search(memberbean);
		
		close(con);
		return id;
	}
}
