package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import dao.MyInterestDAO;
import dao.StockDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MyInterestDeleteService {
	
		// 1. 관심 회사번호 check한 값을 List(sno)에 저장
		// 2. List에 저장된 관심회사번호를 삭제
	
		public void myInterestDeleteService(int mno,String[] snameArray) {
			System.out.println("myInterestDeleteService-mno=" + mno+"삭제");
			System.out.println("myInterestDeleteService-snameArray=" + Arrays.toString(snameArray));
			
			Connection conn = null;
			
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);//트랜잭션 시작
				
				MyInterestDAO myInterestDao = new MyInterestDAO();
				StockDAO stockDao = new StockDAO();
				if(snameArray !=null) {
					for (String sname : snameArray){
						
						String sno = stockDao.selectSnoBySname(conn, sname);
						
						System.out.println("sname는!"+sname+"//sno는!"+sno);
						
						myInterestDao.delete(conn, mno, sno);
					}
				}
				
				conn.commit(); //트랙잭션 반영
				
			}catch(SQLException e) {
				System.out.println("myInterestDeleteService-SQLException="+e);
				JdbcUtil.rollback(conn); //트랙잭션 취소
				throw new RuntimeException(e);
				
			}finally {
				JdbcUtil.close(conn);
			}
			
		}
}
