package json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;

public class trans 
{
	public static void main(String args) {
	
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		Connection conn = null;
	try 
		{
			conn = ConnectionProvider.getConnection();
		}
	catch(SQLException e)
		{
			throw new RuntimeException();
		}
	String sql = "SELECT DISTINCT sdetail FROM stock";
	try {
		pstmt = conn.prepareStatement(sql);
	
	rs = pstmt.executeQuery();
	
	if( rs.next() )
	{ 
		System.out.println(rs.getString("sdetail"));
	}
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
}
}

