package dto;

import java.sql.Timestamp;
import java.util.Date;

public class SimulationVO {
	int mno, siquantity, siprice;
	String sno;
	Timestamp sidate;
	
	public SimulationVO(int mno, String sno, int siquantity, int siprice, Timestamp sidate) {
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
