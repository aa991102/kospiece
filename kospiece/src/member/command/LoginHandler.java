package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.MemberVO;
import member.service.LoginFailException;
import member.service.LoginService;

public class LoginHandler implements CommandHandler {

	private static final String FORM_VIEW = "/member/login.jsp";
	private	LoginService loginService = new LoginService();
	private String path;
	
	@Override
	public String process(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		path = request.getParameter("path");
		request.setAttribute("path", path);
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}

	//GET방식으로  요청이 들어오면  폼(/member/login.jsp)을 보여주기
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		
		System.out.println("Login processForm path="+path);
		
		
		return FORM_VIEW;
	}

	//POST방식으로 요청이 들어오면  로그인처리요청을 진행
	private String processSubmit(HttpServletRequest request, 
			HttpServletResponse response) 
			throws Exception {
		
		//1.파라미터받기
		String id 		= trim(request.getParameter("id")); //아이디
		String password = request.getParameter("pw"); //비번
		
		try {
		}catch(Exception e) {
			path = "";
		}
		System.out.println("Login processSubmit path="+path);
		if(id==null) {
			return processForm(request,response);
		}
		System.out.println("processSubmit() id/password="+id+"/"+password);
		
		
		
		//2.비즈니스로직수행
		try {
			MemberVO member = loginService.login(id,password); //폼에서 받은 파라미터를 가지고 로그인 로직 수행
			System.out.println(member.toString());
			//3.Model(session객체이용,request객체이용,..)
			
			HttpSession session = request.getSession();
			session.setAttribute("AUTHUSER", member); //로그인한 회원의 정보를 세션에 저장
			session.setAttribute("ID", member.getId());
			session.setAttribute("MNO", member.getMno());
			session.setAttribute("NICKNAME", member.getNickname()); //5.14 유태우 추가

			//4.View
			//로그인성공시   index.jsp문서로 sendRedirect를 이용하여 강제이동
			//response.sendRedirect(request.getContextPath()+"/main.jsp");
			System.out.println("path="+path);
			if(path!=null) {
				return "/member/loginSuccess.jsp";
			}else {
				return "/member/login.jsp";
			}
			
			
			
		} catch (LoginFailException e) {//로그인 실패시
			System.out.println("로그인 실패");
			e.printStackTrace(); //에러관련내용 콘솔출력
			return FORM_VIEW; 
		}
	}
	private String trim(String str) {
		//조건? 참: 거짓;
		return  str==null? null: str.trim();		
	}

}








