package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.User;
import mvc.command.CommandHandler;

//p606
//이 문서는 로그인폼에서  로그인요청시 호출되는 컨트롤러
//GET방식으로  요청이 들어오면  폼(/view/member/loginForm.jsp)을 보여주기
/*POST방식으로 요청이 들어오면  로그인처리요청을 진행
	-입력데이터가 잘못된 경우에 다시 (/view/member/loginForm.jsp)을 보여주기
	-로그인         성공한 경우에         (index.jsp)을 보여주기
*/
public class LoginHandler implements CommandHandler {

	//View지정 p606-16
	private static final String FORM_VIEW = "/view/member/loginForm.jsp";
	private	LoginService loginService = new LoginService();
	
	@Override
	public String process(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("LoginHandler의 process()진입");

		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("loginForm.jsp의 method방식="+request.getMethod());
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) { //POST방식으로 요청이 들어오면
			System.out.println("loginForm.jsp의 method방식="+request.getMethod());
			return processSubmit(request,response);
		}else {
			//405에러
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
		
	}


	//p607-32
	//GET방식으로  요청이 들어오면  폼(/view/member/loginForm.jsp)을 보여주기
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("LoginHandler의 processForm()호출");
		return FORM_VIEW;
	}
	
	//p607-36
	/*POST방식으로 요청이 들어오면  로그인처리요청을 진행
	  -입력데이터가 잘못된 경우에 다시 (/view/member/loginForm.jsp)을 보여주기 */
	private String processSubmit(HttpServletRequest request, 
			HttpServletResponse response) 
			throws Exception {
		//1.파라미터받기
		String id 		= trim(request.getParameter("id")); //아이디
		String password = request.getParameter("password"); //비번
		System.out.println("processSubmit() id/password="+id+"/"+password);
		
		//2.비즈니스로직수행(<->Servict<->DAO<->DB)
		Map<String,Boolean> errors = new HashMap<>();

			
			//유효성검사등관련 에러처리p607 41~51
			//에러에 대한 데이터를 Map방식으로 관리
			
			//3.Model(session객체이용,request객체이용,..)
			request.setAttribute("errors",errors); //View에 Model로 전달
			
			//p607 44~51
			if( id==null || id.isEmpty() ) {
				errors.put("id",Boolean.TRUE);
			}
			
			if( password==null || password.isEmpty() ) {
				errors.put("password",Boolean.TRUE);
			}
			
			//에러가 발생되면
			if(  !errors.isEmpty() ) {
				return FORM_VIEW; //로그인폼으로 이동
			}
			
		//회원으로 존재하면 회원정보가 User객체로 리턴
		try {
			User user = loginService.login(id,password);
			
			//로그인한 회원의 정보를 세션에 저장
			//형식> session.setAttribute(String name,Obejct value);
			HttpSession session = request.getSession();
			session.setAttribute("AUTHUSER", user);
			/*
			session.setAttribute("AUTHUSER_ID", user.getId());
			session.setAttribute("AUTHUSER_NAME", user.getName());
			*/
			
			//4.View
			//로그인성공시   index.jsp문서로 sendRedirect를 이용하여 강제이동
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return null;
		} catch (LoginFailException e) {//로그인 실패시
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			e.printStackTrace(); //에러관련내용 콘솔출력
			return FORM_VIEW;    //  /view/member/loginForm.jsp를  View지정 
		}
	}
	
	
	private String trim(String str) {
		//조건? 참: 거짓;
		return  str==null? null: str.trim();		
	}
}















