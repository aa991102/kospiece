package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDAO;
import dto.MemberVO;
import jdbc.JdbcUtil;
import util.ConnectionProvider;

public class ChangePasswordService {
	
	private MemberDAO memberDao = new MemberDAO();
	
	public void changePassword(String userId,String curPw,String newPw) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MemberVO member = memberDao.selectById(conn, userId);
			if(member == null) {
				throw new MemberNotFoundException();
			}
			
			if(!member.matchPassword(curPw)) {
				throw new InvalidPasswordException();
			}
			
			member.changePassword(newPw);
			memberDao.update(conn, member);
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
