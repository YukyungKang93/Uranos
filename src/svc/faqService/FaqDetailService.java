package svc.faqService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.FaqDAO;
import vo.Faq;

public class FaqDetailService {

	public Faq getArticle(int f_num) throws Exception{
				
		Faq article = null;
		Connection con = getConnection();
		FaqDAO boardDAO = FaqDAO.getInstance();
		boardDAO.setConnection(con);
		int updateCount = boardDAO.updateReadCount(f_num);
		
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		article = boardDAO.selectArticle(f_num);
		close(con);
		return article;
		
	}

}
