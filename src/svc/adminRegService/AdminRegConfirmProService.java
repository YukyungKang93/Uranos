package svc.adminRegService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.RegDAO;

public class AdminRegConfirmProService {

	public boolean ConfirmArticle(int reg_num) throws Exception{
		
		boolean isConfirmSuccess = false;
		Connection con = getConnection();
		RegDAO regDAO = RegDAO.getInstance();
		regDAO.setConnection(con);
		int updateCount = regDAO.ConfirmArticle(reg_num);
		
		if(updateCount > 0){
			commit(con);
			isConfirmSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isConfirmSuccess;
	}

}
