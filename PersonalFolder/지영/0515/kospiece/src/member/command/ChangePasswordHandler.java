package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberVO;
import controller.command.CommandHandler;
import member.service.ChangePasswordService;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;

public class ChangePasswordHandler implements CommandHandler {

	private static final String FORM_VIEW = "/mypage/myInfo/changePasswordForm.jsp";
	private ChangePasswordService changePwSvc = new ChangePasswordService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		MemberVO user = (MemberVO)req.getSession().getAttribute("AUTHUSER");
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		String curPw = req.getParameter("curPw");
		String newPw = req.getParameter("newPw");
		
		if(curPw == null || curPw.isEmpty()) {
			errors.put("curPw", Boolean.TRUE);
		}
		if(newPw == null || newPw.isEmpty()) {
			errors.put("newPw", Boolean.TRUE);
		}
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			System.out.println(user.getId());
			changePwSvc.changePassword(user.getId(), curPw, newPw);
			return "/mypage/myInfo/changePasswordSuccess.jsp";
		}catch(InvalidPasswordException e) {
			errors.put("badCurPw", Boolean.TRUE);
			return FORM_VIEW;
		}catch(MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

}
