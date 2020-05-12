package model.dto;

//게시판테이블
public class BoardVO {

	private int userno;
	private String title;
	private String contents;
	
	public BoardVO() {}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "postVO [userno=" + userno + ", title=" + title + ", contents=" + contents + "]";
	}
	
}

