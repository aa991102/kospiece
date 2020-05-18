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

	public void delete(Connection conn, int commentNum)  throws SQLException{
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
				rs.getString("fccontent"), rs.getTimestamp("fcdate"));
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

}

