package svc.qnaService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.QnaDAO;
import vo.QnaBean;

public class QnaListService {

	public int getListCount() throws Exception{
				
		int listCount = 0;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		listCount = qnaDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<QnaBean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<QnaBean> articleList = null;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		articleList = qnaDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
		
	}

}
