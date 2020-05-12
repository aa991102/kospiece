package dto;

import java.util.Date;

public class SimulationVO {
	int mno, siquantity, siprice;
	String sno;
	Date sidate;
	
	public SimulationVO(int mno, String sno, int siquantity, int siprice, Date sidate) {
		this.mno = mno;
		this.siquantity = siquantity;
		this.siprice = siprice;
		this.sno = sno;
		this.sidate = sidate;
	}
	
	
	
	public SimulationVO(int siquantity, String sno) {
		super();
		this.siquantity = siquantity;
		this.sno = sno;
	}



	public int getMno() {
		return mno;
	}
	public int getSiquantity() {
		return siquantity;
	}
	public int getSiprice() {
		return siprice;
	}
	public String getSno() {
		return sno;
	}
	public Date getSidate() {
		return sidate;
	}
	
	
}
