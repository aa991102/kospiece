package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberVO;
import controller.command.CommandHandler;
import member.service.InfoUpdateService;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;

public class InfoUpdatePwCheckHandler implements CommandHandler {
	
	/* 회원정보 수정 비밀번호 확인 핸들러
	 * get방식 
	 *  - /mypage/myInfo/myInfoUpdatePassChk.jsp
	 * post방식 
	 *  - /mypage/myInfo/myInfoUpdateForm.jsp
	 */
	
	private static final String FORM_VIEW = "/mypage/myInfo/myInfoUpdatePassChk.jsp";
	private InfoUpdateService infoUpSvc = new InfoUpdateService();
	MemberVO user = null;

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//session에서 로그인 회원정보 가져오기
		user = (MemberVO)request.getSession().getAttribute("AUTHUSER");
		
		//get방식이면 processForm
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
			
		//post방식이면 processSubmit
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
			
		//두 방식 다 아니면...
		}else {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}
	
	
	//get방식 /mypage/myInfo/myInfoUpdatePassChk.jsp
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("user",user);
		return FORM_VIEW;
	}
	
	
	// post방식 /mypage/myInfo/myInfoUpdateForm.jsp
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("InfoUpdatePwCheckHandler-processSubmit()진입");
				
		Map<String,Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		String passCheck = request.getParameter("passcheck");
		
		if(passCheck == null || passCheck.isEmpty()) {
			errors.put("checkPw", Boolean.TRUE);
		}
		
		if(!errors.isEmpty()) {
			request.setAttribute("user", user);
			return FORM_VIEW;
		}
		
		try {
			System.out.println(user.getId());
			infoUpSvc.checkPassword(user.getId(), passCheck);
			request.setAttribute("user", user);
			return "/mypage/myInfo/myInfoUpdateForm.jsp";
			
		}catch(InvalidPasswordException e) {
			errors.put("badCurPw", Boolean.TRUE);
			request.setAttribute("user", user);
			return FORM_VIEW;
		}catch(MemberNotFoundException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

}
