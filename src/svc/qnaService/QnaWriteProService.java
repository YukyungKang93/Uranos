package svc.qnaService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.QnaDAO;
import vo.QnaBean;

public class QnaWriteProService {
   public boolean registArticle(QnaBean boardBean) throws Exception {
      boolean isWriteSuccess = false;
      
      Connection con = getConnection();
      QnaDAO qnaDAO = QnaDAO.getInstance();
      qnaDAO.setConnection(con);
      int insertCount = qnaDAO.insertArticle(boardBean);
      
      if(insertCount>0) {
         commit(con);
         isWriteSuccess = true;
      } else {
         rollback(con);
      }
      close(con);
      return isWriteSuccess;
   }
}