package vo;

import java.sql.Date;

public class RegBean {
	
	private int reg_num;
	private String category;
	private String p_name;
	private String startdate;
	private String m_id;
	private String m_name;
	private String image;
	private String reg_content;
	private int total_number;
	private Date reg_date;
	private int reg_readcount;
	private String reg_state;
	
	public int getReg_num() {
		return reg_num;
	}
	public void setReg_num(int reg_num) {
		this.reg_num = reg_num;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getReg_content() {
		return reg_content;
	}
	public void setReg_content(String reg_content) {
		this.reg_content = reg_content;
	}
	public int getTotal_number() {
		return total_number;
	}
	public void setTotal_number(int total_number) {
		this.total_number = total_number;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getReg_readcount() {
		return reg_readcount;
	}
	public void setReg_readcount(int reg_readcount) {
		this.reg_readcount = reg_readcount;
	}
	public String getReg_state() {
		return reg_state;
	}
	public void setReg_state(String reg_state) {
		this.reg_state = reg_state;
	}

}