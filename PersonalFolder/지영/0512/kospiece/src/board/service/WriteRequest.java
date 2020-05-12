package board.service;

import java.util.Map;


//게시글을 쓰는데 필요한 데이터를 정의
//작성자, 제목, 내용이 잘 들어갔는지 유효성검사
public class WriteRequest {
	private String nickname;
	private String title;
	private String content;

	public WriteRequest(String nickname, String title, String content) {
		this.nickname = nickname;
		this.title = title;
		this.content = content;
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

	//유효성검사 후 에러를 Map에 put시킴
	public void validate(Map<String, Boolean> errors) {
		if (title == null||title.trim().isEmpty()) {
			errors.put("title",Boolean.TRUE);
		}
	}

	@Override
	public String toString() {
		return "WriteRequest [nickname=" + nickname + ", title=" + title + ", content=" + content + "]";
	}
	
}