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
	
	public MyStockVO insertInfo(String id, String sname, int quantity) {
		
		MemberDAO memberDAO = new MemberDAO();
		
		try {
			
			conn=ConnectionProvider.getConnection();
			MemberVO member =	memberDAO.selectById(conn, id);
			StockDAO stockDAO = new StockDAO();
			StockVO stock = stockDAO.selectByName(conn, sname);
			
			//보유 포인트량이 현재가*판매수량보다 같거나 많을 경우 (정상 진행)
			if(member.getDeposit()-(stock.getPrice()*quantity)>=0) {
				SimulationDAO simulationDAO = new SimulationDAO();
				simulationDAO.insertInfo(conn, member.getMno(), quantity, stock);
				MyInvestService service = new MyInvestService();
				//member에 포인트량 업데이트 하기
				member.setDeposit(member.getDeposit()-(stock.getPrice()*quantity));
				memberDAO.update(conn, member);;
				
				return service.getMyStock(id, sname);
			//보유 포인트량이 현재가*판매수량보다 적을 경우 (error)
			}else {
				return null;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
	
	public ArrayList<StockHistoryVO> getMyInvestHistory(ArrayList<StockHistoryVO> historys, String sname){
		
		ArrayList<StockHistoryVO> result = null;
		
		for(StockHistoryVO history : historys) {
			if(history.getsname().equals(sname)) {
				result.add(history);
			}
		}
		
		return result;
		
	}
	
	
	
}
