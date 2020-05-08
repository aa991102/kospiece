package dto;

import java.sql.Date;

public class FreeCommentVO {
	private int fcno; //자유게시판 댓글번호(fcno)
	private String id; //회원 ID(fcmemid)
	private String fno; //글번호(fno)
	private Date uploaddate; //작성일(fcdate)
	
	public int getFcno() {
		return fcno;
	}
	public void setFcno(int fcno) {
		this.fcno = fcno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFno() {
		return fno;
	}
	public void setFno(String fno) {
		this.fno = fno;
	}
	public Date getUploaddate() {
		return uploaddate;
	}
	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}
	@Override
	public String toString() {
		return "FreeComment [fcno=" + fcno + ", id=" + id + ", fno=" + fno + ", uploaddate=" + uploaddate + "]";
	}
}
