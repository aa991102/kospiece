//200503 신나진 noticeVO생성 및 공지사항테이블 컬럼명 주석

package dto;

import java.sql.Date;

public class NoticeVO {
	private int nno; //글번호(nno)
	private String title; //제목(ntitle)
	private String content; //내용(ncontent)
	private Date uploadDate; //작성일(ndate)
	private int hit; //조회수(nhit)
	public int getNno() {
		return nno;
	}
	public void setNno(int nno) {
		this.nno = nno;
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
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	@Override
	public String toString() {
		return "NoticeVO [nno=" + nno + ", title=" + title + ", content=" + content + ", uploadDate=" + uploadDate
				+ ", hit=" + hit + "]";
	}
	
}
