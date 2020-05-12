package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import dto.MyStockVO;
import dto.SimulationVO;
import dto.StockVO;
import jdbc.JdbcUtil;

public class SimulationDAO {
	
	public SimulationVO getSimulationVO(Connection conn, int mno) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM jusiktopia.simulation where mno=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			SimulationVO SimulationVO = null;
			
			if(rs.next()) {
				SimulationVO = new SimulationVO(
						rs.getInt("mno"), 
						rs.getString("sno"), 
						rs.getInt("siquantity"), 
						rs.getInt("siprice"), 
						rs.getTimestamp("sidate"));
			}
			return SimulationVO;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}//getSimulationVO() end
	
	//getMySimulationVO(mno)
	public ArrayList<MyStockVO> getMySimulationList(Connection conn, int mno) throws Exception{
	//회원번호를 기준으로 모의투자테이블에서 보유량이 0이상인 정보만 추출해야 한다.
		//1 보유량이 0 주식들을 ArrayList에 담는다.
		//1-1 보유량 0이상인 주식들 가져오기
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String sql1 = "SELECT sno, SUM(siquantity) FROM simulation where mno=? GROUP BY sno ";
		String sql2 = "SELECT * FROM stock WHERE sno=?";
		
		
		pstmt = conn.prepareStatement(sql1);
		pstmt.setInt(1, mno);
		rs = pstmt.executeQuery();
		
		ArrayList<MyStockVO> mystockList = new ArrayList();
		
		//ArrayList에 MystockList 담기
		while(rs.next()) {
			String sno = rs.getString("sno");
			int sum = rs.getInt("SUM(siquantity)");
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, sno);
			rs2 = pstmt2.executeQuery();
			
			if(rs2.next()) {
				StockVO stock = new StockVO(
						rs2.getString("sno"),
						rs2.getString("sname"),
						rs2.getString("sfield"),
						rs2.getString("sdetail"),
						rs2.getInt("sprice"),
						rs2.getString("sdayrate"),
						rs2.getFloat("schangerate"),
						rs2.getString("svolume"),
						rs2.getString("sdealprice"),
						rs2.getInt("stotal"),
						rs2.getString("shigh52")); //종목 정보
				mystockList.add(new MyStockVO(sum, stock));
			}
		}
		
		return mystockList;
					
	}//getMySimulationVO() end
	
	
}
