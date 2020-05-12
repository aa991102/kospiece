package dto;

public class UserVO {
	private String mid;
	private String mnick;
	
	public UserVO(String mid, String mnick) {
		this.mid = mid;
		this.mnick = mnick;
	}

	public String getMid() {
		return mid;
	}

	public String getMnick() {
		return mnick;
	}
	
}
