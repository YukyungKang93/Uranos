package admin.MemberAction;

import vo.MemberBean;

public class ListPage {
	private MemberBean memberBean;
	private int total = memberBean.getCount();
	private int total_page;
	private int start_page;
	private int end_page;
	private int current_page;
	private int size = 10;
	
	public ListPage(int total_page, int current_page) {
		this.total_page = total_page;
		this.current_page = current_page;
		if(total == 0) {
			total_page = 0;
			start_page = 0;
			end_page = 0;
		} else {
			if(total / size > 0) {
				total_page++;
			}
			int ModVal = current_page % 5;
			start_page = current_page / 5 * 5 + 1;
			if(ModVal == 0) start_page -= 5;
			
			end_page = start_page + 4;
			if(end_page > start_page) end_page = start_page;
		}
	}

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	public int getStart_page() {
		return start_page;
	}

	public void setStart_page(int start_page) {
		this.start_page = start_page;
	}

	public int getEnd_page() {
		return end_page;
	}

	public void setEnd_page(int end_page) {
		this.end_page = end_page;
	}

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}
	
	public boolean hasNoResult() {
		return total == 0;
	}
	
	public boolean hasResult() {
		return total > 0;
	}
	
}
