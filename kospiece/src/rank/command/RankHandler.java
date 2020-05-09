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
		//get방식
		
		//정렬을 하고싶은 컬럼명과 정렬방식을 파라미터로 받아온다
		String field=request.getParameter("select");
		String type=request.getParameter("column");
		String sort=request.getParameter("orderBy");
		
		//처음 실시간순위 페이지에 들어올때 기본값으로 등락률과 내림차순을 셋팅한다.
		if(type==null) {
			type="schangerate";
		}
		if(sort==null) {
			sort="desc";
		}
		if(field==null) {
			field="all";
		}
		stockList=rankService.service(field,type,sort);
		
		//페이지에서 출력할 데이터 request객체에 담아보내기
		request.setAttribute("field",field);
		request.setAttribute("sort",sort);
		request.setAttribute("type",type);
		request.setAttribute("stockList",stockList);
		
		return FORM_VIEW;
		
	}
	private String processSelectedList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("파라미터있을때");

		return FORM_VIEW;
		
	}
}
