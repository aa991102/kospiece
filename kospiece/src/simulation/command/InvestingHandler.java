package simulation.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.MemberVO;
import dto.MyStockVO;
import dto.StockHistoryVO;
import simulation.service.InvestingService;
import simulation.service.MyInvestListService;
import simulation.service.MyInvestService;

public class InvestingHandler implements CommandHandler{
	
	private HttpSession session = null;
	private MyInvestListService myInvestListService = new MyInvestListService(); 
	private MyInvestService searchService = new MyInvestService();
	private InvestingService service = new InvestingService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		session = req.getSession();
		MemberVO user = (MemberVO) session.getAttribute("AUTHUSER");
		if(user == null){return processForm(req, res);
		}else{return processSubmit(req, res, user);}
		
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return "/member/login.jsp";
	}

	private String processSubmit2(HttpServletRequest request, HttpServletResponse res, String mid, String sname) {
		//파라미터 가져오기
				
		//비즈니스 수행		
		MyStockVO myStock = searchService.getMyStock(mid, sname);
		MemberVO member = myInvestListService.getMemberVOById(mid);
		ArrayList<StockHistoryVO> histories = searchService.getMyHistory(member.getMno(), myStock.getStock().getNo(),myStock.getStock().getNo());
		
		//model 
		request.setAttribute("MyStock", myStock);
		request.setAttribute("historys", histories);
		request.setAttribute("errors", "보유량을 확인하세요.");
		
		//view 지정
		return "/virtual/investing.jsp";

	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response, MemberVO user) {
		System.out.println("InvestingHandler processSubmit들어옴");
		//파라미터 가져오기
		String sname = request.getParameter("sname");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int totalquantity = Integer.parseInt(request.getParameter("totalquantity"));
		String mid = user.getId();
		
		//int 저장용량 이상의 값 입력시 return
		if(quantity<0) {return processSubmit2(request, response, mid, sname);}
		
		String tmethod = request.getParameter("income");
		if(tmethod==null) {
			quantity=quantity*-1;
		}
		//판매량이 보유량보다 많을 경우(보유량이 없으므로 error)
		if((quantity+totalquantity)<0){return processSubmit2(request, response, mid, sname);}

		//비즈니스 수행
		MyStockVO myStock = service.insertInfo(mid, sname, quantity);
		MemberVO member = myInvestListService.getMemberVOById(mid);
		//수행조건이 맞지 않을 경우 null반환
		if(myStock == null ) {
			myStock = searchService.getMyStock(mid, sname);
			request.setAttribute("errors", "포인트가 부족합니다.");
		}
		ArrayList<StockHistoryVO> histories = searchService.getMyHistory(member.getMno(), myStock.getStock().getNo(),myStock.getStock().getNo());
		
		//model
		request.setAttribute("historys", histories);
		request.setAttribute("MyStock", myStock);
		
		//view
		return "/virtual/investing.jsp";
	}
	
}
