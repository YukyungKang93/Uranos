package svc.programService;


import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProgramDAO;
import vo.ProgramBean;


public class PetTourListService {

	public int getListCount(String category) throws Exception{
			
			
		int listCount = 0;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		listCount = programDAO.selectSelectedListCount(category);
		close(con);
		return listCount;
			
	}

	public ArrayList<ProgramBean> getPetTourList( int page, int limit) throws Exception{
			
		ArrayList<ProgramBean> petTourList = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		petTourList = programDAO.selectPetTourList( page, limit);
		close(con);
		return petTourList;
			
	}


}


