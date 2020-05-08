package member.MODEL;

import java.util.Date;

//p591
//여기에서는 db의 member테이블과 관련한 데이터를 담는 클래스
//VO(Value Object)
//DTO(Data Transfer Object)
//MemeberVO
//MemberDTO
public class Member {
	//변수
	private String id;		//memberid컬럼용
	private String name;	//name컬럼용
	private String password;//password컬럼용
	private Date   regDate; //regdate컬럼용
	
	//생성
	public Member() {}
	
	public Member(String id, String name, String password, Date regDate) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.regDate = regDate;
	}


	//setter & getter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	//p592 35~37
	//비밀번호 일치여부 체크
	public boolean matchPassword(String pwd) {
		return  this.password.equals(pwd);
	}
	
	//p619 20
	public void changePassword(String newPwd) {
		this.password = newPwd;
	}
	
}







