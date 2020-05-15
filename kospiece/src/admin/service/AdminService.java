package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.AdminDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class AdminService {

	AdminDAO adminDao = new AdminDAO();
	Statistics memberStatistics= new Statistics();
	
	public Statistics service() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작

			int totalMember=adminDao.selectTotalMember(conn);
			int todayMember=adminDao.selectTodayMember(conn);
			int totalPost=adminDao.selectTotalPost(conn);
			int todayPost=adminDao.selectTodayPost(conn);
			
			memberStatistics.setTotalMember(totalMember);
			memberStatistics.setTodayMember(todayMember);
			memberStatistics.setTotalPost(totalPost);
			memberStatistics.setTodayPost(todayPost);
			conn.commit(); //트랙잭션 반영
			
			return memberStatistics;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
}
