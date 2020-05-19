package simulation.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.MemberVO;
import dto.MyStockVO;
import dto.StockHistoryVO;
import simulation.service.MyInvestListService;
import simulation.service.MyInvestService;

public class MyInvestHandler implements CommandHandler{

	private HttpSession session = null;
	private MyInvestService searchService = new MyInvestService();
	private MyInvestListService service = new MyInvestListService();
	private String path = "";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		session = req.getSession();
		MemberVO user = (MemberVO) session.getAttribute("AUTHUSER");//회원 아이디 가져오기
		if(user == null) {
			return processForm(req, res);
		}else{return processSubmit(req, res, user);
		}
		
	}//process() end
	
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		path = req.getRequestURI();
		req.setAttribute("path", path);
		return "/member/login.jsp";
	}//processForm() end

	private String processSubmit(HttpServletRequest request, HttpServletResponse response, MemberVO user) {
		//파라미터 가져오기
		String sname = request.getParameter("sname");
		if(sname==null) {
			sname="삼성전자";
		}
		String mid = user.getId();
		 
		//비즈니스 수행		
		try {
		MyStockVO myStock = searchService.getMyStock(mid, sname);
		MemberVO member = service.getMemberVOById(mid);
		ArrayList<StockHistoryVO> histories = searchService.getMyHistory(member.getMno(), myStock.getStock().getNo(),myStock.getStock().getNo());
		
		//model 
		request.setAttribute("MyStock", myStock);
		request.setAttribute("Member",member);
		request.setAttribute("historys", histories);
		
		}catch(Exception e) {
			System.out.println("MyInvestHandler processSubmit error");
			return "/virtual/investing.jsp";
		}
								
		//view 지정
		return "/virtual/investing.jsp";
		
	}//processSubmit() end
	
	
}
