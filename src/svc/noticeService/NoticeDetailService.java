package svc.noticeService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.Notice;

public class NoticeDetailService {

	public Notice getArticle(int n_num) throws Exception{
				
		Notice article = null;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int updateCount = noticeDAO.updateReadCount(n_num);
		
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		article = noticeDAO.selectArticle(n_num);
		close(con);
		return article;
		
	}

}
