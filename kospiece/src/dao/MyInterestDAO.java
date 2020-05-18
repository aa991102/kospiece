package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dto.MyInterestVO;
import dto.StockVO;

public class MyInterestDAO {

	PreparedStatement pstmt = null;
	ResultSet rs  = null;
	MyInterestVO myInterest = null;
	StockDAO stockDao = null;
	StockVO stock = new StockVO();
	List<String> snoList = null;
	
	//회원번호로 관심 회사번호 목록 가져오기
	public List<String> selectSno(Connection conn, int mno) 
			throws SQLException {
		System.out.println("MyInterestDAO-selectSno호출="+mno);
		
		String sql = "SELECT sno FROM interest WHERE mno = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mno);
		rs = pstmt.executeQuery();
		
		if( rs.next() ) {
			snoList = new ArrayList<String>();
			do {
				snoList.add(rs.getString("sno"));
				
			}while(rs.next());
			//System.out.println("conn="+conn);
			//System.out.println("selectSno-rs="+rs.toString());
			//System.out.println("selectSno-snoList="+snoList.toString());
			
			return snoList;
			
		}else {
			return Collections.emptyList();
		}
	}
	
	
	//관심주식 추가
	public void insertBySno(Connection conn, int mno, String sno) throws SQLException {
		
		String sql = "INSERT INTO Interest(mno,sno) " + 
				     " VALUES(?,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,mno);
		pstmt.setString(2,sno);
		
		pstmt.executeUpdate();
	}
	
	
	//관심주식 삭제
	public void delete(Connection conn,int mno, String sno) throws SQLException {
		
		String sql = "DELETE FROM interest WHERE mno=? AND sno=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mno);
		pstmt.setString(2, sno);
		pstmt.executeUpdate();
	}
}
