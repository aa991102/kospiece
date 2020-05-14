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
	
	public List<NoticeVO> noticeListService(String column, String value) {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			if(column==""||value==""||column==null||value==null) {
				noticelist=noticeDao.selectAllNotice(conn);
			}else {
				noticelist=noticeDao.selectedNotice(conn,column,value);
			}
			
			conn.commit(); //트랙잭션 반영
			
			return noticelist;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}


}
