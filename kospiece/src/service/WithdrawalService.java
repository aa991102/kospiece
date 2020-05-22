package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDAO;
import dto.MemberVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WithdrawalService {

	MemberDAO memberDao=new MemberDAO();

	public void deleteMember(String mid,String checkPw) {

			Connection conn = null;
			try {
				
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);//트랜잭션 시작
				
				MemberVO member = memberDao.selectById(conn, mid);
				
				if(member == null) {
					throw new MemberNotFoundException();
				}
				
				if(!member.matchPassword(checkPw)) {
					throw new InvalidPasswordException();
				}
				
				memberDao.deleteMember(conn,member.getNickname());
				
				conn.commit(); //트랙잭션 반영
				
			}catch(SQLException e) {
				JdbcUtil.rollback(conn); //트랙잭션 취소
				throw new RuntimeException(e);
			}finally {
				JdbcUtil.close(conn);
			}
	}
}
