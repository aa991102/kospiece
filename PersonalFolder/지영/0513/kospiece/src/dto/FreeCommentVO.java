package dto;

import java.sql.Date;

public class FreeCommentVO {
	private int fcno; //자유게시판 댓글번호(fcno)
	private String name; //회원 ID(fcmenick)
	private String fno; //글번호(fno)
	private Date uploaddate; //작성일(fcdate)
	public int getFcno() {
		return fcno;
	}
	public String getName() {
		return name;
	}
	public String getFno() {
		return fno;
	}
	public Date getUploaddate() {
		return uploaddate;
	}
	@Override
	public String toString() {
		return "FreeCommentVO [fcno=" + fcno + ", name=" + name + ", fno=" + fno + ", uploaddate=" + uploaddate + "]";
	}
}
