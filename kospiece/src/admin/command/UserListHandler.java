package admin.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.UserListService;
import controller.command.CommandHandler;
import dto.MemberVO;

public class UserListHandler implements CommandHandler{
	private static final String FORM_VIEW = "/admin/userManage.jsp";
	
	UserListService userlistService=new UserListService();
	List<MemberVO> memberList=null;
	String column="";
	String value="";
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("UserListHandler 진입 ");

		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.print("get방식, 파라미터 없음, 전체 회원 출력");
			return processTotalList(request,response);//파라미터가 없으면
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.print("post방식, 파라미터 있음, 선택된 조건의 회원 출력");
			return processSelectedList(request,response);//파라미터가 있으면
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processTotalList(HttpServletRequest request, HttpServletResponse response) {
		//파라미터 없을때 실행하는 로직. 전체 회원리스트를 출력한다.
		memberList=userlistService.userListService(column,value);
		
		request.setAttribute("memberList",memberList);
		//페이지에서 출력할 공지사항 객체 arrayList를 request속성에 담아보내기
		//<1번회원객체,2번회원객체.....>
		
		return FORM_VIEW;
		
	}
	private String processSelectedList(HttpServletRequest request, HttpServletResponse response) {
		//파라미터 있을때 실행하는 로직. 선택된 조건의 회원리스트만 출력한다.
		
		String column=request.getParameter("search");
		String value=request.getParameter("user-inform");
		
		memberList=userlistService.userListService(column,value);
		
		request.setAttribute("memberList",memberList);
		//페이지에서 출력할 공지사항 객체 arrayList를 request속성에 담아보내기
		//<1번회원객체,2번회원객체.....>
		
		return FORM_VIEW;
		
	}
	
}
