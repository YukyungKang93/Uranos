package svc.programService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProgramDAO;
import vo.ProgramBean;


public class RiddingListService {
	
	public int getListCount(String category) throws Exception{
			
			
		int listCount = 0;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		listCount = programDAO.selectSelectedListCount(category);
		close(con);
		return listCount;
			
	}

	public ArrayList<ProgramBean> getRiddingList( int page, int limit) throws Exception{
			
		ArrayList<ProgramBean> riddingList = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		riddingList = programDAO.selectRiddingList( page, limit);
		close(con);
		return riddingList;
			
	}


}