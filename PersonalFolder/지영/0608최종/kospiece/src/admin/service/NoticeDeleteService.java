package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.NoticeDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class NoticeDeleteService {

	public void deleteService(int no) {
		
		NoticeDAO noticeDao=new NoticeDAO();

		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작

			noticeDao.deleteNotice(conn,no);
			
			conn.commit(); //트랙잭션 반영
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
			
	}
}


