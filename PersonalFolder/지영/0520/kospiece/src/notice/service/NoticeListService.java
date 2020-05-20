package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.NoticeDAO;
import dto.NoticeVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class NoticeListService {
	
	NoticeDAO noticeDao=new NoticeDAO();
	List<NoticeVO> noticelist=null;
	
	int size=10;
	
	//get방식일 때 서비스(전체 공지사항 리스트 출력)
	public NoticePage noticeListService(int page) {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			int total=noticeDao.selectCount(conn);
			
			noticelist=noticeDao.selectAllNotice(conn,(page-1)*size,size);
			
			
			conn.commit(); //트랙잭션 반영
			
			return new NoticePage(total,page,size,noticelist);
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//post방식일 때 서비스(선택된 조건의 공지사항 리스트 출력)
	public NoticePage noticeListService(int page,String column, String value) {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			int total=noticeDao.selectedCount(conn,column,value);
			
			noticelist=noticeDao.selectedNotice(conn,(page-1)*size,size,column,value);
			
			conn.commit(); //트랙잭션 반영
			
			return new NoticePage(total,page,size,noticelist);
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}

	


}
