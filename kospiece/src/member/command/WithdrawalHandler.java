package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import controller.command.CommandHandler;
import member.service.WithdrawalService;

public class WithdrawalHandler implements CommandHandler {

	WithdrawalService withdrawalService=new WithdrawalService(); 

	private static final String FORM_VIEW = "/withdrawal.do"; 
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("WithdrawalHandler 진입 성공 ");

		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.print("get방식 ");
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.print("post방식 ");
			return processSubmit(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {

		User user = (User)request.getSession().getAttribute("AUTHUSER");
		String id = user.getId();
		System.out.println(id);
		withdrawalService.service(id);
		
		System.out.print(id + " 탈퇴 완료");
		
		return "/mypage/myInfo/withdrawalSuccess.jsp";
		
	}

}
