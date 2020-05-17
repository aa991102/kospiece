package admin.listener;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dao.AdminDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

@WebListener
public class SessionListener implements HttpSessionListener {
	
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    	
    	System.out.print("세션시작-");
    	
    	Connection conn = null;
    	int todayCount;
    	int totalCount;
    	
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작

			// DAO 객체 생성
	        AdminDAO adminDao = new AdminDAO();
	         
	        // 전체 방문자 수 +1
	        adminDao.setVisitTotalCount(conn);
	         
	        // 오늘 방문자 수
	        todayCount = adminDao.getVisitTodayCount(conn);
	         
	        // 전체 방문자 수
	        totalCount = adminDao.getVisitTotalCount(conn);
	        
			conn.commit(); //트랙잭션 반영
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
    	
        HttpSession session = se.getSession();
         
        // 세션 속성에 담아준다.
        session.setAttribute("totalCount", totalCount); // 전체 방문자 수
        session.setAttribute("todayCount", todayCount); // 오늘 방문자 수
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    	
    	System.out.println("세션삭제");
    }
 
}