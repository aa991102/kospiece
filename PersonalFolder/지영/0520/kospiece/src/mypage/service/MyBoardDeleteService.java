package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import dao.BoardDAO;
import dao.MyInterestDAO;
import dao.StockDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
	
	//1. 관심 회사번호 check한 값을 List(sno)에 저장
	// 2. List에 저장된 관심회사번호를 삭제
public class MyBoardDeleteService {
	
	public void myBoardDeleteService(String[] fnoArray) {
		System.out.println("myInterestDeleteService-snameArray=" + Arrays.toString(fnoArray));
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			BoardDAO myBoardDao = new BoardDAO();
			StockDAO stockDao = new StockDAO();
			if(fnoArray !=null) {
				for (String fnoString : fnoArray){
					int fno = Integer.parseInt(fnoString);
					System.out.println("fno는!"+fno);
					
					myBoardDao.delete(conn, fno);
				}
			}
			
			conn.commit(); //트랙잭션 반영
			
		}catch(SQLException e) {
			System.out.println("myBoardDeleteService-SQLException="+e);
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
			
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
