package admin.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.AdminService;
import admin.service.Statistics;
import admin.service.Visitor;
import controller.command.CommandHandler;

//관리자페이지 첫 화면(페이지 통계데이터 나타내기)
public class AdminHandler implements CommandHandler {

	private static final String FORM_VIEW = "/admin/admin.jsp";
	
	AdminService adminService= new AdminService(); //서비스객체 생성
	Statistics statistics= new Statistics(); //통계데이터 담을 객체 생성
	List<Visitor> visitor=new ArrayList<Visitor>();
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {

		System.out.print("AdminHandler 진입성공 ");

		statistics=adminService.staticService();//통계값 리턴받아 통계객체에 저장
		
		request.setAttribute("stat",statistics);//페이지에서 출력할 통계 객체 request속성으로 전달
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.print("get방식, 일주일 그래프 나타내기");
			return processForm(request,response);//파라미터가 없으면
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.print("post방식, 방문자 수 그래프 기간별로 표현 ");
			return processCheck(request,response);//파라미터가 있으면
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		String term="week";
		
		visitor=adminService.visitService(term);
		
		request.setAttribute("visitor", visitor);
		request.setAttribute("term", term);
		return FORM_VIEW;
		
	}
	private String processCheck(HttpServletRequest request, HttpServletResponse response) {
		
		String term=request.getParameter("term");
		
		System.out.println(term);
		
		visitor=adminService.visitService(term);
		
		System.out.println(visitor);
		
		request.setAttribute("visitor", visitor);
		request.setAttribute("term", term);
		
		return FORM_VIEW;
	}
	
	
}
