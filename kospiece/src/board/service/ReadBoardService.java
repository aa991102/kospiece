package board.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BoardDAO;
import dto.FreeBoardVO;
import util.ConnectionProvider;

public class ReadBoardService {
	
	private BoardDAO boardDao = new BoardDAO();
	
	
	public FreeBoardVO getBoard(int fno, boolean uphit) {
		System.out.println("ReadBoardService의 getBoard메소드 진입");
		try(Connection conn = ConnectionProvider.getConnection()){
			FreeBoardVO boardVO = boardDao.selectById(conn, fno);
			if(boardVO == null) {
				throw new ArticleNotFoundException();
			}
			if(uphit) { 
				boardDao.increaseHit(conn, fno); //uphit 이 true면 조회수 증가
			}
			return boardVO;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
