package article.MODEL;

import java.util.Date;

//p631 코드작성 후 
//WriteArticleService의 toArticle()함수 emd dptj tkdyd
//article 테이블 관련 데이터를 관리하는 클래스

public class Article {
	
	private Integer number;
	private Writer 	writer;			//writer_id,writer_name
	private String 	title;			//title
	private Date 	regDate;		//regdate
	private Date 	modifiedDate;	//moddate
	private int 	readCount;		//read_cnt
	
	public Article(Integer number, Writer writer, String title, 
					Date regdate, Date modifiedDate,int readCount) {
		this.number = number;
		this.writer = writer;
		this.title 	= title;
		this.regDate= regdate;
		this.modifiedDate = modifiedDate;
		this.readCount = readCount;
	}

	@Override
	public String toString() {
		return "Article [number=" + number + ", writer=" + writer + ", title=" + title + ", regDate=" + regDate
				+ ", modifiedDate=" + modifiedDate + ", readCount=" + readCount + "]";
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

}
