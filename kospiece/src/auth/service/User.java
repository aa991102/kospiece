package auth.service;

//5.6 유태우 수정
//authentication(인증) 패키지
//위 패키지는 로그인정보를 세션에 저장 및 운반하기위한 객체를 선언하기 위해 만듬
public class User {
	private String id;
	private String nick;
	
	public User(String id, String nick) {
		this.id=id;
		this.nick=nick;
	}

	public String getId() {
		return id;
	}

	public String getNick() {
		return nick;
	}
	
}
