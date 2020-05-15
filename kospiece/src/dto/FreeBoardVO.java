package dto;

import java.util.Date;

public class FreeBoardVO {
	private int fno; //글번호(fno)
	private String horsehead; //말머리(fclass)
	private String nickname; //회원ID(fmemnick)
	private String title; //제목(ftitle)
	private String content; //내용(fcontent)
	private Date uploaddate; //작성일(fdate)
	private int hit; //조회수(fhit)

	public FreeBoardVO() {}
	//write에서 쓰는 생성자
	public FreeBoardVO(String horsehead, String nickname, String title, String content, Date uploaddate) {
		this.horsehead=horsehead;
		this.nickname=nickname;
		this.title=title;
		this.content=content;
		this.uploaddate=uploaddate;
	}
	//update에 쓰이는 생성자
	public FreeBoardVO(int fno, String horsehead, String title, String content) {
		this.fno=fno;
		this.horsehead=horsehead;
		this.title=title;
		this.content=content;
	}
	public FreeBoardVO(int fno, String horsehead, String nickname, String title, String content, Date uploaddate, int hit) {
		this.fno=fno;
		this.horsehead=horsehead;
		this.nickname=nickname;
		this.title=title;
		this.content=content;
		this.uploaddate = uploaddate;
		this.hit=hit;
	}
	public int getFno() {
		return fno;
	}

	public String getNickname() {
		return nickname;
	}

	public String getHorsehead() {
		return horsehead;
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

	public int getHit() {
		return hit;
	}
	@Override
	public String toString() {
		return "FreeBoardVO [fno=" + fno + ", horsehead=" + horsehead + ", nickname=" + nickname + ", title=" + title
				+ ", content=" + content + ", uploaddate=" + uploaddate + ", hit=" + hit + "]";
	}
}
