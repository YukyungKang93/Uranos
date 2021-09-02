package svc.adminProgramService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ProgramDAO;
import vo.ProgramBean;

public class ProgramModifyService {	
	public boolean modifyProgram(ProgramBean program) throws Exception {
				
		boolean isModifySuccess = false;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		int updateCount = programDAO.updateProgram(program);
		
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
