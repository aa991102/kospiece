package simulation.command;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.CommandHandler;
import dao.StockDAO;
import dto.MyStockVO;
import dto.Stock;
import jdbc.connection.ConnectionProvider;

public class MyInvestHandler implements CommandHandler {

	
	private static final String FORM_INVEST ="/virtual/investing.jsp"; 
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {   //equalsIgnoreCase  -> 대소문자 상관없이 동일여부 확인
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_INVEST;
	}
	
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		Connection conn = null;
		
		//파라미터
		String sname = req.getParameter("sname");
		int totalquantity = 0;
		System.out.println("여기는 오니?");
		try {
		 totalquantity = Integer.parseInt(req.getParameter("totalquantity"));
		}catch(NumberFormatException e) {
		
			
		}
		
		
		
		//비즈니스
		//1. sname으로 지정된 종목 정보를 MyStockVO로 저장
		try {
			StockDAO stockDAO = new StockDAO();
			Stock stock = stockDAO.selectByName(conn = ConnectionProvider.getConnection(), sname);
			MyStockVO myStock = new MyStockVO(totalquantity, stock);
			req.setAttribute("MyStock", myStock);
			return FORM_INVEST;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return FORM_INVEST;
	}


}
