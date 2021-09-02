package svc.regService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.RegDAO;
import vo.RegBean;


public class RegDetailService {

	public RegBean getArticle(int reg_num) throws Exception{
		RegBean article = null;
		Connection con = getConnection();
		RegDAO regDAO = RegDAO.getInstance();
		regDAO.setConnection(con);
		article = regDAO.selectArticle(reg_num);
		close(con);
		return article;
	}
}
