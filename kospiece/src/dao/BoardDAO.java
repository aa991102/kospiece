package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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
		String sql = "INSERT INTO freeboard(fid, fclass, fmemnick, ftitle, fcontent, fdate, fhit) " + 
				     " VALUES(?,?,?,?,?,?,0)";
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,board.getFid());
		pstmt.setString(2,board.getHorsehead());
		pstmt.setString(3,board.getNickname());
		pstmt.setString(4,board.getTitle());
		pstmt.setString(5,board.getContent());
		pstmt.setTimestamp(6,toTimestamp(board.getUploaddate()));
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
			pstmt.setInt(2, size);
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
				rs.getString("fcontent"), rs.getTimestamp("fdate"), rs.getInt("fhit"));
	}
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	//특정 회원의 전체 게시물을 가져오는 메서드
	 public List<FreeBoardVO> selectByNick(Connection conn, int startRow, int size,String fmemnick) throws SQLException {
		System.out.println("BoardDAO-selectByNick()호출");
		try {
			String sql = "SELECT * from freeboard where fmemnick=? order by fno desc LIMIT ?, ?"; // 0부터 시작함
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fmemnick);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			;
			rs = pstmt.executeQuery();
			List<FreeBoardVO> result = new ArrayList<>();
			while (rs.next()) {
				result.add(toFreeBoardVO(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	 
	//특정 회원의 전체 게시물 수를 구하는 메서드
	public int selectCountByNick(Connection conn,String fmemnick) throws SQLException {
		System.out.println("BoardDAO-selectCountByNick()호출");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select  count(*) from  freeboard where fmemnick = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fmemnick);
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
	
	//특정 회원의 특정조건의 게시글 수를 구하는 메서드
	public int selectedCountByNick(Connection conn,String column, String value,String fmemnick) throws SQLException {
		String sql = "select count(*) from freeboard where "+column+" like ? and fmemnick=?";
		
		value = "%"+value+"%"; //해당 조건을 포함하는 조건문으로 설정
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			pstmt.setString(2, fmemnick);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//조건이 선택된 게시글 전체보기
	public List<FreeBoardVO> selectedBoard
		(Connection conn, String column, String value, String fmemnick , int startRow, int size) 
			throws SQLException {
		
		List<FreeBoardVO> Boardlist=new ArrayList<FreeBoardVO>();
		
		String sql = "select * from freeboard where "+ column +" like ? and fmemnick = ? order by fno desc limit ?,? ";
		
		value="%"+value+"%"; //해당 조건을 포함하는 조건문으로 설정
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value);
		pstmt.setString(2, fmemnick);
		pstmt.setInt(3, startRow);
		pstmt.setInt(4, size);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			
			do{
				Boardlist.add(toFreeBoardVO(rs));
			}while(rs.next());
			
			return Boardlist;
		}else {
			return Collections.emptyList();
		}
	}
	
	//닉네임으로 정보 5개만 가져오기
	public List<FreeBoardVO> selectByNick5(Connection conn,String fmemnick) throws SQLException {
		System.out.println("BoardDAO-selectByNick5()호출");
		System.out.println("fmemnick="+fmemnick);
		try {
			String sql = "SELECT * from freeboard where fmemnick = ? order by fno desc LIMIT 0, 5";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fmemnick);
			rs = pstmt.executeQuery();
			
			List<FreeBoardVO> result = new ArrayList<>();
			while (rs.next()) {
				System.out.println("rs="+rs.toString());
				result.add(toFreeBoardVO(rs));
			}
			System.out.println("selectByNick-result="+result);
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int selectSearchCount(Connection conn, String column, String value) throws SQLException {
		System.out.println("BoardDAO-selectSearchCount()호출");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select  count(*) from  freeboard where "+column+" like '%"+value+"%'";
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
	
	public List<FreeBoardVO> selectSearch(
			Connection conn, int startPage, int size, String column, String value) throws SQLException {
		System.out.println("BoardDAO-selectSearch호출");
		try {
			String sql = "SELECT * from freeboard  where "+column+" like '%"+value+"%'  order by fno desc LIMIT ?, ?"; // 0부터 시작함
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startPage);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<FreeBoardVO> result = new ArrayList<>();
			while (rs.next()) {
				result.add(toFreeBoardVO(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
