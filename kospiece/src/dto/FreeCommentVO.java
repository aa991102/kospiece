package dto;

import java.sql.Date;

public class FreeCommentVO {
	private int fcno; //자유게시판 댓글번호(fcno)
	private String name; //회원 ID(fcmenick)
	private String fno; //글번호(fno)
	private Date uploaddate; //작성일(fcdate)
<<<<<<< Updated upstream
=======
	
	public FreeCommentVO(int fno, String nickname, String content) {
		this.fno=fno;
		this.nickname=nickname;
		this.content=content;
	}
	//select dao에 쓰이는 생성자
	public FreeCommentVO(int fcno, int fno, String nickname, String content, Date uploaddate) {
		this.fcno = fcno;
		this.fno=fno;
		this.nickname=nickname;
		this.content=content;
		this.uploaddate=uploaddate;
	}


>>>>>>> Stashed changes
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
