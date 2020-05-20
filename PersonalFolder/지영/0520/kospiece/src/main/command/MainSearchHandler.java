package main.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.SearchStockWithDetailVO;
import main.service.MainSearchService;

public class MainSearchHandler implements CommandHandler{

	private MainSearchService mainSearchService = new MainSearchService();
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//비회원 검색
		
		//파라미터 받기
		String sname = request.getParameter("sname");
		
		if(sname==null||sname.equals("")) {
			request.setAttribute("error", "올바르게 입력하세요.");
			return "/main.jsp";
		}
		
		//로그인한 회원 받기
		HttpSession session = request.getSession();
		int mno=0;
		if(session.getAttribute("MNO")!=null) {
			//세션에 회원정보가 있으면(로그인 상태면) mno에 회원번호 넣어주기
			//비로그인 상태라면 mno는 0이된다
			mno=(int)session.getAttribute("MNO");
		}
		
		//비즈니스
		SearchStockWithDetailVO info = mainSearchService.getStockInfo(sname,mno);
		String chartinfo = mainSearchService.getChartinfo(info.getStockVO().getNo());
		
		
		//model
		request.setAttribute("info", info);
		request.setAttribute("chart", chartinfo);
		if(info==null) {
			request.setAttribute("error", "검색하신 업체는 존재하지 않습니다.");
		}
		if(info.getList()==null) {
			request.setAttribute("errors", "검색하신 업체의 동종업체는 없습니다.");
		}
		
		//view
		return "/main.jsp";
		
		//회원일경우
		
	}

	
	
}
