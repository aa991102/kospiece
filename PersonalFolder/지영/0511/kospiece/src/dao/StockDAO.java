package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dto.StockVO;

public class StockDAO {
	
	PreparedStatement pstmt = null;
	ResultSet rs  = null;
	
	
	//stock객체를 셋팅
	private StockVO stockResultSet(ResultSet rs) throws SQLException{
		StockVO stockvo=new StockVO();
		stockvo.setNo(rs.getString("sno"));
		stockvo.setName(rs.getString("sname"));
		stockvo.setField(rs.getString("sfield"));
		stockvo.setDetail(rs.getString("sdetail"));
		stockvo.setPrice(rs.getInt("sprice"));
		stockvo.setDayrate(rs.getString("sdayrate"));
		stockvo.setChangerate(rs.getFloat("schangerate"));
		stockvo.setVolume(rs.getString("svolume"));
		stockvo.setDealprice(rs.getString("sdealprice"));
		stockvo.setTotal(rs.getInt("stotal"));
		stockvo.setHigh52(rs.getString("shigh52"));
		return stockvo;
	}
	
	//전체 주식정보를 특정컬럼을 기준으로 정렬해서 불러오기
	public List<StockVO> selectAllStock
	(Connection conn,String field,String column,String orderBy) throws SQLException {

		String sql="";
		
		System.out.println(field);
		
		if(field=="all"||field.contentEquals("all")) {
			sql = "select * from stock order by "+column+" "+orderBy;
			pstmt = conn.prepareStatement(sql);
		}else {
			sql = "select * from stock where sfield=? order by "+column+" "+orderBy;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, field);
			System.out.println(pstmt);
		}
		
		
		
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			
			List<StockVO> stocklist=new ArrayList<StockVO>();
			
			do{
				stocklist.add(stockResultSet(rs));
			}while(rs.next());
			return stocklist;
		}else {
			return Collections.emptyList();
		}
	}

	public StockVO selectByName(Connection conn, String sname) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM stock WHERE sname=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return stockResultSet(rs);
				
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

}
