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

public class InvestingService {
	
	Connection conn = null;
	private SimulationDAO simulationDAO = new SimulationDAO();
	private StockDAO stockDAO = new StockDAO();
	private MyInvestService searchService = new MyInvestService();
	private MemberDAO memberDAO = new MemberDAO();
	
	//매수기준 수행
	public MyStockVO insertInfo(String id, String sname, int quantity) {
		
		try {
			
			conn=ConnectionProvider.getConnection();
			MemberVO member =	memberDAO.selectById(conn, id);
			StockVO stock = stockDAO.selectByName(conn, sname);
			int price = stock.getPrice()*quantity;

			//member update
			simulationDAO.insertInfo(conn, member.getMno(), quantity, stock);
			
			//member에 포인트량 업데이트 하기
			member.setDeposit(member.getDeposit()-price);
			memberDAO.update(conn, member);;
				
			return searchService.getMyStock(id, sname);
			
		} catch (SQLException e) {
			System.out.println("InvestingService-insertInfo Exception");
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
	
}
