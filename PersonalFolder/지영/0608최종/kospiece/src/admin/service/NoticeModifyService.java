package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.NoticeDAO;
import dto.NoticeVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class NoticeModifyService {
	
	NoticeDAO noticeDAO = new NoticeDAO();
	NoticeVO notice = new NoticeVO();

	public NoticeVO selectNotice(int no) {
		
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			notice=noticeDAO.selectNoticeDetail(conn,no);
			
			conn.commit(); //트랙잭션 반영
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
		return notice;
	}

	public void writeNotice(int no,String title, String content) {
		
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			noticeDAO.updateNotice(conn,no,title,content);
			
			conn.commit(); //트랙잭션 반영
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
			
	}
}
