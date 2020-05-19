package main.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.StockDAO;
import dto.SearchStockWithDetailVO;
import dto.StockVO;
import jdbc.connection.ConnectionProvider;

public class MainSearchService {

	private StockDAO stockDAO = new StockDAO();
	Connection conn;
	
	public SearchStockWithDetailVO getStockInfo(String sname) {
		
		try {
			
			System.out.println("여기니?1");
			StockVO stockVO = stockDAO.selectByName(conn=ConnectionProvider.getConnection(), sname);
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

}
