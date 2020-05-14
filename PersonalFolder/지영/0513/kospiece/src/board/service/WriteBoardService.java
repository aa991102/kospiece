package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import dao.BoardDAO;
import dto.FreeBoardVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//5.8 write메소드 작성, Dao의 insert문과 연동(Dao -> DB)
public class WriteBoardService {

	BoardDAO boardwriteDao = new BoardDAO(); 
	
//	public int write(WriteRequest writeReq) {
	public int write(FreeBoardVO board) {
		System.out.println("WriteBoardService.write() 호출");
		
		// 파라미터 전달
		String Nickname = board.getNickname();
		String Title = board.getTitle();
		String Content = board.getContent();
		Date Uploaddate = board.getUploaddate();
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
		//파라미터 전달받기
			FreeBoardVO freeboardVO = 
					new FreeBoardVO(Nickname, Title, Content , Uploaddate);
			
		//Dao의 insert문 실행
			boardwriteDao.insert(conn, freeboardVO);
			
			conn.commit();
			
			return freeboardVO.getFno();
			
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
