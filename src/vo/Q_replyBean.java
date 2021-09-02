package vo;

import java.sql.Date;

public class Q_replyBean {
	
	private int reply_num;
	private int sec_num;
	private int qna_num;
	private String m_id;
	private String content;
	private Date reg_date;
	private Date up_date;
	
	public int getReply_num() {
		return reply_num;
	}
	public int getSec_num() {
		return sec_num;
	}
	public int getQna_num() {
		return qna_num;
	}
	public String getM_id() {
		return m_id;
	}
	public String getContent() {
		return content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public Date getUp_date() {
		return up_date;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	public void setSec_num(int sec_num) {
		this.sec_num = sec_num;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}

}
