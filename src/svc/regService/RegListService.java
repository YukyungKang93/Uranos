package svc.regService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.RegDAO;
import vo.RegBean;

public class RegListService {

	public int getListCount() throws Exception{
				
		int listCount = 0;
		Connection con = getConnection();
		RegDAO regDAO = RegDAO.getInstance();
		regDAO.setConnection(con);
		listCount = regDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<RegBean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<RegBean> articleList = null;
		Connection con = getConnection();
		RegDAO regDAO = RegDAO.getInstance();
		regDAO.setConnection(con);
		articleList = regDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
		
	}

}
