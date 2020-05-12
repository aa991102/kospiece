//200503 신나진 memberVO생성 및 회원테이블 컬럼명 주석

package dto;

import java.sql.Date;

public class MemberVO {
	
	private int mno; //회원번호(mno)
	private String id; //회원ID(id)
	private String nickname; //닉네임(mnick)
	private String pw; //비밀번호(mpw)
	private String name; //이름(mname)
	private String mail; //이메일(mname)
	private String phone; //전화번호(mphone)
	private Date regdate; //가입일(mdate)
	private int deposit; //예수금포인트(mdeposit)
	private int asset; //자산포인트(masset)

	public MemberVO() {}
	
	public MemberVO(String id,String nickname, String pw) {
		this.id=id;
		this.nickname=nickname;
		this.pw=pw;
	}
	
	
	
	public MemberVO(int mno, String id, String nickname, String pw, String name, int deposit, int asset) {
		this.mno = mno;
		this.id = id;
		this.nickname = nickname;
		this.pw = pw;
		this.name = name;
		this.deposit = deposit;
		this.asset = asset;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getAsset() {
		return asset;
	}

	public void setAsset(int asset) {
		this.asset = asset;
	}

	@Override
	public String toString() {
		return "MemberVO [mno=" + mno + ", id=" + id + ", nickname=" + nickname + ", pw=" + pw + ", name=" + name
				+ ", mail=" + mail + ", phone=" + phone + ", regdate=" + regdate + ", deposit=" + deposit + ", asset="
				+ asset + "]";
	}

	public boolean matchPassword(String password) {
		return pw.contentEquals(password);
	}

	
}
