package admin.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.Statistics;
import admin.service.AdminService;
import admin.service.UserListService;
import controller.command.CommandHandler;
import dto.MemberVO;

public class UserListHandler implements CommandHandler{
private static final String FORM_VIEW = "/admin/userManage.jsp";
	
	AdminService adminService= new AdminService();
	UserListService userlistService=new UserListService();
	Statistics statistics= new Statistics();
	List<MemberVO> memberList=null;
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.println("AdminHandler 진입성공");

		
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
		memberList=userlistService.service();
		
		//페이지에서 출력할 데이터 request객체에 담아보내기
		request.setAttribute("memberList",memberList);
		
		return FORM_VIEW;
		
	}
	private String processSelectedList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("파라미터있을때");

		return FORM_VIEW;
		
	}
	
}
