package svc.memberService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class LoginService {	
	public String logIn(MemberBean boardBean)throws Exception{

		Connection con = getConnection();
		MemberDAO uranosDAO = MemberDAO.getInstance();
		uranosDAO.setConnection(con);
		String insertCount = uranosDAO.login(boardBean);

		close(con);
		return insertCount;
	}	
}
