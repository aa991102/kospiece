package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;

public class LogoutHandler implements CommandHandler{

	private HttpSession session;
	private String path;
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		session = request.getSession();
		session.invalidate();
		path = request.getParameter("path");
		
		System.out.println("path="+path);
		System.out.println(request.getRequestURI());
		request.setAttribute("path", path);
		
		
		return "/user/logoutSuccess.jsp";
	}

	
}	
