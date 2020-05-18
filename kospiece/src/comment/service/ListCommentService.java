package comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.CommentDAO;
import dto.FreeCommentVO;
import util.ConnectionProvider;

public class ListCommentService {

	private CommentDAO commentDao = new CommentDAO();
	private int size = 10; // 한 페이지에 보여지는 게시글 수


	public ListComment getComments(int fno, int commentPageNo) {
		System.out.println("ListCommentService의 getComments진입");
		try (Connection conn = ConnectionProvider.getConnection()) {

			int total = commentDao.selectCount(conn, fno); // 전체 댓글의 수 구함
			
			List<FreeCommentVO> content = 
					commentDao.select(conn, fno, getStartPage(commentPageNo, size), size);
			System.out.println("쿼리를 실행한 뒤 content = "+content);
			
			return new ListComment(total, commentPageNo, size, content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private int getStartPage(int pageNo, int size) { // pageNo = 사용자가 선택한 페이지, total = 전체 게시글 수
		System.out.println("ListBoardService의 getStartPage 진입");
		// 시작페이지 설정
			// 1페이지(pageNo) = 최근 게시물순으로 n개(size)만큼 출력
			// 2페이지(pageNo) = 최근 게시물순으로 n번~n번 출력
			// .. 마지막페이지 = n개씩 끊어 마지막 남은 게시물 출력
		return size * (pageNo - 1);
	}

}
