package dto;

public class UserVO {
	private int mno;
	private String id;
	private String nickname;
	
	public int getMno() {
		return mno;
	}
	public String getId() {
		return id;
	}
	public String getNickname() {
		return nickname;
	}
	
	public UserVO(int mno, String id, String nickname) {
		super();
		this.mno = mno;
		this.id = id;
		this.nickname = nickname;
	}

	
	
	
}
