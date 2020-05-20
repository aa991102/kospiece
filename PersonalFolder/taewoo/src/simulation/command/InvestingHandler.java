package simulation.command;

import java.util.ArrayList;

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
		//파라미터 가져오기
		String sname = request.getParameter("sname");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String mid = user.getId();
		
		//int 저장용량 이상의 값 입력시 return
		if(quantity<0) {return processSubmit2(request, response, mid, sname);}
		
		String tmethod = request.getParameter("income"); //income == 매수
		if(tmethod==null) {//매도
			quantity=quantity*-1;
		}
		
		MyStockVO myStock =  searchService.getMyStock(mid, sname);
		System.out.println("myStock="+myStock);
		System.out.println("quantity="+quantity);
		
		//매수 > 0  포인트보다 낮으면 error
		if(quantity>0) {
			if(myStock.getMdeposit()<quantity*myStock.getStock().getPrice()) {
				request.setAttribute("errors", "포인트가 부족합니다.");
			}else {
				myStock = service.insertInfo(mid, sname, quantity);
			}
		}else if(quantity<0) {//매도  < 0 보유량보다 낮으면 error
			if(myStock.getTotalquantity()+quantity<0) {
				return processSubmit2(request, response, mid, sname);
			}else {
				myStock = service.insertInfo(mid, sname, quantity);
			}
		}
		
		//매매이력 가져오기
		MemberVO member = myInvestListService.getMemberVOById(mid);
		ArrayList<StockHistoryVO> histories = searchService.getMyHistory(member.getMno(), myStock.getStock().getNo(),myStock.getStock().getNo());
		
		//model
		request.setAttribute("historys", histories);
		request.setAttribute("MyStock", myStock);
		
		//view
		return "/virtual/investing.jsp";
	}
	
}
