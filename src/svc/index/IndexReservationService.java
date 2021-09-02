package svc.index;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReservationDAO;
import vo.Reservation;

public class IndexReservationService {

	Connection con = getConnection();

	public void setConnection(Connection con) {
		this.con = con;
	}

	private ReservationDAO reservationdao = ReservationDAO.getInstance();

	private static IndexReservationService reservationService = new IndexReservationService();

	public IndexReservationService() {
		super();
	}

	public static IndexReservationService getReservationService() {
		return reservationService;
	}

	public ArrayList<Reservation> selectReservationList(int page, int limit) {
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);
		ArrayList<Reservation> reservationlist = reservationDAO.selectReservationList(page, limit);
		close(con);
		return reservationlist;
	}

}
