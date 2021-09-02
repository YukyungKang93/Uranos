package svc.programService;


import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProgramDAO;
import vo.ProgramBean;
public class ProgramListService{
	
	
	public int getListCount() throws Exception{
		
		
		int listCount = 0;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		listCount = programDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<ProgramBean> getProgramList(int page, int limit) throws Exception{
		
		ArrayList<ProgramBean> programList = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		programList = programDAO.selectProgramList(page,limit);
		close(con);
		return programList;
		
	}

}

