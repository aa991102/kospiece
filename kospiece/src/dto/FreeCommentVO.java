package dto;

import java.util.Date;

public class FreeCommentVO {
	private int fcno; //자유게시판 댓글번호(fcno)
	private int fno; //글번호(fno)
	private String nickname; //회원 ID(fcmenick)
	private String content; //(fccontent)
	private Date uploaddate; //작성일(fcdate)
	
	public FreeCommentVO(int fno, String nickname, String content) {
		this.fno=fno;
		this.nickname=nickname;
		this.content=content;
	}


	public int getFcno() {
		return fcno;
	}
	
	public String getContent() {
		return content;
	}

	public String getNickname() {
		return nickname;
	}
	public int getFno() {
		return fno;
	}
	public Date getUploaddate() {
		return uploaddate;
	}

}
