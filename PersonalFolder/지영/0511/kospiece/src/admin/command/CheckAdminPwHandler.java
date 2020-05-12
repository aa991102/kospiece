package admin.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.service.CheckAdminPwService;
import controller.command.CommandHandler;

public class CheckAdminPwHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/admin/checkAdminPw.jsp";

	CheckAdminPwService checkAdminPwService = new CheckAdminPwService();
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("CheckAdminPwHandler 진입 ");

		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.print("get방식, 폼 보여주기");
			return processForm(request,response);//파라미터가 없으면
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.print("post방식, 비밀번호 맞는지 확인");
			return processCheck(request,response);//파라미터가 있으면
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		return FORM_VIEW;
		
	}
	private String processCheck(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String id=(String) session.getAttribute("ID");
		
		String pw=request.getParameter("adminPw");
		
		checkAdminPwService.check(id,pw);
		
		return FORM_VIEW;
		
	}
}
