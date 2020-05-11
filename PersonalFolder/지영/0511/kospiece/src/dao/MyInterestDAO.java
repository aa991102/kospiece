package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberVO;
import dto.MyInterestVO;

public class MyInterestDAO {

	PreparedStatement pstmt = null;
	ResultSet rs  = null;
	MyInterestVO myInterest = null;
	
	
	//select
	public void select(Connection conn, int mno) 
			throws SQLException {
		
		System.out.println("MyInterestDAO-select호출="+mno);
		
		String sql = "SELECT * FROM Interest WHERE mno = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mno);
		rs = pstmt.executeQuery();
		
		if( rs.next() ) {
			
		}
		
	}
	
	//관심주식 추가
	public void insert(Connection conn, int mno, String sno) throws SQLException {
		System.out.println("MyInterestVO-insert()호출");
		
		String sql = "INSERT INTO Interest(mno,sno) " + 
				     " VALUES(?,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,mno);
		pstmt.setString(2,sno);
		
		pstmt.executeUpdate();
	}
}
