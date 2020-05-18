package comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import dao.CommentDAO;
import dto.FreeCommentVO;
import util.ConnectionProvider;

public class WriteCommentService {
	CommentDAO commentdao = new CommentDAO();
	
	public int write(FreeCommentVO comment) {
		System.out.println("WriteCommentService.write() 호출");
		
		try(Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			
			//파라미터 전달받기, insert문 실행
			commentdao.insert(conn, comment);
			
			conn.commit();
			return comment.getFno();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void validate(Map<String, Boolean> errors, FreeCommentVO comment) {
		if (comment.getContent().trim().isEmpty()) {
			errors.put("Content", Boolean.TRUE);
		}
	}

}
