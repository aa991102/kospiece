package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberVO;

public class MemberDAO {
	
	PreparedStatement pstmt = null;
	ResultSet rs  = null;
	MemberVO member = null;
	
	//회원가입 로직
	public void  insert(Connection conn,MemberVO mem)
		throws SQLException {
		System.out.println("MemberDAO-insert()호출");
		String sql = "INSERT INTO member(mid,mnick,mpw,mname,mmail,mphone) " + 
				     " VALUES(?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,mem.getId());
		pstmt.setString(2,mem.getNickname());
		pstmt.setString(3,mem.getPw());
		pstmt.setString(4,mem.getName());
		pstmt.setString(5,mem.getMail());
		pstmt.setString(6,mem.getPhone());
		pstmt.executeUpdate();
	}
	
	//로그인 로직
	public MemberVO selectById(Connection conn, String id) 
			throws SQLException {
		
		System.out.println("MemberDAO-selectById(id)호출="+id);
		
		String sql = "SELECT  mid,mnick,mpw " + 
					 "	FROM 	member " + 
					 "	WHERE	mid=?";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery();
		
		if( rs.next() ) { 
			
			String memberid = rs.getString("mid");
			String name     = rs.getString("mnick");
			String password = rs.getString("mpw");
			
			member = new MemberVO(memberid,name,password);	
		}
		return member;
	}
}






