package admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.Statistics;
import admin.service.AdminService;
import controller.command.CommandHandler;

public class NoticeListHandler implements CommandHandler {

	private static final String FORM_VIEW = "/admin/admin.jsp";
	
	AdminService adminService= new AdminService();
	Statistics statistics= new Statistics();
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.println("AdminHandler 진입성공");

		//admin페이지는 파라미터를 받을 일이 없으므로 processAdmin메서드 하나만 생성
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processAdmin(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processAdmin(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processAdmin(HttpServletRequest request, HttpServletResponse response) {
		
		statistics=adminService.service();//파라미터없음
		
		//페이지에서 출력할 데이터 request객체에 담아보내기
		request.setAttribute("stat",statistics);
		System.out.println(statistics.toString());
		
		return FORM_VIEW;
		
	}
}
