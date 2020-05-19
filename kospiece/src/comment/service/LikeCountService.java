package comment.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CommentDAO;
import dto.LikeBoardVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class LikeCountService {

	CommentDAO commentDao = new CommentDAO();

	public void increaseCount(int commentNo, String id) {
		System.out.println("좋아요 서비스의 increaseCount메서드 호출");
		Connection conn = null;

		LikeBoardVO likeVO = new LikeBoardVO(commentNo, id);
		System.out.println("like count service의 전달받은 객체"+likeVO);
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//5.18 
			// 1. 좋아요를 눌렀는지 파악하여 분기(좋아요 증가, 감소 메서드로 진입)
			// 2. likeboard,hateboard테이블에서 -> freecomment테이블 로 update
			if (validate(conn, likeVO)==1) { // 이미 좋아요를 눌렀다면(등록된 게시물이 존재하면)
				System.out.println("좋아요를 누른 기록이 있는가? = "+validate(conn, likeVO));
				commentDao.likeCountDelete(conn, likeVO);
				commentDao.likeCountUpdate(conn, likeVO);
			} else { // 좋아요를 안눌렀다면
				System.out.println("좋아요를 누른 기록이 있는가? = "+validate(conn, likeVO));
				commentDao.likeCountInsert(conn, likeVO);
				commentDao.likeCountUpdate(conn, likeVO);
			}
			conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int Countlike(int commentNo) {
		System.out.println("좋아요 서비스의 Count메서드 호출");
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int LikeCount = commentDao.LikeCount(conn, commentNo);
			return LikeCount;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private int validate(Connection conn, LikeBoardVO likeVO) throws SQLException {
		return commentDao.selectLikeBoardById(conn, likeVO.getFcno(), likeVO.getLmemid());
	}

}
