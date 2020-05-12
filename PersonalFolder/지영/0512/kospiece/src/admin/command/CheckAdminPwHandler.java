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
		
		//세션의 id에 해당하는 비번과 사용자가 입력한 비번이 같은지 확인
		String id=(String) session.getAttribute("ID");
		String pw=request.getParameter("adminPw");
		System.out.print(id+"님이 입력한 비밀번호는 "+pw);
		
		Boolean pwCheck=checkAdminPwService.check(id,pw);
		System.out.println(pwCheck);
		
		if(pwCheck) {//비밀번호가 맞으면 이 페이지를 부른 각 페이지로 리턴
			String service=request.getParameter("service");
			String userId=request.getParameter("userId");
			request.setAttribute("userId",userId);
			
			if(service.equals("deleteMember")){
				
				return "memberDelete.do";
			}
		}else {//비밀번호가 다르면 에러메시지를 가지고 비밀번호 입력 폼으로 이동
			String error="비밀번호를 다시 입력하세요";
			request.setAttribute("error", error);
		}
		return FORM_VIEW;
	}
}
