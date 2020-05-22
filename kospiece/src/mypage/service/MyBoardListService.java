package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BoardDAO;
import dto.FreeBoardVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MyBoardListService {
	BoardDAO boardDao=new BoardDAO();
	List<FreeBoardVO> boardList=null;
	
	int size=10;
	
	//get방식일 때 서비스(전체 내 게시글 리스트 출력)
	public MyBoardPage boardListService(int page,String fid) {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			int total=boardDao.selectCountByFid(conn, fid);
			System.out.println("total="+total);
			boardList=boardDao.selectByFid(conn, (page-1)*size, size, fid);
			
			
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
	public MyBoardPage boardListService(int page,String column, String value, String fid) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			int total = boardDao.selectedCountByFid(conn,column,value,fid);
			System.out.println("total="+total);
			boardList = boardDao.selectedBoard(conn, column, value, fid, (page-1)*size, size);
			
			conn.commit(); //트랙잭션 반영
			
			return new MyBoardPage(total,page,size,boardList);
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
	//내 게시글 리스트 5개만 가져오기
	public List<FreeBoardVO> boardListService5(String fid) {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			boardList=boardDao.selectByFid5(conn,fid);
			System.out.println("boardListService5-boardList="+boardList);
			
			conn.commit(); //트랙잭션 반영
			
			return boardList;
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
