package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.Statistics;
import dao.MemberDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class AdminService {

	MemberDAO adminDao = new MemberDAO();
	Statistics memberStatistics= new Statistics();
	int totalMember;
	
	public Statistics service() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작

			int totalMember=adminDao.selectTotalMember(conn);
			int todayMember=adminDao.selectTodayMember(conn);
			
			memberStatistics.setTotalMember(totalMember);
			memberStatistics.setTodayMember(todayMember);
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
