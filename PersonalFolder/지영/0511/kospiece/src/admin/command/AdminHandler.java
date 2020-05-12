package admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.AdminService;
import admin.service.Statistics;
import controller.command.CommandHandler;

//관리자페이지 첫 화면(페이지 통계데이터 나타내기)
public class AdminHandler implements CommandHandler {

	private static final String FORM_VIEW = "/admin/admin.jsp";
	
	AdminService adminService= new AdminService(); //서비스객체 생성
	Statistics statistics= new Statistics(); //통계데이터 담을 객체 생성
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("AdminHandler 진입성공 ");

		//admin페이지는 파라미터를 받을 일이 없으므로 processAdmin메서드 하나만 생성
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.print("get방식 ");
			return processAdmin(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.print("post방식 ");
			return processAdmin(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processAdmin(HttpServletRequest request, HttpServletResponse response) {
		
		statistics=adminService.service();//통계값 리턴받아 통계객체에 저장
		
		request.setAttribute("stat",statistics);//페이지에서 출력할 통계 객체 request속성으로 전달
		System.out.print(statistics.toString()+"통계객체 전달");
		
		return FORM_VIEW;
		
	}
}
