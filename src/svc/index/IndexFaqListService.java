package svc.index;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FaqDAO;
import vo.Faq;

public class IndexFaqListService {

	public int getListCount() throws Exception{
				
		int listCount = 0;
		Connection con = getConnection();
		FaqDAO faqDAO = FaqDAO.getInstance();
		faqDAO.setConnection(con);
		listCount = faqDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<Faq> getIndexFaqList() throws Exception{
		
		ArrayList<Faq> indexFaqList = null;
		Connection con = getConnection();
		FaqDAO faqDAO = FaqDAO.getInstance();
		faqDAO.setConnection(con);
		indexFaqList = faqDAO.selectIndexFaqList();
		close(con);
		return indexFaqList;
		
	}

}