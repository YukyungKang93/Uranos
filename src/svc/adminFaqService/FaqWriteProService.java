package svc.adminFaqService;

import static util.JdbcUtil.*;

import java.sql.Connection;

import dao.FaqDAO;
import vo.Faq;

public class FaqWriteProService {
	public boolean registFaq(Faq faq) throws Exception {
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		FaqDAO faqDAO = FaqDAO.getInstance();
		faqDAO.setConnection(con);
		int insertCount = faqDAO.insertFaq(faq);
		
		if (insertCount > 0) {
			commit(con);		isWriteSuccess = true;
		} else {
			rollback(con);
		}
		close(con);		return isWriteSuccess;
	}

}
