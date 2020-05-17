package admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.UserDeleteService;
import controller.command.CommandHandler;

//회원을 강제탈퇴 눌렀을 때 실행
public class UserDeleteHandler  implements CommandHandler {
	
	UserDeleteService userDeleteService=new UserDeleteService(); //서비스 객체 생성

	private static final String FORM_VIEW = "/userList.do"; //탈퇴시킨 후 유저 리스트 페이지로 리턴
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("UserDeleteHandler진입-");

		String id=(String) request.getAttribute("userId"); //해당 회원 id를 파라미터로 받음
		userDeleteService.service(id); //회원 id를 가지고 서비스 진행
		
		System.out.println(id+"강제탈퇴완료");
		
		return FORM_VIEW;
		
	}
}
