package admin.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.service.CheckAdminPwService;
import controller.command.CommandHandler;

//관리자페이지 비밀번호 확인하는 로직
public class CheckAdminPwHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/admin/checkAdminPw.jsp";

	CheckAdminPwService checkAdminPwService = new CheckAdminPwService();
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.println("CheckAdminPwHandler 진입");

		HttpSession session = request.getSession();
		
		//세션의 id에 해당하는 비번과 사용자가 입력한 비번이 같은지 확인
		String id=(String) session.getAttribute("ID");
		String pw=request.getParameter("adminPw");
		
		if(pw==null) {//처음 비밀번호 입력 화면에 들어왔을 때 form만 출력
			return FORM_VIEW;
		}
		
		System.out.print(id+"님이 입력한 비밀번호는 "+pw+"입니다-");
		
		Boolean pwCheck=checkAdminPwService.check(id,pw);
		System.out.println("비밀번호가 일치하는가?"+pwCheck);
		
		if(pwCheck) {//비밀번호가 맞으면 이 페이지를 부른 각 페이지로 리턴
			String service=request.getParameter("service");
			
			if(service.equals("deleteMember")){
				String userNick=request.getParameter("userNick");
				request.setAttribute("userNick",userNick);
				return "memberDelete.do"; //회원을 삭제하는 로직 수행
				
			}else if(service.equals("pointCharge")) {
				String point=request.getParameter("point");
				request.setAttribute("point", point);	
				String userNick=request.getParameter("userNick");
				request.setAttribute("userNick",userNick);
				return "/admin/pointCharge.jsp"; //포인트 충전하기 위한 form 화면으로
				
			}else if(service.equals("modify")) {
				String no=request.getParameter("no");
				request.setAttribute("no", no);
				return "noticeModify.do"; //게시글 수정을 위한 로직 수행
				
			}else if(service.equals("delete")) {
				String no=request.getParameter("no");
				request.setAttribute("no", no);
				return "noticeDelete.do"; //게시글 삭제를 위한 로직 수행
				
			}else if(service.equals("write")) {
				return "/admin/noticeWrite.jsp";//게시글 작성을 위한 form 화면으로
			}
		}else {//비밀번호가 다르면 에러메시지를 가지고 비밀번호 입력 폼으로 이동
			String error="비밀번호를 다시 입력하세요";
			request.setAttribute("error", error);
		}
		return FORM_VIEW;
	}
}
