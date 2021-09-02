package svc.adminNoticeService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.Notice;

public class NoticeWriteProService {
	public boolean registNotice(Notice notice) throws Exception {
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int insertCount = noticeDAO.insertNotice(notice);
		
		if (insertCount > 0) {
			commit(con);		isWriteSuccess = true;
		} else {
			rollback(con);
		}
		close(con);		return isWriteSuccess;
	}

}
