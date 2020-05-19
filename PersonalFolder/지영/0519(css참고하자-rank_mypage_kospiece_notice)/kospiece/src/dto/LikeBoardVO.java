package dto;

public class LikeBoardVO {
	private int lno; //좋아요 번호(lno)
	private int fcno; //자유게시판 댓글 번호(fcno)
	private String lmemid; //회원ID(lmemid)
	
	//LikeCountService 에서 호출하는 생성자
	public LikeBoardVO(int commentNo, String id) {
		this.fcno = commentNo;
		this.lmemid=id;
	}
	
	public LikeBoardVO() {
	}

	public int getLno() {
		return lno;
	}
	public void setLno(int lno) {
		this.lno = lno;
	}
	public int getFcno() {
		return fcno;
	}
	public void setFcno(int fcno) {
		this.fcno = fcno;
	}
	public String getLmemid() {
		return lmemid;
	}
	public void setLmemid(String lmemid) {
		this.lmemid = lmemid;
	}
	@Override
	public String toString() {
		return "LikeBoard [lno=" + lno + ", fcno=" + fcno + ", lmemid=" + lmemid + "]";
	}
}
