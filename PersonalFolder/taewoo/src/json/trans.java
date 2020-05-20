package json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.StockVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class trans 
{
	public static String Data() throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		Connection conn = ConnectionProvider.getConnection();
	
	
	String sql = "SELECT DISTINCT sdetail FROM stock";
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();

	//rs 갯수세기
	int rowcount = 0;
	if (rs.last()) {
		rowcount = rs.getRow();
		rs.beforeFirst(); 
	}
	System.out.println(rowcount);
	
	//업종추출
	String[] sdetail = new String[rowcount];
	int i = 0;
	while( rs.next() )
	{ 
		sdetail[i]=rs.getString("sdetail");
//		System.out.println(sdetail[i]);
		i++;
	}
	//데이터파싱
	String data = "[{name: \"kospi200\", children: [";
	for (int j=0 ; j<sdetail.length; j++)
	{
	    data = data + "{name: \"" + sdetail[j] + "\", children: [";
	    String sql1 = "select sname,schangerate,stotal from stock where sdetail=?";
	    pstmt = conn.prepareStatement(sql1);
	    pstmt.setString(1, sdetail[j]);
	    rs = pstmt.executeQuery();
	    
	    while (rs.next())
	    {
	    	StockVO s = new StockVO(rs.getString("sname"),rs.getFloat("schangerate"),rs.getInt("stotal"));
	    	String sname = s.getName();
	    	String schangerate = String.valueOf(s.getChangerate());
	    	String stotal = String.valueOf(s.getTotal());
	    	data = data + "{name: \"" + sname + "\", value:" + schangerate + ", size: " + stotal + "},";
	    }	  
	    data=data.substring(0, data.length()-1)+"]},"; 
	}
data=data.substring(0, data.length()-1)+"]}];";
//System.out.println(data);
JdbcUtil.close(conn);
return data;
	}

//	}
//	return
}



