package board.model;

import java.sql.Date;

public class Board {
	private Integer fno; //글번호(fno)
	private Writer writer; //회원ID(fmemid)
	private String title; //제목(ftitle)
	private String content; //내용(fcontent)
	private Date uploaddate; //작성일(fdate)
	private int hit; //조회수(fhit)
	
	public Board(Integer fno, Writer writer, String title, String content, Date uploaddate, int hit) {
		this.fno = fno;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.uploaddate = uploaddate;
		this.hit = hit;
	}
	
	public Board(Integer fno,String content) {
		this.fno = fno;
		this.content = content;
	}
	
	public Integer getFno() {
		return fno;
	}
	public Writer getWriter() {
		return writer;
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
	
	
}
