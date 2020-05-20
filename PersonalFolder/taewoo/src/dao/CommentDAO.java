package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.FreeCommentVO;
import dto.HateBoardVO;
import dto.LikeBoardVO;
import jdbc.JdbcUtil;

public class CommentDAO {
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	FreeCommentVO comment = null;

	public void insert(Connection conn, FreeCommentVO comment) throws SQLException {
		System.out.println("CommentDAO.insert()호출");
		String sql = "INSERT INTO freecomment(fno, fcmemnick, fccontent, fcdate) " + " VALUES(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getFno());
			pstmt.setString(2, comment.getNickname());
			pstmt.setString(3, comment.getContent());
			pstmt.setTimestamp(4, toTimestamp(comment.getUploaddate()));
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public int selectCount(Connection conn, int fno) throws SQLException {
		System.out.println("CommentDAO-selectCount()호출");
		try {
			String sql = "select  count(*) from  freecomment where fno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fno);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 등록된 게시물이 존재하면
				return rs.getInt(1); // 전체 게시물수 리턴
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<FreeCommentVO> select(Connection conn, int fno, int startRow, int size) throws SQLException {
		System.out.println("CommentDAO-select()호출");
		try {
			String sql = "SELECT * from freecomment where fno=? ORDER BY fcno DESC LIMIT ?,?";
			// 0부터 시작해야함 0,10,20 ...
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fno);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			List<FreeCommentVO> result = new ArrayList<>();
			while (rs.next()) {
				result.add(toFreeCommentVO(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void delete(Connection conn, int commentNum) throws SQLException {
		System.out.println("CommentDAO-delete()호출");
		String sql = "delete from freecomment where fcno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNum);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	private FreeCommentVO toFreeCommentVO(ResultSet rs) throws SQLException {
		return new FreeCommentVO(rs.getInt("fcno"), rs.getInt("fno"), rs.getString("fcmemnick"),
				rs.getString("fccontent"), rs.getTimestamp("fcdate"), rs.getInt("fclike"), rs.getInt("fchate"));
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public void likeCountInsert(Connection conn, LikeBoardVO likeVO) throws SQLException {
		System.out.println("CommentDao - 좋아요 증가 진입");
		String sql = "insert into likeboard(fcno, lmemid) values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, likeVO.getFcno());
			pstmt.setString(2, likeVO.getLmemid());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void likeCountDelete(Connection conn, LikeBoardVO likeVO) throws SQLException {
		System.out.println("CommentDao - 좋아요 감소 진입");
		String sql = "delete from likeboard where fcno=? and lmemid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, likeVO.getFcno());
			pstmt.setString(2, likeVO.getLmemid());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 좋아요 및 싫어요를 사전에 눌렀는지 확인하기 위해 LikeCountService에서 사용
	public int selectLikeBoardById(Connection conn, int commentNo, String id) throws SQLException {
		System.out.println("CommentDAO-selectLikeBoardById()호출");
		try {
			String sql = "select  count(*) from  likeboard where fcno=? and lmemid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 등록된 게시물이 존재하면
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 좋아요 및 싫어요를 사전에 눌렀는지 확인하기 위해 HateCountService에서 사용
	public int selectHateBoardById(Connection conn, int commentNo, String id) throws SQLException {
		System.out.println("CommentDAO-selectHateBoardById()호출");
		try {
			String sql = "select  count(*) from  hateboard where fcno=? and hmemid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 등록된 게시물이 존재하면
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// Like갯수를 세기 위한 메서드
	public int LikeCount(Connection conn, int commentNo) throws SQLException {
		System.out.println("CommentDAO-LikeCount()호출");
		try {
			String sql = "select count(*) from  likeboard where fcno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 등록된 게시물이 존재하면
				return rs.getInt(1); // 전체 게시물수 리턴
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void likeCountUpdate(Connection conn, LikeBoardVO likeVO) throws SQLException {
		System.out.println("CommentDAO-LikeCountUpdate()호출");
		String sql = "update freecomment set fclike = (select count(*) from likeboard where fcno=?) where fcno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, likeVO.getFcno());
			pstmt.setInt(2, likeVO.getFcno());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void hateCountDelete(Connection conn, HateBoardVO hateVO) throws SQLException {
		System.out.println("CommentDao - 싫어요 감소 진입");
		String sql = "delete from hateboard where fcno=? and hmemid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hateVO.getFcno());
			pstmt.setString(2, hateVO.getHmemid());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void hateCountInsert(Connection conn, HateBoardVO hateVO) throws SQLException {
		System.out.println("CommentDao - 싫어요 증가 진입");
		String sql = "insert into hateboard(fcno, hmemid) values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hateVO.getFcno());
			pstmt.setString(2, hateVO.getHmemid());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void hateCountUpdate(Connection conn, HateBoardVO hateVO) throws SQLException {
		System.out.println("CommentDAO-HateCountUpdate()호출");
		String sql = "update freecomment set fchate = (select count(*) from hateboard where fcno=?) where fcno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hateVO.getFcno());
			pstmt.setInt(2, hateVO.getFcno());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public int HateCount(Connection conn, int commentNo) throws SQLException {
		System.out.println("CommentDAO-HateCount()호출");
		try {
			String sql = "select count(*) from  hateboard where fcno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 등록된 게시물이 존재하면
				return rs.getInt(1); // 전체 게시물수 리턴
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

}
