package svc.programService;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProgramDAO;
import dao.ReservationDAO;
import vo.MemberBean;
import vo.ProgramBean;
import vo.Reservation;

public class ReservationService {
	public boolean reservation(MemberBean member, int p_num) throws Exception {
		boolean isWriteSuccess = false;

		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);
		ProgramBean programBean = null;
		programBean = new ProgramBean();
		member = reservationDAO.selectById(member);
		programBean = reservationDAO.selectByProgramNum(p_num);
		int insertCount = reservationDAO.Reservation(member, programBean);
		reservationDAO.updateCount(p_num);
		if (insertCount > 0) {
			commit(con);
			isWriteSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isWriteSuccess;
	}

	// 중복예약 방지 jsp랑 연동
	public Reservation getReservationList(String user_id, int p_num) throws Exception {

		Reservation checkReservation = null;
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);
		checkReservation = reservationDAO.checkReservationList(user_id, p_num);
		close(con);
		return checkReservation;

	}

}
