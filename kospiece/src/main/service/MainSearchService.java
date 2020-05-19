package main.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DdateStockDAO;
import dao.StockDAO;
import dto.SearchStockWithDetailVO;
import dto.StockVO;
import jdbc.connection.ConnectionProvider;

public class MainSearchService {

	private StockDAO stockDAO = new StockDAO();
	private DdateStockDAO chartDAO = new DdateStockDAO();
	Connection conn;
	
	public void test() {
		
	}
	
	public SearchStockWithDetailVO getStockInfo(String sname,int mno ) {
		
		try {
			
			System.out.println("여기니?1");
			StockVO stockVO = stockDAO.selectedStock(conn=ConnectionProvider.getConnection(),sname,mno);
			if(stockVO==null) {return null;}
			System.out.println("여기니?2");
			ArrayList<StockVO> list = stockDAO.getListByDetail(conn, stockVO.getNo(), stockVO.getDetail());
			System.out.println("여기니?3");
			
			
			System.out.println("여기니?4");
			return new SearchStockWithDetailVO(stockVO, list);
			
		} catch (SQLException e) {
			System.out.println("MainSearchService getStockInfo error");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String getChartinfo(String sno) {
		
		
		try {
			return chartDAO.getChartinfo(conn=ConnectionProvider.getConnection(), sno);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
