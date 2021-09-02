package svc.faqService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FaqDAO;
import vo.Faq;

public class FaqListService {

	public int getListCount() throws Exception{
				
		int listCount = 0;
		Connection con = getConnection();
		FaqDAO boardDAO = FaqDAO.getInstance();
		boardDAO.setConnection(con);
		listCount = boardDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<Faq> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<Faq> articleList = null;
		Connection con = getConnection();
		FaqDAO faqDAO = FaqDAO.getInstance();
		faqDAO.setConnection(con);
		articleList = faqDAO.selectFaqList(page,limit);
		close(con);
		return articleList;
		
	}

}