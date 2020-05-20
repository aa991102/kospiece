package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import dao.BoardDAO;
import dto.FreeBoardVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//5.8 write메소드 작성, Dao의 insert문과 연동(Dao -> DB)
public class WriteBoardService {

	BoardDAO boardwriteDao = new BoardDAO();

//	public int write(WriteRequest writeReq) {
	public void write(FreeBoardVO board) {
		System.out.println("WriteBoardService.write() 호출");

		// 파라미터 전달
		String Id = board.getFid();
		String Horsehead = board.getHorsehead();
		String Nickname = board.getNickname();
		String Title = board.getTitle();
		String Content = board.getContent();
		Date Uploaddate = board.getUploaddate();
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
		//파라미터 전달받기
			FreeBoardVO boardVO = 
					new FreeBoardVO(Id, Horsehead, Nickname, Title, Content , Uploaddate);
			
			// Dao의 insert문 실행
			boardwriteDao.insert(conn, boardVO);

			conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public void validate(Map<String, Boolean> errors, FreeBoardVO board) {
		if (board.getTitle().trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}else if(board.getContent().trim().isEmpty()) {
			errors.put("content", Boolean.TRUE);
		}
	}
}
