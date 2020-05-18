package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

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
		String sql = "INSERT INTO freecomment(fno, fcmemnick, fccontent) " + 
				     " VALUES(?,?,?)";
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,comment.getFno());
		pstmt.setString(2,comment.getNickname());
		pstmt.setString(3,comment.getContent());
		pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	/*
	 * private FreeCommentVO toFreeCommentVO(ResultSet rs) throws SQLException{
	 * return new FreeCommentVO(); }
	 */
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
}
