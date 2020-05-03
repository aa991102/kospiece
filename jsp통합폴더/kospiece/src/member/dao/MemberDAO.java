package member.dao;

import java.sql.*;

import dto.MemberVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}


	public int insertUser(MemberVO userVO) {
	int result = 0;
	String sql = "insert into user(id, pw,name,email,nickname,phone) value (?,?,?,?,?,?)";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	  
	try {
		conn = ConnectionProvider.getConnection();
	    pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, userVO.getId());      
	    pstmt.setString(2, userVO.getPw());
	    pstmt.setString(3, userVO.getName());
	    pstmt.setString(4, userVO.getEmail());
	    pstmt.setString(5, userVO.getNickname());
	    pstmt.setString(6, userVO.getPhone());
	    result = pstmt.executeUpdate();
	  } catch (Exception e) {
	    e.printStackTrace();
	  } finally {
	    JdbcUtil.close(pstmt);
	    JdbcUtil.close(conn);
  	}
  	return result;
	}
}