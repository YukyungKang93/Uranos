package svc.regService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.RegDAO;
import vo.RegBean;

public class RegWriteProService {
   public boolean registArticle(RegBean regBean) throws Exception {
      boolean isWriteSuccess = false;
      
      Connection con = getConnection();
      RegDAO regDAO = RegDAO.getInstance();
      regDAO.setConnection(con);
      int insertCount = regDAO.insertArticle(regBean);
      
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