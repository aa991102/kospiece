package main.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		//비즈니스
		SearchStockWithDetailVO info = mainSearchService.getStockInfo(sname);
		
		//model
		request.setAttribute("info", info);
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
