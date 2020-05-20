package comment.service;

import java.sql.Connection;
import java.sql.SQLException;


import dao.CommentDAO;
import util.ConnectionProvider;

public class DeleteCommentService {

	CommentDAO commentDao = new CommentDAO();
	public void delete(int commentNum) {
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			commentDao.delete(conn, commentNum);
			System.out.println(commentNum+"번 댓글 삭제 성공");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
