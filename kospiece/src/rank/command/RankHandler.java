package rank.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.CommandHandler;
import dto.StockVO;
import rank.service.RankService;

public class RankHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/rank/rank.jsp";
	RankService rankService=new RankService();
	List<StockVO> stockList=null;

	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.println("RankHandler 진입성공");

		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processTotalList(request,response);//파라미터가 없으면
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSelectedList(request,response);//파라미터가 있으면
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processTotalList(HttpServletRequest request, HttpServletResponse response) {
		//파라미터 없을때 실행하는 로직. 전체 회원리스트를 출력한다.
		System.out.println("파라미터없을때");
		
		stockList=rankService.service();
		
		//페이지에서 출력할 데이터 request객체에 담아보내기
		request.setAttribute("stockList",stockList);
		
		System.out.println(stockList);
		
		return FORM_VIEW;
		
	}
	private String processSelectedList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("파라미터있을때");

		return FORM_VIEW;
		
	}
}
