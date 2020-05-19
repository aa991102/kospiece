package dto;

import java.util.Date;

public class FreeCommentVO {
	private int fcno; //자유게시판 댓글번호(fcno)
	private int fno; //글번호(fno)
	private String nickname; //회원 ID(fcmenick)
	private String content; //fccontent
	private Date uploaddate; //작성일(fcdate)
	private int like; //fclike 좋아요 수
	private int hate; //fchate 싫어요 수
	
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
	public FreeCommentVO(int fcno, int fno, String nickname, String content, Date uploaddate, int like, int hate) {
		this.fcno = fcno;
		this.fno=fno;
		this.nickname=nickname;
		this.content=content;
		this.uploaddate=uploaddate;
		this.like=like;
		this.hate=hate;
	}


	public int getLike() {
		return like;
	}
	public int getHate() {
		return hate;
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
				+ ", uploaddate=" + uploaddate + ", like=" + like + ", hate=" + hate + "]";
	}

}
