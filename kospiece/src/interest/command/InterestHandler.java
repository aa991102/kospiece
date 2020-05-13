package interest.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.CommandHandler;

public class InterestHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/rank/rank.jsp";
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("interestHandler 진입 ");
		
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.print("get방식 ");
			return processTotalList(request,response);//파라미터가 없으면
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.print("post방식 ");
			return processSelectedList(request,response);//파라미터가 있으면
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processTotalList(HttpServletRequest request, HttpServletResponse response) {
		return null;
	
	}
	private String processSelectedList(HttpServletRequest request, HttpServletResponse response) {
		return null;
	
	}
}
