package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MyInterestDAO;
import dao.StockDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//관심주식 - 해당 회원의 관심주식 리스트에 해당 회사가 존재하는지 확인하는 로직 추가!

public class MyInterestInsertService {
	
	Connection conn = null;
	
	//회원 번호 받아서 관심주식리스트에 해당하는 회사가 있으면 ~
	//회사코드가 회원의 관심주식리스트의 ~와 일치하면 ★ / 아닐경우 ☆
	
	
	//업체 이름 검색
	// - 일치하면 회사번호(sno) 반환/일치 안하면 null반환
	public String selectSnoBySname(String sname) {
		System.out.println("MyInterestInsertService-selectSnoBySname(sname)="+sname);
		
		StockDAO stockDAO = new StockDAO();
		
		try {
			conn=ConnectionProvider.getConnection();
			String sno = stockDAO.selectSnoBySname(conn, sname);
			
			//검색된 회사이름이 없는 경우 null반환
			if(sno == null) {
				return null;
			}
			
			return sno; 
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	
	//관심주식 추가
	public void insertMyInterest(int mno, String sno) {
		System.out.println("MyInterestInsertService-insertInterest(sno)="+sno);
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//Dao의 insert문 실행
			MyInterestDAO myInterestDao = new MyInterestDAO();
			
			myInterestDao.insertBySno(conn, mno, sno);
			
			conn.commit();
			
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			System.out.println("insertMyInterest 실패 ");
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
}
