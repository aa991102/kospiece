package simulation.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MemberDAO;
import dao.SimulationDAO;
import dto.MemberVO;
import dto.MyStockVO;
import dto.StockHistoryVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MyInvestListService {
	
	Connection conn = null;
	
	public MemberVO getMemberVOById(String mid) {
		
		MemberDAO memberDAO = new MemberDAO();
		SimulationDAO simulationDAO = new SimulationDAO();
		try {
			MemberVO member = memberDAO.selectById(conn=ConnectionProvider.getConnection(), mid); 
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
		
		SimulationDAO service = new SimulationDAO();
		try {
			return service.getMySimulationList(conn=ConnectionProvider.getConnection(), mno);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<StockHistoryVO> getMyInvestHistory(int mno){
		SimulationDAO service = new SimulationDAO();
		try {
			return service.getMyInvestHistory(conn=ConnectionProvider.getConnection(), mno);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("service errors");
			return null;
		}
		
		
		
	}
	
	
}
