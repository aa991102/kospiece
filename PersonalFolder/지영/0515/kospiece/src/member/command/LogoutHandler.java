package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;

public class LogoutHandler implements CommandHandler{

	private HttpSession session;
	String path = "";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		session = request.getSession();
		session.invalidate();
		
		return "/member/login.jsp";
	}

	
}	
