package svc.adminNoticeService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.Notice;

public class NoticeModifyProService {

	public boolean modifyNotice(Notice notice) throws Exception {
				
		boolean isModifySuccess = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int updateCount = noticeDAO.updateNotice(notice);
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