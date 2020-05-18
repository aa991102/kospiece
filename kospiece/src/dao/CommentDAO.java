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
	ResultSet rs  = null;
	FreeCommentVO comment = null;

	public void insert(Connection conn,FreeCommentVO comment)
		throws SQLException {
		System.out.println("CommentDAO.insert()호출");
		String sql = "INSERT INTO freecomment(fno, fcmemnick, fccontent, fcdate) " + 
				     " VALUES(?,?,?,?)";
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,comment.getFno());
		pstmt.setString(2,comment.getNickname());
		pstmt.setString(3,comment.getContent());
		pstmt.setTimestamp(4,  toTimestamp(comment.getUploaddate()));
		pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectCount(Connection conn) throws SQLException {
		System.out.println("CommentDAO-selectCount()호출");
		try {
			String sql = "select  count(*) from  freecomment";
			pstmt = conn.prepareStatement(sql);
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

	public List<FreeCommentVO> select(Connection conn, int startRow, int size) throws SQLException {
		System.out.println("CommentDAO-select()호출");
		try {
			String sql = "SELECT * from freeboard order by fno desc LIMIT ?, ?"; // 0부터 시작함
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
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
	
	private FreeCommentVO toFreeCommentVO(ResultSet rs) throws SQLException {
		return new FreeCommentVO(rs.getInt("fcno"), rs.getInt("fno"),
				rs.getString("fcmenick"), rs.getString("fccontent"), rs.getTimestamp("fcdate"));
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
}
