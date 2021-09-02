package svc.noticeService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.NoticeDAO;
import vo.Notice;

public class NoticeListService {

	public int getListCount() throws Exception{
				
		int listCount = 0;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		listCount = noticeDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<Notice> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<Notice> articleList = null;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		articleList = noticeDAO.selectNoticeList(page,limit);
		close(con);
		return articleList;
		
	}

}