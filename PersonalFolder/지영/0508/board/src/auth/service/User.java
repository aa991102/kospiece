package auth.service;

//p604
//LoginService에서 로그인성공시
//로그인한 회원의  정보를 담기위한 클래스
public class User {
	
	private String id;		//memberid
	private String name;	//회원명
	
	public User(String id, String name) {
		this.id   = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

	
	
	
}
