package svc.adminReservationListService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProgramDAO;
import dao.ReservationDAO;
import vo.ProgramBean;
import vo.Reservation;

public class ReservationSearchService {
	
	// item 선택후 검색어 입력 할 때 카운트
	public int getItemSearchListCount(String item, String searchText) throws Exception{
	
		
		int listCount = 0;
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);
		listCount = reservationDAO.selectItemSearchListCount(item, searchText);
		close(con);
		return listCount;
		
	}
	// item 선택후 검색어 입력 할 때 리스트
	public ArrayList<Reservation> getItemSearchList(int page, int limit, String item, String searchText) throws Exception{
		
		ArrayList<Reservation> reservationSearchList = null;
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);
		reservationSearchList = reservationDAO.selectItemSearchList(page, limit,item, searchText);
		close(con);
		return reservationSearchList;
		
	}
	
	//item 선택안하고 바로 검색버튼만 (전체보기 효과)
	public int getListCount() throws Exception{
		
		
		int listCount = 0;
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);
		listCount = reservationDAO.selectAdminReservationListCount();
		close(con);
		return listCount;
		
	}
	//item 선택안하고 바로 검색버튼만 (전체보기 효과)
	public ArrayList<Reservation> getReservationList(int page, int limit) throws Exception{
		
		ArrayList<Reservation> reservationSearchList = null;
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);
		reservationSearchList = reservationDAO.selectAdminReservationList(page,limit);
		close(con);
		return reservationSearchList;
		
	}
	//바로 검색어만 입력하고 검색버튼 클릭 (프로그램제목으로 검색됨)
	public int getAllReservationSearchListCount(String searchText) throws Exception{
		
		
		int listCount = 0;
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);
		listCount = reservationDAO.selectReservationSearchListCount(searchText);
		close(con);
		return listCount;
		
	}
	//바로 검색어만 입력하고 검색버튼 클릭 (프로그램제목으로 검색됨)
	public ArrayList<Reservation> getAllReservationSearchList(int page, int limit,String searchText) throws Exception{
		
		ArrayList<Reservation> reservationSearchList = null;
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);
		reservationSearchList = reservationDAO.selectReservationAdminList(page,limit,searchText);
		close(con);
		return reservationSearchList;
		
	}

}

