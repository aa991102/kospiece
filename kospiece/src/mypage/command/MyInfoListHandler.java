package mypage.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dao.MemberDAO;
import dto.MemberVO;
import jdbc.connection.ConnectionProvider;

public class MyInfoListHandler implements CommandHandler {
	
	/* 회원정보 조회 핸들러
	 * 
	 */
	
	HttpSession session = null;
	
	private static final String FORM_VIEW ="/member/login.jsp";  // 로그인페이지
	private String path = "";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("myInfoListHandler-process()");
		//session 정보 가져오기
		session = req.getSession();
		if(session.getAttribute("AUTHUSER") == null) {//user session 정보가 없으면 login.jsp로 강제이동
			return processForm(req, res);
		}else {//user session 정보가 있으면 process 실행
			return processSubmit(req, res);
		}
		
	}//process() end
	
	
	//로그인 안했을때
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		path = req.getRequestURI(); 
		req.setAttribute("path", path);
		return FORM_VIEW;
	}//processForm() end


	//로그인 했을때 
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		MemberVO member = null;
		MemberDAO memberDao = new MemberDAO();
		String id = null;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			id = (String)session.getAttribute("ID");
			member = memberDao.selectById2(conn, id);
			
			req.setAttribute("member", member);
			return "/mypage/myInfo.jsp";
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//에외 발생 시 일단 로그인화면
			return FORM_VIEW;
		}
		
	}//processSubmit() end

}