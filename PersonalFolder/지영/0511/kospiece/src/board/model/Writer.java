package board.model;

/*
 * boardWriteservice를 구현하면서 필요하게되는 글쓴이의 정보
 */
public class Writer {

	private String id;
	private String nick;

	public Writer(String id, String nick) {
		this.id = id;
		this.nick = nick;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return nick;
	}
}
