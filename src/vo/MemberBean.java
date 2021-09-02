package vo;

import java.util.ArrayList;
import java.util.Map;

public class MemberBean {


	private String M_ID;
	private String M_PW;
	private String M_PW_CONFIRM;
	private String M_NAME;
	private String ADDR;
	private String DETAIL_ADDR;
	private String REF_ADDR;
	private String EMAIL;
	private String PHONE;
	private String GENDER;
	private String BIRTH;
	private String REGDATE;
	private String SALT;
	private String s_topic;
	private String s_value;
	private String Arrange;
	private String Order_By;
	private int count;
	private int show_count;
	private int result;
	private int current_page;
	private ArrayList<MemberListBean> data;
	private ArrayList<String> revData;
	private ArrayList<String> proData;
	private ArrayList<String> repData;
	public ArrayList<String> getRepData() {
		return repData;
	}

	public void setRepData(ArrayList<String> repData) {
		this.repData = repData;
	}
	
	
	public ArrayList<String> getRevData() {
		return revData;
	}
	public void setRevData(ArrayList<String> revData) {
		this.revData = revData;
	}
	public ArrayList<String> getProData() {
		return proData;
	}
	public void setProData(ArrayList<String> proData) {
		this.proData = proData;
	}
	public int getShow_count() {
		return show_count;
	}
	public void setShow_count(int show_count) {
		this.show_count = show_count;
	}
	public int getCurrent_page() {
		return current_page;
	}
	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}
	public String getOrder_By() {
		return Order_By;
	}
	public void setOrder_By(String order_By) {
		Order_By = order_By;
	}
	public String getArrange() {
		return Arrange;
	}
	public void setArrange(String arrange) {
		Arrange = arrange;
	}
	public ArrayList<MemberListBean> getData() {
		return data;
	}
	public void setData(ArrayList<MemberListBean> data) {
		this.data = data;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getM_ID() {
		return M_ID;
	}
	public String getM_PW() {
		return M_PW;
	}
	
	public String getM_PW_CONFIRM() {
		return M_PW_CONFIRM;
	}
	
	public String getM_NAME() {
		return M_NAME;
	}
	
	public String getDETAIL_ADDR() {
		return DETAIL_ADDR;
	}

	public void setDETAIL_ADDR(String dETAIL_ADDR) {
		DETAIL_ADDR = dETAIL_ADDR;
	}

	public String getREF_ADDR() {
		return REF_ADDR;
	}

	public void setREF_ADDR(String rEF_ADDR) {
		REF_ADDR = rEF_ADDR;
	}

	public String getADDR() {
		return ADDR;
	}
	public String getEMAIL() {
		return EMAIL;
	}	
	public String getPHONE() {
		return PHONE;
	}
	public String getGENDER() {
		return GENDER;
	}
	public String getBIRTH() {
		return BIRTH;
	}
	public String getREGDATE() {
		return REGDATE;
	}
	public String getSALT() {
		return SALT;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public void setM_PW(String m_PW) {
		M_PW = m_PW;
	}
	
	public void setM_PW_CONFIRM(String m_PW_CONFIRM) {
		M_PW_CONFIRM = m_PW_CONFIRM;
	}
	
	
	
	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}
	public void setADDR(String aDDR) {
		ADDR = aDDR;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}
	public void setBIRTH(String bIRTH) {
		BIRTH = bIRTH;
	}
	public void setREGDATE(String rEGDATE) {
		REGDATE = rEGDATE;
	}
	public void setSALT(String sALT) {
		SALT = sALT;
	}
	
	
	public void changePassword(String newM_PW) {
		this.M_PW = newM_PW;
	}
	
	public boolean matchPassword(String curM_PW) {
		return M_PW.equals(curM_PW);
	}
	
	
	public boolean isPasswordEqualToConfirm() {
		return M_PW != null && M_PW.equals(M_PW_CONFIRM);
	}
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, M_ID, "id");
		checkEmpty(errors, M_NAME, "name");
		checkEmpty(errors, M_PW, "password");
		checkEmpty(errors, M_PW_CONFIRM, "confirmPassword");
		if (!errors.containsKey("confirmPassword")) {
			if (!isPasswordEqualToConfirm()) {
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}

	private void checkEmpty(Map<String, Boolean> errors, 
			String value, String fieldName) {
		if (value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
	public void set_search(String search) {
		this.s_topic = search;
	}
	public String get_search() {
		return s_topic;
	}
	public void set_value(String value) {
		this.s_value = value;
	}
	public String get_value() {
		return s_value;
	}
	
	public boolean hasNoResult() {
		return result==0;
	}
	
	
}
