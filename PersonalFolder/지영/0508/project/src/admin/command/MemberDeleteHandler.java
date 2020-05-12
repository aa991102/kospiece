package admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.UserDeleteService;
import controller.command.CommandHandler;

public class MemberDeleteHandler  implements CommandHandler {
	
	UserDeleteService userDeleteService=new UserDeleteService();

	private static final String FORM_VIEW = "/userList.do";
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.println("MemberDeleteHandler 진입성공");

		//아이디 파라미터를 받는 경우
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("get으로받음");
			return processDelete(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("post으로받음");
			return processDelete(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processDelete(HttpServletRequest request, HttpServletResponse response) {

		String id=request.getParameter("id");
		userDeleteService.service(id);
		
		return FORM_VIEW;
		
	}
}
