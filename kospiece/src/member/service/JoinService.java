package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dao.MemberDAO;
import dto.MemberVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//p596
//JoinHandler에서  호출
//회원가입기능을 제공 => DAO연동
public class JoinService {

	private MemberDAO memberDao = new MemberDAO();
	Connection conn;
	
	//회원가입처리요청 p596-16 
	//JoinHandler에서  호출
	public void join(MemberVO membervo) {
		System.out.println("JoinService-join()호출");
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작

			memberDao.insert(conn,membervo);
			
			
			conn.commit(); //트랙잭션 반영
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}//end of join


	public MemberVO checkBy(String column, String value) {
		
		MemberVO member;
		
		try {
			 member = memberDao.findMemberByColumn(conn=ConnectionProvider.getConnection(), column,value);
			 if(member!=null) {
				 return member;
			 }else {
				 return null;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean checkNull(MemberVO member) {
		
		boolean result=true;
		
		if(member.getId().equals("")||
				member.getNickname().equals("")||
				member.getPw().equals("")||
				member.getName().equals("")||
				member.getMail().equals("")||
				member.getPhone().equals("")) {
			result = false;
		}
							
		return result;
		
	}
	
	
	public Map<String, String> checkDuplicate (MemberVO member){
		
		Map<String, String> errors = new HashMap<String, String>();
		
		if(checkBy("mid", member.getId())!=null) {
			errors.put("id", "중복되는 아이디 입니다.");
		}
		
		if(checkBy("mnick", member.getNickname())!=null) {
			errors.put("nick", "중되는 nickname 입니다.");
		}
		if(checkBy("mmail", member.getMail())!=null) {
			errors.put("mmail", "중복되는 e-mail 입니다.");
		}
		if(checkBy("mmail", member.getMail())!=null) {
			errors.put("mphone", "중복되는 전화번호 입니다.");
		}
		
		return errors;
		
	}

}//end of class






