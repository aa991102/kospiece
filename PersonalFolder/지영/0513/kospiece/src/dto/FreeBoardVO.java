package dto;

import java.util.Date;

public class FreeBoardVO {
	private int fno; //글번호(fno)
	private String nickname; //회원ID(fmemnick)
	private String title; //제목(ftitle)
	private String content; //내용(fcontent)
	private Date uploaddate; //작성일(fdate)
	private String hit; //조회수(fhit)

	public FreeBoardVO() {}
	public FreeBoardVO(String nickname, String title, String content, Date uploaddate) {
		this.nickname=nickname;
		this.title=title;
		this.content=content;
		this.uploaddate = uploaddate;
	}
	
	public int getFno() {
		return fno;
	}

	public String getNickname() {
		return nickname;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getUploaddate() {
		return uploaddate;
	}

	public String getHit() {
		return hit;
	}

	@Override
	public String toString() {
		return "FreeBoardVO [fno=" + fno + ", nickname=" + nickname + ", title=" + title + ", content=" + content
				+ ", uploaddate=" + uploaddate + ", hit=" + hit + "]";
	}
	

}
