package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.NoticeDAO;
import dto.NoticeVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class NoticeDetailService {

	NoticeDAO noticeDao=new NoticeDAO();
	NoticeVO notice=new NoticeVO();
	
	public NoticeVO selectNotice(int no) {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			noticeDao.increaseNoticeHit(conn,no);//조회수 증가
			
			notice=noticeDao.selectNoticeDetail(conn,no);//해당 게시글 정보 불러오기
			
			conn.commit(); //트랙잭션 반영
			
			return notice;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
}


