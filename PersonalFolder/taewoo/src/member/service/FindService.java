package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDAO;
import dto.MemberVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class FindService {

	Connection conn;
	private MemberDAO memberdao = new MemberDAO();
	
	
	public String findid(String mname, String mmail) {
		
		
		MemberVO member;
		try {
			member = memberdao.findMemberByColumn(conn=ConnectionProvider.getConnection(), "mname", mname);
			if(member == null) {
				return "존재하지 않는 회원 입니다.";
			}else if(member.getMail().equals(mmail)){
				return member.getId();
			}else {
				return "존재하지 않는 회원 입니다.";
			}
		} catch (SQLException e) {
			System.out.println("FindService findid errors");
			e.printStackTrace();
			return "존재하지 않는 회원 입니다.";
		}finally {
			JdbcUtil.close(conn);
		}
	
		
	}


	public boolean validateMember(String mname, String mmail, String mid) {
		
		MemberVO member;
		
		try {
			member = memberdao.findMemberByColumn(conn=ConnectionProvider.getConnection(), "mname", mname);
			if(member == null) {
				return false;
			}else if(member.getMail().equals(mmail)&& member.getId().equals(mid)){
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			System.out.println("FindService findid errors");
			e.printStackTrace();
			return false;
		}finally {
			JdbcUtil.close(conn);
		}
		
		
		
	}

}
