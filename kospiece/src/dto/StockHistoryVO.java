package dto;

import java.util.Date;

public class StockHistoryVO {
	
	private Date date;
	private String sname;
	private int siquantity, siprice, total;
	private String dmethod;  //true = 매수, false = 매도
	
	public StockHistoryVO(Date date, String sname, int siquantity, int siprice, int total, String dmethod) {
		this.date = date;
		this.sname = sname;
		this.siquantity = siquantity;
		this.siprice = siprice;
		this.total = total;
		this.dmethod = dmethod;
	}

	
	
	public String getSname() {
		return sname;
	}


	public String getDmethod() {
		return dmethod;
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



	@Override
	public String toString() {
		return "StockHistoryVO [date=" + date + ", sname=" + sname + ", siquantity=" + siquantity + ", siprice="
				+ siprice + ", total=" + total + ", dmethod=" + dmethod + "]";
	}
	
	
	
}
