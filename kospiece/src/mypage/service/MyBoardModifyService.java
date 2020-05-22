package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BoardDAO;
import dto.FreeBoardVO;
import jdbc.JdbcUtil;
import util.ConnectionProvider;

public class MyBoardModifyService {

	BoardDAO boardDao = new BoardDAO();
	
	public void modify(FreeBoardVO boardVO) {
		System.out.println("modify함수 실행");
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			System.out.println("전달받은 수정 파라미터 : boardVO"+boardVO);
			//쿼리문 실행
			boardDao.update(conn, 
					boardVO.getFno(), boardVO.getHorsehead(), 
					boardVO.getTitle(), boardVO.getContent());
			
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
		
	}

}
