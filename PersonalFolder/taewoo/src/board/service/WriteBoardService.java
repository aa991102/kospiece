package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import dao.BoardWriteDAO;
import dto.FreeBoardVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//5.8 write메소드 작성, Dao의 insert문과 연동(Dao -> DB)
public class WriteBoardService {

	BoardWriteDAO boardwriteDao = new BoardWriteDAO(); 
	
	public int write(WriteRequest writeReq) {
		System.out.println("WriteBoardService.write() 호출");
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
		//DB insert를 위한 파라미터 전달받기
			FreeBoardVO freeboardVO = new FreeBoardVO(
					writeReq.getNickname(), 
					writeReq.getTitle(),
					writeReq.getContent(),
					new Date());
			
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
