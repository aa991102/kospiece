package dto;

import java.util.Date;

public class StockHistoryVO {
	
	private Date date;
	private String sname;
	private int siquantity, siprice, total;
	
	
	public StockHistoryVO(Date date, String sname, int siquantity, int siprice, int total) {
		super();
		this.date = date;
		this.sname = sname;
		this.siquantity = siquantity;
		this.siprice = siprice;
		this.total = total;
	}

	
	
	public void setSname(String sname) {
		this.sname = sname;
	}


	public Date getDate() {
		return date;
	}


	public String getsname() {
		return sname;
	}


	public int getSiquantity() {
		return siquantity;
	}


	public int getSiprice() {
		return siprice;
	}


	public int getTotal() {
		return total;
	}
	
	
	
}
