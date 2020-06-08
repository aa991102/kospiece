package simulation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MemberDAO;
import dao.SimulationDAO;
import dao.StockDAO;
import dto.MemberVO;
import dto.MyStockVO;
import dto.StockHistoryVO;
import dto.StockVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MyInvestService {

	Connection conn = null;
	StockDAO stockDAO = new StockDAO();
	SimulationDAO simulationDAO = new SimulationDAO();
	
	//업체 이름으로 주식정보 가져오기, 
	private StockVO selectBySname(String sname) {
		
		try {
			StockVO stockVO = stockDAO.selectByName(conn=ConnectionProvider.getConnection(), sname);
			return stockVO; 
		} catch (SQLException e) {
			System.out.println("MyInvestService-selectBySname Exception");
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//내보유량 찾기	
	private int getTotalQuantity(int mno, String sno) {
		
		int totalquantity=0;
		try {
			totalquantity = simulationDAO.getTotalquantity(conn=ConnectionProvider.getConnection(), mno, sno);
			
			return totalquantity;
		} catch (Exception e) {
			System.out.println("MyInvestService-getTotalQuantity Exception");
			e.printStackTrace();
			return totalquantity=-2;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
	private MemberVO getMemberVO(String id) {
		MemberDAO memberDAO = new MemberDAO();
		try {
			conn=ConnectionProvider.getConnection();
			MemberVO memberVO = memberDAO.selectById(conn, id);
			return memberVO;
		} catch (SQLException e) {
			System.out.println("MyInvestService-getMemberVO Exception");
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	public MyStockVO getMyStock(String id, String sname) {
		try {	
			return new MyStockVO(getMemberVO(id).getMno(), getMemberVO(id).getDeposit(), getTotalQuantity(getMemberVO(id).getMno(), selectBySname(sname).getNo()), selectBySname(sname));
		
		}catch(NullPointerException e) {
			System.out.println("MyInvestService-getMyStock Exception");
			e.printStackTrace();
			return null;
		}
		
	}
	
	//내 주식 기록 가져오기
	public ArrayList<StockHistoryVO> getMyHistory(int mno, String no) {
		
		SimulationDAO service = new SimulationDAO();
		try {
			return MyInvestListService.toName(service.getMyInvestHistory(conn=ConnectionProvider.getConnection(), mno), mno);
			
		} catch (SQLException e) {
			System.out.println("MyInvestService-getMyHistory Exception2");
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
			
	}
	
	//특정 주식 기록 가져오기
	public ArrayList<StockHistoryVO> getMyHistory(int mno, String no, String sname) {
		
		try {
			return MyInvestListService.toName(simulationDAO.getMyInvestHistory(conn=ConnectionProvider.getConnection(), mno, sname), mno);
			
		} catch (SQLException e) {
			System.out.println("MyInvestService-getMyHistory Exception1");
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
			
	}
	
	
}
