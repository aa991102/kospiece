package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDAO;
import dto.MemberVO;
import jdbc.JdbcUtil;
import util.ConnectionProvider;

public class InfoUpdateService {
	
	private MemberDAO memberDao = new MemberDAO();
	
	//InfoUpdatePwCheckHandler에서 사용
	//비밀번호 맞는지 확인
	public void checkPassword(String userId,String passCheck) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MemberVO member = memberDao.selectById(conn, userId);
			
			if(member == null) {
				throw new MemberNotFoundException();
			}
			
			if(!member.matchPassword(passCheck)) {
				throw new InvalidPasswordException();
			}
			
			conn.commit();
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}
	}
	
	//InfoUpdateHandler에서 사용
	//회원정보 수정
	public MemberVO infoUpdate(String userId,String changeNick,String changeMail,String changePhone) throws Exception {
		
		System.out.println("InfoUpdateService-infoUpdate");
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MemberVO member = memberDao.selectById(conn, userId);
			
			//동일한 닉네임/이메일/전화번호가 있는 경우 해당 Exception 던짐
			String mid1 = memberDao.selectByNick(conn, userId, changeNick);
			if(mid1 != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateNickException();
			}		
			String mid2 = memberDao.selectByMail(conn, userId, changeMail);
			if(mid2 != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateMailException();
			}
			String mid3 = memberDao.selectByPhone(conn, userId, changePhone);
			if(mid3 != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicatePhoneException();
			}
			
			//변경된 회원정보 저장
			member.changeInfo(changeNick, changeMail, changePhone);
			//회원정보 변경 쿼리 실행
			memberDao.UpdateInfos(conn, changeNick, changeMail, changePhone, userId);
			conn.commit();
			
			return member;
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
			
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	
}
