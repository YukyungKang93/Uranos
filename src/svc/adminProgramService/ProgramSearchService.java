package svc.adminProgramService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProgramDAO;
import vo.ProgramBean;

public class ProgramSearchService {
	//카테고리 item 선택후 검색
	public int getSearchListCount(String category,String item, String searchText) throws Exception{
		
		
		int listCount = 0;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		listCount = programDAO.selectSearchListCount(category, item, searchText);
		close(con);
		return listCount;
		
	}
	
	public ArrayList<ProgramBean> getProgramSearchList(int page, int limit, String category, String item, String searchText) throws Exception{
		
		ArrayList<ProgramBean> programAdminList = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		programAdminList = programDAO.selectProgramSearchList(page, limit,category, item, searchText);
		close(con);
		return programAdminList;
		
	}
	//카테고리에서 체크안하고 item만 선택후 검색
	public int getCategoryAllSearchListCount(String item, String searchText) throws Exception{
	
		
		int listCount = 0;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		listCount = programDAO.selectCategoryAllSearchListCount(item, searchText);
		close(con);
		return listCount;
		
	}
	//카테고리에서 체크안하고 item만 선택후 검색
	public ArrayList<ProgramBean> getCategoryAllSearchList(int page, int limit, String item, String searchText) throws Exception{
		
		ArrayList<ProgramBean> programAdminList = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		programAdminList = programDAO.selectCategoryAllSearchList(page, limit,item, searchText);
		close(con);
		return programAdminList;
		
	}
	//카테고리만 선택하고 검색어 입력
	public int getCategorySearchListCount(String category) throws Exception{
	
		
		int listCount = 0;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		listCount = programDAO.selectCategorySearchListCount(category);
		close(con);
		return listCount;
		
	}
	//카테고리만 선택하고 검색어 입력
	public ArrayList<ProgramBean> getCategorySearchList(int page, int limit, String category) throws Exception{
		
		ArrayList<ProgramBean> programAdminList = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		programAdminList = programDAO.selectCategorySearchList(page, limit,category);
		close(con);
		return programAdminList;
		
	}
	//category랑 item 선택안하고 검색버튼
	public int getListCount() throws Exception{
		
		
		int listCount = 0;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		listCount = programDAO.selectListCount();
		close(con);
		return listCount;
		
	}
	//category랑 item 선택안하고 검색버튼
	public ArrayList<ProgramBean> getProgramAdminList(int page, int limit) throws Exception{
		
		ArrayList<ProgramBean> programAdminList = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		programAdminList = programDAO.selectProgramAdminList(page,limit);
		close(con);
		return programAdminList;
		
	}
	//바로 검색어만 입력하고 검색버튼 클릭
	public int getAllProgramSearchListCount(String searchText) throws Exception{
		
		
		int listCount = 0;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		listCount = programDAO.selectProgramSearchListCount(searchText);
		close(con);
		return listCount;
		
	}
	//바로 검색어만 입력하고 검색버튼 클릭
	public ArrayList<ProgramBean> getAllProgramSearchList(int page, int limit,String searchText) throws Exception{
		
		ArrayList<ProgramBean> programAdminList = null;
		Connection con = getConnection();
		ProgramDAO programDAO = ProgramDAO.getInstance();
		programDAO.setConnection(con);
		programAdminList = programDAO.selectProgramAdminList(page,limit,searchText);
		close(con);
		return programAdminList;
		
	}

}

