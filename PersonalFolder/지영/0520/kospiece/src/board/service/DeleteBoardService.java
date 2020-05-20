package board.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BoardDAO;
import util.ConnectionProvider;

public class DeleteBoardService {
	private BoardDAO boardDao = new BoardDAO();

	public int deleteBoard(int fno) {
		System.out.println("deleteBoard진입");
		try (Connection conn = ConnectionProvider.getConnection()) {
			return boardDao.delete(conn, fno);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
