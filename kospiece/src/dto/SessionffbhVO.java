package dto;

import java.util.Date;

public class SessionffbhVO {
	int fno;
	String sessionid;
	Date accesstime;
	
	public SessionffbhVO(int fno,	String sessionid,	Date accesstime){
		this.fno=fno;
		this.sessionid=sessionid;
		this.accesstime=accesstime;
	}

	public SessionffbhVO() {
		// TODO Auto-generated constructor stub
	}

	public int getFno() {
		return fno;
	}

	public String getSessionid() {
		return sessionid;
	}

	public Date getAccesstime() {
		return accesstime;
	}

	@Override
	public String toString() {
		return "SessionffbhVO [fno=" + fno + ", sessionid=" + sessionid + ", accesstime=" + accesstime + "]";
	}
}
