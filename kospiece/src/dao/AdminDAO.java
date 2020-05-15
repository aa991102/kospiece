package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class AdminDAO {
	
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	/*1.AdminService(관리자페이지 홈화면)*/
	//전체 회원 수 구하는 메서드
	public int selectTotalMember(Connection conn) throws SQLException {
		String sql = "select count(*) from member";
		
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	//신규(오늘 가입한) 회원 수 구하는 메서드
	public int selectTodayMember(Connection conn) throws SQLException {
			String sql = "select count(*) from member where mdate=?";
			
			pstmt = conn.prepareStatement(sql);
			
			Calendar cal = Calendar.getInstance();
			 
			//현재 년도, 월, 일
			int year = cal.get ( Calendar.YEAR );
			int month = cal.get ( Calendar.MONTH );
			int date = cal.get ( Calendar.DATE );
			String today=year+"-"+(month+1)+"-"+(date+1);
			
			Date now=Date.valueOf(today);
			
			pstmt.setDate(1, now);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
	}

	//전체 게시글 구하는 메서드
	public int selectTotalPost(Connection conn) throws SQLException {
		String sql = "select count(*) from freeboard";
		
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		rs.next();
		return rs.getInt(1);
	}

	//오늘 업로드된 게시글 구하는 메서드
	public int selectTodayPost(Connection conn) throws SQLException {
		String sql = "select count(*) from freeboard where fdate>=?";
		
		pstmt = conn.prepareStatement(sql);
		
		Calendar cal = Calendar.getInstance();
		 
		//현재 년도, 월, 일
		int year = cal.get ( Calendar.YEAR );
		int month = cal.get ( Calendar.MONTH );
		int date = cal.get ( Calendar.DATE );
		String today=year+"-"+(month+1)+"-"+(date+1);
		
		Date now=Date.valueOf(today);
		
		pstmt.setDate(1, now);
		rs=pstmt.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
}




















