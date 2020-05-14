package dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dto.MyStockVO;
import dto.SimulationVO;
import dto.StockHistoryVO;
import dto.StockVO;
import jdbc.JdbcUtil;

public class SimulationDAO {
	
	
	//특정회사의 내 보유량 가져오기
	public int getTotalquantity(Connection conn, int mno, String sno) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT sum(siquantity) FROM simulation where mno=? and sno=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setString(2, sno);
			rs = pstmt.executeQuery();
			int totalquantity=0;
			if(rs.next()) {
				totalquantity=rs.getInt("sum(siquantity)");
			}else {
				totalquantity = -1;
			}
			return totalquantity;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}//getSimulationVO() end
	
	
	//내 주식정보 리스트 가져오기
	public ArrayList<MyStockVO> getMySimulationList(Connection conn, int mno) throws Exception{
	//회원번호를 기준으로 모의투자테이블에서 보유량이 0이상인 정보만 추출해야 한다.
		//1 보유량이 0 주식들을 ArrayList에 담는다.
		//1-1 보유량 0이상인 주식들 가져오기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String sql = "SELECT sno, SUM(siquantity) FROM simulation where mno=? GROUP BY sno ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mno);
		rs = pstmt.executeQuery();
		
		ArrayList<MyStockVO> mystockList = new ArrayList();
		StockDAO stockDAO = new StockDAO();
		
		//ArrayList에 MystockList 담기
		while(rs.next()) {
			String sno = rs.getString("sno");
			int sum = rs.getInt("SUM(siquantity)");
			sql = "SELECT * FROM stock WHERE sno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sno);
			rs2 = pstmt.executeQuery();
			StockVO stock = null;
			if(rs2.next()) {
				stock = stockDAO.stockResultSet(rs2);	
			}
			mystockList.add(new MyStockVO(0, sum, stock));
		}
		
		JdbcUtil.close(rs2);
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		
		return mystockList;
					
	}//getMySimulationVO() end
	
	
	//가상투자 정보 입력
	public void insertInfo(Connection conn, int mno, int quantity, StockVO stock) {

		PreparedStatement pstmt = null;
		String sql = "INSERT INTO simulation (mno, sno, siquantity, siprice, sidate) VALUES (?,?,?,?,sysdate())";
		Date date = new Date();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setString(2, stock.getNo());
			pstmt.setInt(3, quantity);
			pstmt.setInt(4, stock.getPrice());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//내 보유량을 현재가를 기준으로 포인트로 환산하기
	public int calculateAsset(Connection conn, int mno) {
		int i = 0;
		try {
			for(MyStockVO mystock: getMySimulationList(conn, mno)) {
				i += mystock.getStock().getPrice()*mystock.getTotalquantity();
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return i;
		}
	}//calculateAsset() end
	
	
	//내 주식 기록 가져오기
	public ArrayList<StockHistoryVO> getMyInvestHistory(Connection conn, int mno){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM simulation where mno=?";
		ArrayList<StockHistoryVO> history = new ArrayList<StockHistoryVO>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				StockHistoryVO stock = new StockHistoryVO(
						rs.getDate("sidate"), 
						rs.getString("sno"), 
						rs.getInt("siquantity"), 
						rs.getInt("siprice"), 
						rs.getInt("siquantity")*rs.getInt("siprice"));
				
				
				history.add(stock);
				
			}
			
			return history;
		}catch(Exception e) {
			System.out.println("DAO error");
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	
}
