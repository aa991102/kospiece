package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dao.MemberDAO;
import dto.MemberVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;


public class FindUpdatePwService {

	
	
	public static void findupdatepw(MemberVO membervo) {
		System.out.println("FindUpdatePwService-findupdatepw()호출");
		MemberDAO memberDao = new MemberDAO();
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작

			memberDao.pwUpdate(conn,membervo);
			
			
			conn.commit(); //트랙잭션 반영
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	public static boolean checkNull(MemberVO member) {
		
		boolean result=true;
		
		if(
			member.getPw().equals("")) {
			result = false;
		}
							
		return result;
		
	}
	//checkNull end

	

	

	
	
}
