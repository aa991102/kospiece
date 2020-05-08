package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.DAO.MemberDAO;
import member.MODEL.Member;

//p620
//암호 변경처리 클래스
/* 암호변경 실패 원인 2가지
 * - 암호를 변경할 회원데이터가 존재하지 않는 경우  => MemberNotFoundException
 * - 현재 암호가 일치하지 않는 경우 			 => InvalidPasswordException
 */

public class ChangePasswordService {
	
	MemberDAO memberDao = new MemberDAO();
	
	//p621 15
	/* db의 아래 작업을 진행하는 함수 호출예정
	 * UPDATE  member
	   SET		name=?, password = ?
	   WHERE   memberid = ?; */
	
	//비밀번호(,이름)변경
	public void changePassword(String id, String newPwd, String curPwd) {
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); //트랜잭션시작
			
			//p621 21
			//암호를 변경할 회원데이터가 존재하는지 파악
			Member member = memberDao.selectById(conn, id);
			
			
			//암호를 변경할 회원데이터가 존재하지 않는 경우
			if(member==null) { 
				throw new MemberNotFoundException();
			}
			
			//현재 암호가 일치하지 않는 경우
			if(!member.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			
			
			//memberDao.update(Connection conn,name,newPwd,curPwd);
			member.changePassword(newPwd); //p619 20
			
			memberDao.update(conn,member);
			
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랜잭션 rollback
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}

	}
	
}
