package simulation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.MemberDAO;
import dao.SimulationDAO;
import dao.StockDAO;
import dto.MemberVO;
import dto.MyStockVO;
import dto.StockHistoryVO;
import dto.StockVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MyInvestListService {
	
	static Connection conn = null;
	private MemberDAO memberDAO = new MemberDAO();
	private SimulationDAO simulationDAO = new SimulationDAO();
	private static StockDAO stockDAO = new StockDAO();
	
	public MemberVO getMemberVOById(String mid) {
		
		try {
			conn = ConnectionProvider.getConnection();
			MemberVO member = memberDAO.selectById(conn, mid); 
			member.setAsset(simulationDAO.calculateAsset(conn, member.getMno())+member.getDeposit());
			
			return member;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
	public ArrayList<MyStockVO> getMyList(int mno){
		
		try {
			return simulationDAO.getMySimulationList(conn=ConnectionProvider.getConnection(), mno);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	public ArrayList<StockHistoryVO> getMyInvestHistory(int mno){
		
		try {
			return toName(simulationDAO.getMyInvestHistory(conn=ConnectionProvider.getConnection(), mno), mno); 
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
	public static ArrayList<StockHistoryVO> toName(ArrayList<StockHistoryVO> historys, int mno){
		
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			List<StockVO> list  = stockDAO.selectAllStock(conn=ConnectionProvider.getConnection(), mno, "all", "sno", "desc");
			for(StockVO stock  : list) {
				map.put(stock.getNo(), stock.getName());
			}
			for(StockHistoryVO history : historys) {
				String name = map.get(history.getsname());
				history.setSname(name);
			}
			
			return historys;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
}
