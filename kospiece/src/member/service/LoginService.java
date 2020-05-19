package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDAO;
import dto.MemberVO;
import jdbc.connection.ConnectionProvider;

public class LoginService {

	private MemberDAO  memberDao = new MemberDAO();
	
	public MemberVO login(String id, String password){
		Connection conn = null;
		
		
		try {
			conn = ConnectionProvider.getConnection();
			//유저가 입력한 id를 가진 회원이 존재하는 확인하는 함수호출
			//회원으로 존재하면 회원정보가 Member객체로 리턴
			//회원으로 존재x  회원정보가 Member객체가 null리턴
			MemberVO member = memberDao.selectById(conn, id);
			if(member==null) { //회원으로 존재x=>비회원 
				throw new LoginFailException();
			}
			if(!member.matchPassword(password)) {
				throw new LoginFailException();
			}
			
			return member;
			
		}catch(SQLException e) {
			throw new RuntimeException();
		}
	}

}




