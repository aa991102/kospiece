package member.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dao.SessionffbhDAO;
import util.ConnectionProvider;

public class LogoutHandler implements CommandHandler{

	private HttpSession session;
	private String path;
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		session = request.getSession();
		SessionffbhDAO sessionDao = new SessionffbhDAO();
		
		try(Connection conn = ConnectionProvider.getConnection()){
			sessionDao.delete(conn, session.getId());
		}
		
		session.invalidate();
		path = request.getParameter("path");
		
		System.out.println("path="+path);
		System.out.println(request.getRequestURI());
		request.setAttribute("path", path);
		
		
		return "/member/logoutSuccess.jsp";
	}

	
}	
