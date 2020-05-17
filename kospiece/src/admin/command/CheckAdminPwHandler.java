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

		HttpSession session = request.getSession();
		
		//세션의 id에 해당하는 비번과 사용자가 입력한 비번이 같은지 확인
		String id=(String) session.getAttribute("ID");
		String pw=request.getParameter("adminPw");
		
		if(pw==null) {
			return FORM_VIEW;
		}
		
		System.out.print(id+"님이 입력한 비밀번호는 "+pw);
		
		Boolean pwCheck=checkAdminPwService.check(id,pw);
		System.out.println(pwCheck);
		
		if(pwCheck) {//비밀번호가 맞으면 이 페이지를 부른 각 페이지로 리턴
			String service=request.getParameter("service");
			String userId=request.getParameter("userId");
			request.setAttribute("userId",userId);
			
			String error="";
			request.setAttribute("error",error );
			
			if(service.equals("deleteMember")){
				return "memberDelete.do";
			}else if(service.equals("pointCharge")) {
				return "/admin/pointCharge.jsp";
			}else if(service.equals("modify")) {
				return "/admin/noticeModify.jsp";
			}else if(service.equals("delete")) {
				return "noticeDelete.do";
			}
		}else {//비밀번호가 다르면 에러메시지를 가지고 비밀번호 입력 폼으로 이동
			String error="비밀번호를 다시 입력하세요";
			request.setAttribute("error", error);
		}
		return FORM_VIEW;
	}
}
