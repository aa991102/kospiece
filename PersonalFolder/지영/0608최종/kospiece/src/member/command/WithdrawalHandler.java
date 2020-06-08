package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.MemberVO;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import member.service.WithdrawalService;

public class WithdrawalHandler implements CommandHandler {

	WithdrawalService withdrawalService=new WithdrawalService(); 
	private HttpSession session;
	private static final String FORM_VIEW = "/mypage/myInfo/withdrawalForm.jsp"; 
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		System.out.print("WithdrawalHandler 진입 성공 ");
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
			
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		MemberVO member = (MemberVO)request.getSession(false).getAttribute("AUTHUSER");
		
		request.setAttribute("member", member);
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		MemberVO member = (MemberVO)request.getSession(false).getAttribute("AUTHUSER");
		
		Map<String,Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		//비밀번호 일치하면 삭제하고 탈퇴성공페이지로 / 아닌경우 탈퇴폼으로 다시 보내기
		String checkPw = request.getParameter("passcheck");
		
		if(checkPw == null || checkPw.isEmpty()) {
			errors.put("checkPw", Boolean.TRUE);
		}
		if(!errors.isEmpty()) {
			request.setAttribute("member", member);
			return FORM_VIEW;
		}
		
		try {
			
			String mid = member.getId();
			
			withdrawalService.deleteMember(mid,checkPw);
			session = request.getSession();
			session.invalidate();
			System.out.print(mid + " 탈퇴 완료");
			return "/mypage/myInfo/withdrawalSuccess.jsp";
			
		}catch(InvalidPasswordException e) {
			request.setAttribute("member", member);
			errors.put("badCurPw", Boolean.TRUE);
			
			return FORM_VIEW;
		}catch(MemberNotFoundException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return "/mypage/myInfo/withdrawalFail_NoMember.jsp";
		}catch(Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return "/mypage/myInfo/withdrawalFail.jsp";
		}
		
	}

}
