package svc.programService;


import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProgramDAO;
import vo.ProgramBean;
public class HikingListService{
	
	
	public int getListCount(String category) throws Exception{
		
		
		int listCount = 0;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		listCount = programDAO.selectSelectedListCount(category);
		close(con);
		return listCount;
		
	}

	public ArrayList<ProgramBean> getHikingList( int page, int limit) throws Exception{
		
		ArrayList<ProgramBean> hikingList = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		hikingList = programDAO.selectHikingList( page, limit);
		close(con);
		return hikingList;
		
	}


}

