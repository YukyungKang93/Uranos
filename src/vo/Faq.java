package vo;

public class Faq {
	
	private int f_num;
	private String f_title;
	private String f_content;
	private String f_image;
	private int f_readcount;
	private String f_writer;
	
	public String getF_writer() {
		return f_writer;
	}
	public void setF_writer(String f_writer) {
		this.f_writer = f_writer;
	}
	public int getF_num() {
		return f_num;
	}
	public void setF_num(int f_num) {
		this.f_num = f_num;
	}

	public String getF_title() {
		return f_title;
	}
	public void setF_title(String f_title) {
		this.f_title = f_title;
	}
	public String getF_content() {
		return f_content;
	}
	public void setF_content(String f_content) {
		this.f_content = f_content;
	}
	public String getF_image() {
		return f_image;
	}
	public void setF_image(String f_image) {
		this.f_image = f_image;
	}
	public int getF_readcount() {
		return f_readcount;
	}
	public void setF_readcount(int f_readcount) {
		this.f_readcount = f_readcount;
	}
}
