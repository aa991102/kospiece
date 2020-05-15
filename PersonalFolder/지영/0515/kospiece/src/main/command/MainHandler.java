package main.command;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import controller.command.CommandHandler;
import dto.MemberVO;


	public class MainHandler implements CommandHandler {

		//View지정(p598 16)
		private static final String FORM_VIEW = "main.jsp";
		
		@Override
		public String process(HttpServletRequest request, 
							  HttpServletResponse response) throws Exception {
			System.out.println("MainHandler 진입성공");

			if(request.getMethod().equalsIgnoreCase("GET")) {
				return processForm(request,response);
			}else if(request.getMethod().equalsIgnoreCase("POST")) { //POST방식으로 요청이 들어오면
				return processSubmit(request,response);
			}else {
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
				return   null;
			}
		}
		
		private String processForm(HttpServletRequest request, HttpServletResponse response) {
			String mid=request.getParameter("mid");
			System.out.println("회원아이디 받아씀"+mid);
			return FORM_VIEW;
		}
		
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
			return FORM_VIEW;
			
		}
		

	}
