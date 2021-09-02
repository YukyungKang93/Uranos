package svc.index;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.NoticeDAO;
import vo.Notice;


public class IndexNoticeListService {

		public int getListCount() throws Exception{
					
			int listCount = 0;
			Connection con = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			listCount = noticeDAO.selectListCount();
			close(con);
			return listCount;
			
		}

		public ArrayList<Notice> getIndexNoticeList() throws Exception{
		
			
			ArrayList<Notice> indexNoticeList = null;
			Connection con = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			indexNoticeList = noticeDAO.selectIndexNoticeList();
			close(con);
			return indexNoticeList;
			
		}

	}