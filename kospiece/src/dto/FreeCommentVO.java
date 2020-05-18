package dto;

import java.util.Date;

public class FreeCommentVO {
	private int fcno; //자유게시판 댓글번호(fcno)
	private int fno; //글번호(fno)
	private String nickname; //회원 ID(fcmenick)
	private String content; //fccontent
	private Date uploaddate; //작성일(fcdate)
	
	public FreeCommentVO(int fno, String nickname, String content) {
		this.fno=fno;
		this.nickname=nickname;
		this.content=content;
	}
	//select dao에 쓰이는 생성자
	public FreeCommentVO(int fno, String nickname, String content, Date uploaddate) {
		this.fno=fno;
		this.nickname=nickname;
		this.content=content;
		this.uploaddate=uploaddate;
	}

	//select dao에 쓰이는 생성자
	public FreeCommentVO(int fcno, int fno, String nickname, String content, Date uploaddate) {
		this.fcno = fcno;
		this.fno=fno;
		this.nickname=nickname;
		this.content=content;
		this.uploaddate=uploaddate;
	}


	public int getFno() {
		return fno;
	}

	public String getNickname() {
		return nickname;
	}

	public String getContent() {
		return content;
	}

	public int getFcno() {
		return fcno;
	}
	public Date getUploaddate() {
		return uploaddate;
	}

	@Override
	public String toString() {
		return "FreeCommentVO [fcno=" + fcno + ", fno=" + fno + ", nickname=" + nickname + ", content=" + content
				+ ", uploaddate=" + uploaddate + "]";
	}

}
