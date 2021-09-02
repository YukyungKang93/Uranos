package svc.adminRegService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.RegDAO;
import vo.RegBean;

public class AdminRegConfirmcheckService {

	public boolean checkArticle(int reg_num) throws Exception {
		
		boolean checkCount = false;
		Connection con = getConnection();
		RegDAO regDAO = RegDAO.getInstance();
		regDAO.setConnection(con);
		checkCount = regDAO.confirmCheckCount(reg_num);
		
		close(con);
		return checkCount;
		
	}

}
