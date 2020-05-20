package dto;

public class HateBoardVO {
	private int hno; //싫어요 번호(hno)
	private int fcno; //자유게시판 댓글 번호(fcno)
	private String hmemid; //회원ID(hmemid)
	public HateBoardVO(int commentNo, String id) {
		this.fcno = commentNo;
		this.hmemid=id;
	}
	public int getHno() {
		return hno;
	}
	public void setHno(int hno) {
		this.hno = hno;
	}
	public int getFcno() {
		return fcno;
	}
	public void setFcno(int fcno) {
		this.fcno = fcno;
	}
	public String getHmemid() {
		return hmemid;
	}
	public void setHmemid(String hmemid) {
		this.hmemid = hmemid;
	}
	@Override
	public String toString() {
		return "HateBoard [hno=" + hno + ", fcno=" + fcno + ", hmemid=" + hmemid + "]";
	}
}
