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
	public StockVO stockResultSet(ResultSet rs) throws SQLException{
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
	
	//회사번호리스트로 주식정보 불러오기
	public List<StockVO> selectStocks(Connection conn, List<String> snoList) 
			throws SQLException {
		System.out.println("StockDAO-selectStocks호출="+snoList);
		
		String sql = "SELECT * from stock WHERE sno = ? ";
		List<StockVO> stocklist= new ArrayList<StockVO>();
		
		//snoList 존재하면
		if(snoList != null) {
			//snoList의 값 하나씩 접근해 sql문 돌리기
			
			for (String sno : snoList) { 
				System.out.println("selectStocks-sno="+sno);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sno);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					System.out.println("stockResultSet(rs)"+stockResultSet(rs));
					stocklist.add(stockResultSet(rs));
				}
			}
			
			System.out.println("selectStocks-stocklist="+stocklist.toString());
			
			return stocklist;
		
		//snoList 존재 안하면
		}else {
			return Collections.emptyList();
		}
	}
	
	//전체 주식정보를 특정컬럼을 기준으로 정렬해서 불러오기
	public List<StockVO> selectAllStock
	(Connection conn,String field,String column,String orderBy) throws SQLException {

		String sql="";
		
		if(field=="all"||field.contentEquals("all")) {
			sql = "select * from stock order by "+column+" "+orderBy;
			pstmt = conn.prepareStatement(sql);
		}else {
			sql = "select * from stock where sfield=? order by "+column+" "+orderBy;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, field);
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
	
	//업종 종류 가져오기
	public List<String> selectField(Connection conn) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT sfield FROM stock";
		
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			
			List<String> field=new ArrayList<String>();
			
			do{
				field.add(rs.getString("sfield"));
			}while(rs.next());
			return field;
		}else {
			return Collections.emptyList();
		}
	}

	//특정 회사이름을 이용한 회사정보검색
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
