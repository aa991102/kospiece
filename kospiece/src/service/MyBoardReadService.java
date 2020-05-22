package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BoardDAO;
import dto.FreeBoardVO;
import util.ConnectionProvider;

public class MyBoardReadService {
	
	private BoardDAO boardDao = new BoardDAO();
	
	
	public FreeBoardVO getMyBoard(int fno) {
		System.out.println("MyBoardReadService의 getMyBoard메소드 진입");
		try(Connection conn = ConnectionProvider.getConnection()){
			FreeBoardVO boardVO = boardDao.selectById(conn, fno);
			if(boardVO == null) {
				throw new ArticleNotFoundException();
			}
			return boardVO;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
