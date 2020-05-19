package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


public class DdateStockDAO {
	
	public String getChartinfo(Connection conn, String sno) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from datestock where sno=? order by ddate desc limit 100";
		String data ="";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sno);
			rs = pstmt.executeQuery();
			rs.last();
			
			while(rs.previous()) {
				data = data+"['"
						+covertType(rs.getDate("ddate"))+"',"
						+rs.getInt("drow")+","
						+rs.getInt("dstart")+","
						+rs.getInt("dend")+","
						+rs.getInt("dhigh")+"],";
			}
			System.out.println(data);
			return data;
			
		} catch (SQLException e) {
			System.out.println("ChartDAO getChartinfo error");
			e.printStackTrace();
			return null;
		}
		
	}
	
	private String covertType(Date date) {
		String day = date.toString().substring(5);
		day = day.substring(0, 2)+"-"+day.substring(3)+"";
		System.out.println(day);
		return day;
	}
	
}
