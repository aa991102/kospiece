package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import dto.FreeBoardVO;
import jdbc.JdbcUtil;

public class BoardWriteDAO {
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs  = null;
	FreeBoardVO board = null;

	//5.8 insert문 작성
	public void insert(Connection conn,FreeBoardVO board)
		throws SQLException {
		System.out.println("BoardWriteDAO.insert()호출");
		String sql = "INSERT INTO freeboard(fmemnick, ftitle, fcontent, fdate, fhit) " + 
				     " VALUES(?,?,?,?,0)";
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,board.getNickname());
		pstmt.setString(2,board.getTitle());
		pstmt.setString(3,board.getContent());
		pstmt.setTimestamp(4,toTimestamp(board.getUploaddate()));
		
		pstmt.executeUpdate();
		
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
}
