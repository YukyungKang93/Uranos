package svc.memberService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class PasswordSearchService {
	public String passwordSearch(MemberBean memberbean)throws Exception{

		Connection con = getConnection();
		MemberDAO uranosDAO = MemberDAO.getInstance();
		uranosDAO.setConnection(con);
		
		System.out.println("중간경로");
		
		String pass = uranosDAO.pass_search(memberbean);
		
		System.out.println(pass);
		
		close(con);
		return pass;
	}
}
