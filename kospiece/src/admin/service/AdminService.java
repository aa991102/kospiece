package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.model.Statistics;
import admin.model.Visitor;
import dao.AdminDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class AdminService {

	AdminDAO adminDao = new AdminDAO();
	Statistics memberStatistics= new Statistics();
	List<Visitor> visitor=new ArrayList<Visitor>();
	
	//today,total 통계데이터 구하는 메서드
	public Statistics staticService() {
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
	
	//visitor 통계데이터 구하는 메서드	
	public List<Visitor> visitService(String term) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			if(term.equals("week")) {
				visitor=adminDao.selectWeekVisitor(conn);
			}else if(term.equals("month")) {
				visitor=adminDao.selectMonthVisitor(conn);
			}else if(term.equals("year")) {
				visitor=adminDao.selectYearVisitor(conn);
			}
			
			conn.commit(); //트랙잭션 반영
			
			return visitor;
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
}
