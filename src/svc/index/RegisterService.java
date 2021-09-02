package svc.index;

import static util.JdbcUtil.close;
import static util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.RegDAO;
import vo.RegBean;

public class RegisterService {
	
	Connection con = getConnection();
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	private RegDAO regDAO = RegDAO.getInstance();
	
	private static RegisterService registerService = new RegisterService();
	
	public RegisterService() {
		super();
	}
	
	public static RegisterService getRegisterService() {
		return registerService;
	}
	
	
	public ArrayList<RegBean> selectRegisterList(int page, int limit) {
		RegDAO regDAO = RegDAO.getInstance();
		regDAO.setConnection(con);
		ArrayList<RegBean> reglist = regDAO.selectRegList(page,limit);
		close(con);
		return reglist;
	}
}

