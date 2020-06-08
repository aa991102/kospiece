package comment.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CommentDAO;
import dto.HateBoardVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class HateCountService {
	CommentDAO commentDao = new CommentDAO();

	public void increaseCount(int commentNo, String id) {
		System.out.println("싫어요 서비스의 increaseCount메서드 호출");
		Connection conn = null;

		HateBoardVO hateVO = new HateBoardVO(commentNo, id);
		System.out.println("hate count service의 전달받은 객체"+hateVO);
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//5.18 
			// 1. 좋아요를 눌렀는지 파악하여 분기(좋아요 증가, 감소 메서드로 진입)
			// 2. likeboard, hateboard테이블에서 -> freecomment테이블 로 update
			if (validate(conn, hateVO)==1) { // 이미 좋아요를 눌렀다면(등록된 게시물이 존재하면)
				System.out.println("싫어요를 누른 기록이 있는가? = "+validate(conn, hateVO));
				commentDao.hateCountDelete(conn, hateVO);
				commentDao.hateCountUpdate(conn, hateVO);
			} else { // 싫어요를 안눌렀다면
				System.out.println("싫어요를 누른 기록이 있는가? = "+validate(conn, hateVO));
				commentDao.hateCountInsert(conn, hateVO);
				commentDao.hateCountUpdate(conn, hateVO);
			}
			conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int Counthate(int commentNo) {
		System.out.println("좋아요 서비스의 Count메서드 호출");
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int HateCount = commentDao.HateCount(conn, commentNo);
			return HateCount;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private int validate(Connection conn, HateBoardVO hateVO) throws SQLException {
		return commentDao.selectHateBoardById(conn, hateVO.getFcno(), hateVO.getHmemid());
	}

}
