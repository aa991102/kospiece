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
	
	//주식정보 전부 불러오기
	public List<StockVO> selectAllStock(Connection conn) throws SQLException {
	
		String sql = "select * from stock";
		
		pstmt = conn.prepareStatement(sql);
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
}
