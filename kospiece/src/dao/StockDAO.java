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
		stockvo.setInterest(rs.getInt("mno"));
		return stockvo;
	}
	
	//stock객체를 셋팅2
	// (selectStocks()에서 사용할 것! --- MyInterestListService에서 사용
	// 위의 셋팅1에서  
	// stockvo.setInterest(rs.getInt("mno")); 
	// 만 제거
	public StockVO stockResultSet2(ResultSet rs) throws SQLException{
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
				//System.out.println("selectStocks-sno="+sno);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sno);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					//System.out.println("stockResultSet2(rs)"+stockResultSet2(rs));
					stocklist.add(stockResultSet2(rs));
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
	(Connection conn,int mno,String field,String column,String orderBy) throws SQLException {

		String sql="SELECT s.sno,s.sname,s.sfield,s.sdetail,s.sprice,s.sdayrate,"+
		" s.schangerate,s.svolume,s.sdealprice,s.stotal,s.shigh52,i.mno"+
		" FROM stock s"+
		" LEFT OUTER JOIN"+
		" (SELECT sno,mno FROM interest WHERE mno=?) AS i"+
		" ON s.sno=i.sno";
		
		if(field=="all"||field.contentEquals("all")) {
			sql = sql+ " order by "+column+" "+orderBy;
			pstmt = conn.prepareStatement(sql);
		}else {
			sql = sql+" where sfield=? order by "+column+" "+orderBy;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, field);
		}
		pstmt.setInt(1, mno);
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
	
	//특정회원의 특정회사정보만 가져오기
	public StockVO selectedStock(Connection conn,String sname,int mno) throws SQLException {

		String sql="SELECT s.sno,s.sname,s.sfield,s.sdetail,s.sprice,s.sdayrate,"+
		" s.schangerate,s.svolume,s.sdealprice,s.stotal,s.shigh52,i.mno"+
		" FROM stock s"+
		" LEFT OUTER JOIN"+
		" (SELECT sno,mno FROM interest WHERE mno=?) AS i"+
		" ON s.sno=i.sno"+
		" where s.sname=?";
		
		StockVO stock=null;
		System.out.println(mno+sname);
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, mno);
		pstmt.setString(2, sname);
		rs=pstmt.executeQuery();
		
		System.out.println(pstmt);
		
		if(rs.next()) {
			stock=stockResultSet(rs);
		}
		System.out.println(stock);
		return stock;
	}
	
	//업종 종류 가져오기
	public List<String> selectField(Connection conn) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT sfield FROM stock order by sfield";
		
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

	//같은 sdetail 업체 리스트 가져오기
	public ArrayList<StockVO> getListByDetail(Connection conn, String sno, String sdetail){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM stock where sdetail=?";
		
		ArrayList<StockVO> list = new ArrayList<StockVO>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sdetail);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//결과값 가져와서 본인꺼만 빼고 list에 담자.
				if(sno.equals(rs.getString("sno"))) {
				}else {
					list.add(new StockVO(rs.getString("sno"),
							rs.getString("sname"), 
							rs.getString("sfield"), 
							rs.getString("sdetail"), 
							rs.getInt("sprice"), 
							rs.getString("sdayrate"), 
							rs.getFloat("schangerate"), 
							rs.getString("svolume"), 
							rs.getString("sdealprice"), 
							rs.getInt("stotal"), 
							rs.getString("shigh52")));
				}
			}
			return list;
			
		} catch (SQLException e) {
			System.out.println("StockDAO getListByDetail error");
			e.printStackTrace();
			return null;
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
				return new StockVO(rs.getString("sno"),
						rs.getString("sname"), 
						rs.getString("sfield"), 
						rs.getString("sdetail"), 
						rs.getInt("sprice"), 
						rs.getString("sdayrate"), 
						rs.getFloat("schangerate"), 
						rs.getString("svolume"), 
						rs.getString("sdealprice"), 
						rs.getInt("stotal"), 
						rs.getString("shigh52"));
				
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	
	//특정 회사이름을 검색하여 회사번호 반환
	public String selectSnoBySname(Connection conn,String sname) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT sno FROM stock WHERE sname=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sname);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String sno = rs.getString("sno");
				//return stockResultSet(rs);
				
				return sno;
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;			
		}
	}

	

}
