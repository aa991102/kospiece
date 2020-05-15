package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BoardDAO;
import dao.NoticeDAO;
import dto.FreeBoardVO;
import dto.NoticeVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.dto.BoardVO;

public class MyBoardListService {
	BoardDAO boardDao=new BoardDAO();
	List<FreeBoardVO> boardList=null;
	
	int size=10;
	
	//get방식일 때 서비스(전체 내 게시글 리스트 출력)
	public MyBoardPage boardListService(int page) {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			int total=boardDao.selectCount(conn);
			
			boardList=boardDao.select(conn,(page-1)*size,size);
			
			
			conn.commit(); //트랙잭션 반영
			
			return new MyBoardPage(total,page,size,boardList);
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//post방식일 때 서비스(선택된 조건의 내 게시글 리스트 출력)
	public MyBoardPage boardListService(int page,String column, String value, String fmemnick) {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			int total = boardDao.selectedCountByNick(conn,column,value,fmemnick);
			
			boardList = boardDao.selectedBoard(conn, column, value, fmemnick, page, total);
			
			conn.commit(); //트랙잭션 반영
			
			return new MyBoardPage(total,page,size,boardList);
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
}
