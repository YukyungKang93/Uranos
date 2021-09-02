package svc.regService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.RegDAO;
import vo.RegBean;

public class RegModifyProService {

	public boolean isArticleWriter(int reg_num) throws Exception {
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		RegDAO regDAO = RegDAO.getInstance();
		regDAO.setConnection(con);
		isArticleWriter = regDAO.isArticleBoardWriter(reg_num);
		close(con);
		return isArticleWriter;
		
	}

	public boolean modifyArticle(RegBean article) throws Exception {
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		RegDAO regDAO = RegDAO.getInstance();
		regDAO.setConnection(con);
		int updateCount = regDAO.updateArticle(article);
		
		if(updateCount > 0){
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
