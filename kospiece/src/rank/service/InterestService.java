package rank.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import dao.MyInterestDAO;

public class InterestService {
	
	MyInterestDAO interestDao = new MyInterestDAO();
	
	//관심주식을 추가하는 메서드
	public void plusService(int mno,String sno) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			interestDao.insertBySno(conn, mno, sno);
			
			conn.commit(); //트랙잭션 반영
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	//관심주식을 삭제하는 메서드
		public void deleteService(int mno,String sno) {
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);//트랜잭션 시작
				
				interestDao.delete(conn,mno,sno);
				
				conn.commit(); //트랙잭션 반영
				
			}catch(SQLException e) {
				JdbcUtil.rollback(conn); //트랙잭션 취소
				throw new RuntimeException(e);
			}finally {
				JdbcUtil.close(conn);
			}
			
		}
}
