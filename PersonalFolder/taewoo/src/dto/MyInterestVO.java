package dto;

public class MyInterestVO {
	private int ino;
	private int mno;
	private String sno;
	
	
	
	public MyInterestVO(int ino, int mno, String sno) {
		super();
		this.ino = ino;
		this.mno = mno;
		this.sno = sno;
	}

	
	@Override
	public String toString() {
		return "MyInterestVO [ino=" + ino + ", mno=" + mno + ", sno=" + sno + "]";
	}
	
	
	public int getIno() {
		return ino;
	}
	public void setIno(int ino) {
		this.ino = ino;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	
}
