package member.command;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberVO;
import jdbc.connection.ConnectionProvider;
import controller.command.CommandHandler;
import dao.MemberDAO;
import member.service.ChangePasswordService;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;

public class ChangePasswordHandler implements CommandHandler {
	
	HttpSession session = null;
	private static final String FORM_VIEW = "/mypage/myInfo/changePasswordForm.jsp";
	private ChangePasswordService changePwSvc = new ChangePasswordService();
	private String path = "";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//session 정보가 없으면 processForm
		session = req.getSession();
		if(session.getAttribute("AUTHUSER") == null) {
			return processToLogin(req, res);
		}
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	
	//미로그인시
	private String processToLogin(HttpServletRequest request, HttpServletResponse response) {
		path = request.getRequestURI(); 
		request.setAttribute("path", path);
		
		return "/member/login.jsp";
	}
		
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		MemberDAO memberDao = new MemberDAO();
		Connection conn = ConnectionProvider.getConnection();
		String id = (String)req.getSession().getAttribute("ID");
		MemberVO user = memberDao.selectById2(conn, id);
		
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
			return "/mypage/myInfo/changePasswordFail_NoMember.jsp";
		}catch(Exception e) {
			return "/mypage/myInfo/changePasswordFail.jsp";
		}
	}

}
