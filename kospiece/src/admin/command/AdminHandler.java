package admin.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.Statistics;
import admin.model.Visitor;
import admin.service.AdminService;
import controller.command.CommandHandler;

//관리자페이지 첫 화면(페이지 통계데이터 나타내기)
public class AdminHandler implements CommandHandler {

	private static final String FORM_VIEW = "/admin/admin.jsp"; //admin 홈 화면
	
	AdminService adminService= new AdminService(); //서비스객체 생성
	
	Statistics statistics= new Statistics(); //통계데이터 담을 객체 생성(today,total 통계)
	List<Visitor> visitor=new ArrayList<Visitor>();//단위기간별 방문자 데이터를 담을 객체 생성(기간별 방문자 통계)
	
	String term;
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {

		System.out.print("AdminHandler 진입성공 ");

		statistics=adminService.staticService();//통계값 리턴받아 통계객체에 저장
		
		request.setAttribute("stat",statistics);//페이지에서 출력할 통계 객체 request속성으로 전달
		
		if(request.getMethod().equalsIgnoreCase("GET")) {//처음 화면 들어왔을 때(기본 단위기간=일주일)
			System.out.print("get방식, 일주일 그래프 나타내기-");
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//단위기간 클릭했을 때
			System.out.print("post방식, 방문자 수 그래프 기간별로 표현-");
			return processCheck(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		term="week"; //기본 단위기간=일주일
		processService(request,response);
		return FORM_VIEW;
		
	}
	private String processCheck(HttpServletRequest request, HttpServletResponse response) {
		
		term=request.getParameter("term");//클릭한 단위기간 불러오기
		processService(request,response);
		return FORM_VIEW;
	}
	
	private void processService(HttpServletRequest request, HttpServletResponse response){
		visitor=adminService.visitService(term);
		
		System.out.println("방문자수 단위기간="+term);
		
		request.setAttribute("visitor", visitor);
		request.setAttribute("term", term);
	}
	
	
}
