package simulation.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.MemberVO;
import dto.MyStockVO;
import simulation.service.MyInvestListService;
import simulation.service.MyInvestService;

public class MyInvestHandler implements CommandHandler{

	HttpSession session = null;
	
	private static final String FORM_INVEST ="/virtual/investing.jsp";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String sname = request.getParameter("sname");
		session = request.getSession();
		
		if(session == null) {
			return "/member/login.jsp";
		}
		
		if(sname==null) {   //equalsIgnoreCase  -> 대소문자 상관없이 동일여부 확인
			return processForm(request, response);
		}else if(sname!=null) {
			return processSubmit(request, response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}//process() end
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터
		String sname = "삼성전자";
		session = request.getSession();
		String mid = "jun";//(Integer)session.getAttribute("a");//회원 아이디 가져오기
		
		//비즈니스 수행
		//service 시행
		MyInvestService searchService = new MyInvestService();
		MyStockVO mystockVO = searchService.getMyStock(mid, sname);
					
		//model
		request.setAttribute("MyStock", mystockVO);
		
		//view
		return FORM_INVEST;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 가져오기
		String sname = request.getParameter("sname");
		session = request.getSession();
		String mid = "jun";//(Integer)session.getAttribute("a");//회원 아이디 가져오기
		
		System.out.println("받은 회사명"+sname);
		
		//비즈니스 수행		
		MyInvestService searchService = new MyInvestService();
		MyInvestListService service = new MyInvestListService();
		MyStockVO mystockVO = searchService.getMyStock(mid, sname);
		MemberVO member = service.getMemberVOById(mid);
					
		//model 
		request.setAttribute("MyStock", mystockVO);
		request.setAttribute("Member",member);
		
		//view 지정
		return FORM_INVEST;
		
	}
	
	
}
