package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.CommandHandler;
import dto.MemberVO;
import member.service.FindUpdatePwService;


public class FindUpdatePwHandler implements CommandHandler{
//id,pw,pw2필요
	//뷰지정
	private static final String FORM_VIEW = "/member/FindUpdatePw.jsp";
	private FindUpdatePwService findUpdatePwService = new FindUpdatePwService();
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.println("FindUpdatePwHandler 진입성공");

		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("FindUpdatePw.jsp의 method방식="+request.getMethod());
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) { //POST방식으로 요청이 들어오면
			System.out.println("FindUpdatePw.jsp의 method방식="+request.getMethod());
			return processSubmit(request,response);
		}else {
			//405에러
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	//GET방식으로  요청이 들어오면  폼(/member/FindUpdatePw.jsp)을 보여주기
		private String processForm(HttpServletRequest request, HttpServletResponse response) {
			return FORM_VIEW;
		}
		
		//POST방식으로 요청이 들어오면  비밀번호변경요청을 진행 
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("FindUpdatePwHandler의 processSubmit()호출");
			//할일
			//요청파라미터 받기
			Map<String,String> errors = new HashMap<>();
	
			MemberVO member = new MemberVO();
			member.setId(request.getParameter("mid"));
			member.setPw(request.getParameter("mpw"));
			request.setAttribute("member", member);
			String pw2 = request.getParameter("pw2");
			if(member.getPw().equals("")) {
				errors.put("emptys","빈칸을 채우세요.");
				request.setAttribute("errors", errors);
				return "/member/FindUpdatePw.jsp";
			}
			
			if(member.getPw().equals(pw2)) {
				FindUpdatePwService.findupdatepw(member);
			}else {
				errors.put("notm","비밀번호가 일치하지 않습니다.");
				request.setAttribute("errors", errors);
				return "/member/FindUpdatePw.jsp";
			}
				
				
			
			System.out.println("비번칸공백지나가니");
			
			System.out.println(member);
			
			System.out.println("지나가니?7");
			
			return "/member/FindUpdatePwSuccess.jsp";
			
		}
	
		
		
	}

