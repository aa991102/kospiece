package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import dto.FreeBoardVO;
import dto.SessionffbhVO;
import jdbc.JdbcUtil;

public class SessionffbhDAO {
	PreparedStatement pstmt = null;
	ResultSet rs  = null;
	FreeBoardVO board = null;

	public void insert(Connection conn,SessionffbhVO sessionVO)
		throws SQLException {
		System.out.println("sessionDAO.insert()호출");
		String sql = "INSERT INTO sessionffbh(fno, sessionid, accesstime) " + 
				     " VALUES(?,?,?)";
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,sessionVO.getFno());
		pstmt.setString(2,sessionVO.getSessionid());
		pstmt.setTimestamp(3,toTimestamp(sessionVO.getAccesstime()));
		pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}

	public Date select(Connection conn, int fno, String sessionid) throws SQLException{
		System.out.println("세션DAO-select()호출");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql ="SELECT accesstime from sessionffbh where fno=? and sessionid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fno);
			pstmt.setString(2, sessionid);
			rs = pstmt.executeQuery();
			if (rs.next()) { //등록된 게시물이 존재하면
				return rs.getTimestamp(1);
			}
			return new Date("2019/01/01/00:00:00");
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int delete(Connection conn, String sessionid) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete from sessionffbh where sessionid=?");
			pstmt.setString(1, sessionid);
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
}
