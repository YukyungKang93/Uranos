package svc.adminRegService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.RegDAO;
import vo.RegBean;

public class AdminRegConfirmService {

	public boolean programArticle(RegBean article) throws Exception {
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		RegDAO regDAO = RegDAO.getInstance();
		regDAO.setConnection(con);
		int insertCount = regDAO.insertProgram(article);
		
		if(insertCount > 0){
			commit(con);
			isModifySuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isModifySuccess;
		
	}

}
