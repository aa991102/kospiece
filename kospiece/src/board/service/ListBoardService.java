package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BoardDAO;
import dto.FreeBoardVO;
import util.ConnectionProvider;

public class ListBoardService {

	private BoardDAO boardDao = new BoardDAO();
	private int size = 10; //한 페이지에 보여지는 게시글 수
	
	public ListBoard getBoardPage(int pageNo) { //pageNo에는 선택한 페이지가 들어옴
		System.out.println("ListBoardService의 getBoardPage 진입");
		try(Connection conn = ConnectionProvider.getConnection()){
			
			int total = boardDao.selectCount(conn); //전체 게시글의 수 구함
			
     		List<FreeBoardVO> content = 
					boardDao.select(conn, getStartPage(pageNo, size), size); 
     		
     		System.out.println("쿼리에 들어가는 content 는? \n"+content);
     		
			return new ListBoard(total, pageNo, size, content);
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public ListBoard getSearchBoardPage(int pageNo, String column, String value) {
System.out.println("ListBoardService의 getSearchBoardPage 진입");
		
		try(Connection conn = ConnectionProvider.getConnection()){
			
			int total = boardDao.selectSearchCount(conn, column, value); //전체 게시글의 수 구함
			
     		List<FreeBoardVO> content = 
					boardDao.selectSearch(conn, getStartPage(pageNo, size), size, column, value); 
     		
     		System.out.println("쿼리에 들어가는 content 는? \n"+content);
     		
			return new ListBoard(total, pageNo, size, content);
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private int getStartPage(int pageNo, int size) { //pageNo  = 사용자가 선택한 페이지, total = 전체 게시글 수
		//시작페이지 설정
		//1페이지(pageNo) = 최근 게시물순으로 n개(size)만큼 출력
		//2페이지(pageNo) = 최근 게시물순으로 n번~n번 출력
		//.. 마지막페이지 = n개씩 끊어 마지막 남은 게시물 출력
		return size*(pageNo-1);
	}
}
