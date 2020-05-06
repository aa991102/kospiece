package dto;

import java.sql.Date;

public class FreeBoardVO {
	private int fno; //글번호(fno)
	private String id; //회원ID(fmemid)
	
	//5.6 수정사항 DB FreeBoard에 닉네임 추가
	private String nick; //회원닉네임()
	private String title; //제목(ftitle)
	private String content; //내용(fcontent)
	private Date uploaddate; //작성일(fdate)
	private String hit; //조회수(fhit)

	public FreeBoardVO() {}

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "FreeBoardVO [fno=" + fno + ", id=" + id + ", title=" + title + ", content=" + content + ", uploaddate="
				+ uploaddate + ", hit=" + hit + "]";
	}
	
}
