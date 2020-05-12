package mypage.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dao.MemberDAO;
import dto.MemberVO;
import jdbc.connection.ConnectionProvider;

public class myInfoListHandler implements CommandHandler {
	
	/* 회원정보 조회 핸들러
	 * 
	 */
	HttpSession session = null;
	
	private static final String FORM_VIEW ="/member/login.jsp";  // 로그인페이지
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//session 정보 가져오기
		session = req.getSession();
		if(session == null) {//user session 정보가 없으면 login.jsp로 강제이동
			return processForm(req, res);
		}else {//user session 정보가 있으면 process 실행
			return processSubmit(req, res);
		}
		
	}//process() end
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}//processForm() end


	//로그인 했을때 
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		MemberVO member = null;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			member = (MemberVO)req.getSession(false).getAttribute("AUTHUSER");
			
			req.setAttribute("member", member);
			return "/mypage/mypage.jsp";
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//에외 발생 시 일단 로그인화면으로... 
			return FORM_VIEW;
		}
		
	}//processSubmit() end

}
