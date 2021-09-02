package svc.adminFaqService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.FaqDAO;

public class FaqDeleteProService {

public boolean removeArticle(int f_num) throws Exception{
				
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		FaqDAO faqDAO = FaqDAO.getInstance();
		faqDAO.setConnection(con);
		int deleteCount = faqDAO.deleteFaq(f_num);
		
		if(deleteCount > 0){
			commit(con);
			isRemoveSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}

}
