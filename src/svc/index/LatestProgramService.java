package svc.index;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProgramDAO;
import vo.ProgramBean;

public class LatestProgramService {
	
	
	public int getListCount() throws Exception{
		
		
		int listCount = 0;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		listCount = programDAO.selectLatestProgramListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<ProgramBean> getProgramList() throws Exception{
		
		ArrayList<ProgramBean> latestProgramList = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		latestProgramList = programDAO.selectLatestProgramList();
		close(con);
		return latestProgramList;
		
	}

}

