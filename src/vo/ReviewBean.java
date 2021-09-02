package vo;

import java.sql.Date;

public class ReviewBean {

	private int Rev_num;
	private String P_name;
	private String Title;
	private String M_id;
	private String Image;
	private String Rev_content;
	private int Rev_readcount;
	private Date Rev_date;
	private String Score;
	
	
	public int getRev_num() {
		return Rev_num;
	}
	public void setRev_num(int rev_num) {
		Rev_num = rev_num;
	}
	public String getP_name() {
		return P_name;
	}
	public void setP_name(String p_name) {
		P_name = p_name;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getM_id() {
		return M_id;
	}
	public void setM_id(String m_id) {
		M_id = m_id;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public String getRev_content() {
		return Rev_content;
	}
	public void setRev_content(String rev_content) {
		Rev_content = rev_content;
	}
	public int getRev_readcount() {
		return Rev_readcount;
	}
	public void setRev_readcount(int rev_readcount) {
		Rev_readcount = rev_readcount;
	}
	public Date getRev_date() {
		return Rev_date;
	}
	public void setRev_date(Date rev_date) {
		Rev_date = rev_date;
	}
	public String getScore() {
		return Score;
	}
	public void setScore(String score) {
		Score = score;
	}

}
