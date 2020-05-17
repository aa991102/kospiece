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
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("UserListHandler 진입-");

		String column=request.getParameter("search");
		String value=request.getParameter("user-inform");
		
		if(column==null) {
			memberList=userlistService.userListService();
			System.out.println("전체회원출력");
		}else {
			memberList=userlistService.userListService(column,value);
			System.out.println(column+"에 "+value+"가 포함되는 회원 출력");
		}
		
		request.setAttribute("memberList",memberList);
		//페이지에서 출력할 공지사항 객체 arrayList를 request속성에 담아보내기
		//<1번회원객체,2번회원객체.....>
		
		return FORM_VIEW;
		
	}
	
}
