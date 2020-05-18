package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.FreeBoardVO;
import jdbc.JdbcUtil;

public class BoardDAO {
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs  = null;
	FreeBoardVO board = null;

	//5.8 insert
	public void insert(Connection conn,FreeBoardVO board)
		throws SQLException {
		System.out.println("BoardWriteDAO.insert()호출");
		String sql = "INSERT INTO freeboard(fclass, fmemnick, ftitle, fcontent, fdate, fhit) " + 
				     " VALUES(?,?,?,?,?,0)";
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,board.getHorsehead());
		pstmt.setString(2,board.getNickname());
		pstmt.setString(3,board.getTitle());
		pstmt.setString(4,board.getContent());
		pstmt.setTimestamp(5,toTimestamp(board.getUploaddate()));
		pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	//5.13 selectCount()
	public int selectCount(Connection conn) throws SQLException {
		System.out.println("BoardDAO-selectCount()호출");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select  count(*) from  freeboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { //등록된 게시물이 존재하면
				return rs.getInt(1); //전체 게시물수 리턴
			}
			return 0; 
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public List<FreeBoardVO> select(Connection conn, int startRow, int size) throws SQLException{
		System.out.println("BoardDAO-select()호출");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql ="SELECT * from freeboard order by fno desc LIMIT ?, ?"; //0부터 시작함
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
<<<<<<< Updated upstream
			pstmt.setInt(2, size);;
=======
			pstmt.setInt(2, size);
>>>>>>> Stashed changes
			rs = pstmt.executeQuery();
			List<FreeBoardVO> result = new ArrayList<>();
			while (rs.next()) {
				result.add(toFreeBoardVO(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//5.14 selectById
	public FreeBoardVO selectById(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from freeboard where fno = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			FreeBoardVO boardVO = null;
			if(rs.next()) {
				boardVO = toFreeBoardVO(rs);
			}
			return boardVO;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public void increaseHit(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("UPDATE freeboard SET fhit = fhit+1 WHERE fno=?");
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public int update(Connection conn, int fno, String horsehead, String title, String content) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update freeboard set fclass=?, ftitle=?, fcontent=? where fno=?");
			pstmt.setString(1, horsehead);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setInt(4, fno);
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public int delete(Connection conn, int fno) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete from freeboard where fno=?");
			pstmt.setInt(1, fno);
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private FreeBoardVO toFreeBoardVO(ResultSet rs) throws SQLException{
		return new FreeBoardVO(
				rs.getInt("fno"), rs.getString("fclass"), rs.getString("fmemnick"), rs.getString("ftitle"),
				rs.getString("fcontent"), toDate(rs.getTimestamp("fdate")), rs.getInt("fhit"));
	}
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
<<<<<<< Updated upstream

=======
	 
	 private Timestamp toTimestamp(Date date) { return new
			 Timestamp(date.getTime()); }
	
>>>>>>> Stashed changes

}
