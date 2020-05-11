package simulation.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.MemberVO;
import dto.MyStockVO;
import simulation.service.MyInvestListService;

public class MyInvestListHandler implements CommandHandler{
	
	/*내 주식 handler
	 * 메인페이지에서
	 * 1. 내정보가 없이 들어올 경우 login 페이지로 이동
	 * 2. 내정보를 가지고 들어올 경우 내정보를 토대로 모의투자정보와 실시간주식정보를 가지고 온다.
	 *   해당 정보들을 가지고 주식투자 jsp로 간다.
	 */
	
	HttpSession session = null;
	
	private static final String FORM_LOGIN ="/member/login.jsp";  // 로그인페이지
	private static final String FORM_MYSTOCK = "/virtual/myinvestList.jsp";  //내 주식 페이지
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//session 정보 가져오기
		session = req.getSession();
		if(session == null) {//user session 정보가 없으면 login.jsp로 강제이동
			return processForm(req, res);
		}else {//user session 정보가 있으면 process 실행
			return processSubmit(req, res);
		}
		
	}//process() end
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_LOGIN;
	}//processForm() end


	//로그인 했을때 
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		//파라미터
		session = req.getSession();
		String mid = "jun";//(Integer)session.getAttribute("a");//회원 아이디 가져오기
		
		try {
			
			/* 비즈니스
			 * 1. 내가 가지고 있는 현재포인트 가져오기
			 * 2. 내가 보유한 가상투자 주식의 목록 및 업체별 보유량 가져오기
			 */
			
			MyInvestListService myInvestService = new MyInvestListService();
			MemberVO member = myInvestService.getMemberVOById(mid);
			ArrayList<MyStockVO> mysimulationList = myInvestService.getMyList(member.getMno());
			
			//model
			req.setAttribute("member", member);
			req.setAttribute("mysumlationList", mysimulationList);
			
			//view
			return FORM_MYSTOCK;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//에외 발생 시 일단 로그인화면으로... 
			return FORM_LOGIN;
		}
		
	}//processSubmit() end

}
