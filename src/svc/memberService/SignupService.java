package svc.memberService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class SignupService {
	public int registmember(MemberBean boardBean)throws Exception{
		int signup = 0;
		System.out.println(signup);
		Connection con = getConnection();
		MemberDAO uranosDAO = MemberDAO.getInstance();
		uranosDAO.setConnection(con);
		int insertCount = uranosDAO.insertMember(boardBean);
		
		
		close(con);
		return signup;
	}
	public boolean checkId(MemberBean boardBean)throws Exception{
		Connection con = getConnection();
		MemberDAO uranosDAO = MemberDAO.getInstance();
		uranosDAO.setConnection(con);
		boolean check_id = uranosDAO.id_check(boardBean);
		
		return check_id;
	}
	
}
