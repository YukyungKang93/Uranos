package svc.adminReservationListService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReservationDAO;
import vo.Reservation;

public class ReservationListService {

	public int getReservationAllListCount() throws Exception{
		
		
		int listCount = 0;
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);
		listCount = reservationDAO.selectAdminReservationListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<Reservation> getReservationAllList(int page, int limit) throws Exception{
		
		ArrayList<Reservation> reservationAllList = null;
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);
		reservationAllList = reservationDAO.selectAdminReservationList(page,limit);
		close(con);
		return reservationAllList;
		
	}

}


