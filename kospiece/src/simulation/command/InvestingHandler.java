package simulation.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.MyStockVO;
import simulation.service.InvestingService;
import simulation.service.MyInvestService;

public class InvestingHandler implements CommandHandler{
	
	HttpSession session = null;
	private static final String FORM_INVEST ="/virtual/investing.jsp";
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//파라미터
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int totalquantity = Integer.parseInt(request.getParameter("totalquantity"));
		
		//판매량보다 보유량이 많을 경우(보유량이 없으므로 error)
		if((quantity+totalquantity)<0) {   //equalsIgnoreCase  -> 대소문자 상관없이 동일여부 확인
			return processForm(request, response);
			
		//판매량이 보유량보다 적을 경우(정상 진행) 
		}else if((quantity+totalquantity)>=0) {
			return processSubmit(request, response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {

		//파라미터 가져오기
		String sname = request.getParameter("sname");
		session = request.getSession();
		String mid = "22";//(Integer)session.getAttribute("a");//회원 아이디 가져오기
		
		//비즈니스 수행		
		MyInvestService searchService = new MyInvestService();
		MyStockVO mystockVO = searchService.getMyStock(mid, sname);
		
		//model 
		request.setAttribute("MyStock", mystockVO);
		request.setAttribute("errors", "보유량을 확인하세요.");
		
		//view 지정
		return FORM_INVEST;

	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 가져오기
		String sname = request.getParameter("sname");
		session = request.getSession();
		String mid = "22";//(Integer)session.getAttribute("a");//회원 아이디 가져오기
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		//비즈니스 수행
		InvestingService service = new InvestingService();
		MyStockVO myStock = service.insertInfo(mid, sname, quantity);
		//수행조건이 맞지 않을 경우 null반환
		if(myStock == null ) {
			
			MyInvestService searchService = new MyInvestService();
			myStock = searchService.getMyStock(mid, sname);
			request.setAttribute("errors", "포인트가 부족합니다.");
		}
				
		//model 
		request.setAttribute("MyStock", myStock);
		
		
		//view
		return FORM_INVEST;
	}
	
}
