package svc.adminFaqService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.FaqDAO;
import vo.Faq;

public class FaqModifyProService {

	public boolean modifyFaq(Faq faq) throws Exception {
				
		boolean isModifySuccess = false;
		Connection con = getConnection();
		FaqDAO faqDAO = FaqDAO.getInstance();
		faqDAO.setConnection(con);
		int updateCount = faqDAO.updateFaq(faq);
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