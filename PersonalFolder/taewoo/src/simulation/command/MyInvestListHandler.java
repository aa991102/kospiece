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

public class MyInvestListHandler implements CommandHandler{
	

	private HttpSession session = null;
	private MyInvestListService myInvestService = new MyInvestListService();
	private String path = "";
	private String path2 = "";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		session = req.getSession();
		System.out.println("session="+session);
		MemberVO user = (MemberVO)session.getAttribute("AUTHUSER");
		System.out.println("user="+user);
		if(user== null){return processForm(req, res);
		}else {return processSubmit(req, res, user);
		}
		
	}//process() end
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		path = req.getRequestURI(); 
		path2 = req.getContextPath();
		req.setAttribute("path", path);
		req.setAttribute("path2", path2);
		return "/member/login.jsp";
	}//processForm() end


	//로그인 했을때 
	private String processSubmit(HttpServletRequest req, HttpServletResponse res, MemberVO user) {
		//파라미터
		System.out.println("user="+user);
		String mid = user.getId();
		//String mid = "jun";
		
		
		try {
			
			System.out.println("user="+user);
			//비즈니스
			MemberVO member = myInvestService.getMemberVOById(mid);
			ArrayList<MyStockVO> mysimulationList = myInvestService.getMyList(member.getMno());
			ArrayList<StockHistoryVO> history = myInvestService.getMyInvestHistory(member.getMno());
			
			System.out.println("mysimulationList="+mysimulationList);
			System.out.println("history="+history);
			
			//model
			req.setAttribute("member", member);
			req.setAttribute("mysumlationList", mysimulationList);
			req.setAttribute("historys", history);
			
			//view
			return "/virtual/myinvestList.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//에외 발생 시 일단 로그인화면으로...
			req.setAttribute("path", path);
			return "/member/login.jsp";
		}
		
	}//processSubmit() end

}
