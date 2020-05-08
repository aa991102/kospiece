package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

//로그아웃 컨트롤러
//로그인성공시 세션에 저장한 정보를 해제 -> 세션.invalidate()
//p611
public class LogoutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if( session!=null ) {
			session.invalidate();
		}
		
		//index.jsp화면으로 이동
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return null;
	}

}








