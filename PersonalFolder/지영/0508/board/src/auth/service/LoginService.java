package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.DAO.MemberDAO;
import member.MODEL.Member;

//p605
//이 문서는  LoginHandler에서 호출되는 클래스로서 db연동
public class LoginService {
	//변수
	private MemberDAO  memberDao = new MemberDAO();
	
	//로그인처리(p605-14)
	//회원으로 존재하면 회원정보가 User객체로 리턴
	public User login(String id, String password){
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			//유저가 입력한 id를 가진 회원이 존재하는 확인하는 함수호출
			//회원으로 존재하면 회원정보가 Member객체로 리턴
			//회원으로 존재x  회원정보가 Member객체가 null리턴
			Member member = memberDao.selectById(conn, id);
			/*"SELECT  memberid, name, password, regdate " + 
			 "	FROM 	member " + 
			 "	WHERE	memberid=?";*/
			if(member==null) { //회원으로 존재x=>비회원 
				throw new LoginFailException();
			}
			
			//p606 20
			//비밀번호 불일치
			if( !member.matchPassword(password) ) {
				throw new LoginFailException();
			}
			
			//memberid, name, password, regdate 
			return new User(member.getId(), member.getName());
			
		}catch(SQLException e) {
			throw new RuntimeException();
		}
	}//end of login()
}











