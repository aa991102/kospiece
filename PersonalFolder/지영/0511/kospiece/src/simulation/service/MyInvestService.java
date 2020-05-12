package simulation.service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDAO;
import dao.SimulationDAO;
import dao.StockDAO;
import dto.MemberVO;
import dto.MyStockVO;
import dto.StockVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MyInvestService {

	Connection conn = null;
	
	//업체명을 받아서 업체명 및 내보유량 찾기
	
	//업체 이름으로 주식정보 가져오기, 
	private StockVO selectBySname(String sname) {
		StockDAO stockDAO = new StockDAO();
		
		try {
			conn=ConnectionProvider.getConnection();
			StockVO stockVO = stockDAO.selectByName(conn, sname);
			//stockVO가 
			if(stockVO==null) {
				  
			}
			
			return stockVO; 
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//내보유량 찾기	
	private int getTotalQuantity(int mno, String sno) {
		SimulationDAO simulationDAO = new SimulationDAO();
		int totalquantity=0;
		System.out.println("이거나와?2");
		try {
			conn=ConnectionProvider.getConnection();
			totalquantity = simulationDAO.getTotalquantity(conn, mno, sno);
			
			System.out.println("totalquantity="+totalquantity);
			
			return totalquantity;
		} catch (Exception e) {
			e.printStackTrace();
			return totalquantity=-2;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
	private MemberVO getMemberVO(String id) {
		MemberDAO memberDAO = new MemberDAO();
		System.out.println("이거나와?1");
		try {
			conn=ConnectionProvider.getConnection();
			MemberVO memberVO = memberDAO.selectById(conn, id);
			System.out.println("이거나와?10");	
			System.out.println(memberVO);
			return memberVO;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("이거나와?12");
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	public MyStockVO getMyStock(String id, String sname) {
		try {
			
			int t = getMemberVO(id).getDeposit();
			System.out.println("t="+t);
			
			int q = getTotalQuantity(getMemberVO(id).getMno(), selectBySname(sname).getNo());
			System.out.println("q="+q);
			
			//MyStockVO stock = new MyStockVO(getMemberVO(id).getDeposit(), getTotalQuantity(getMemberVO(id).getMno(), selectBySname(sname).getNo()), selectBySname(sname));
			MyStockVO stock = new MyStockVO(t, q, selectBySname(sname));
		return stock;
		}catch(NullPointerException e) {
			System.out.println("에러 나니?");
			return null;
		}
		
	}
	
	
}
