package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.CommandHandler;
import dto.MemberVO;
import member.service.JoinService;

//p597
//GET방식으로  요청이 들어오면  폼(/view/member/joinForm.jsp)을 보여주기
/*POST방식으로 요청이 들어오면  회원가입요청을 진행
	-입력데이터가 잘못된 경우에 다시 (/view/member/joinForm.jsp)을 보여주기
	-회원가입       성공한 경우에         (/view/member/joinSuccess.jsp)을 보여주기
*/
public class JoinHandler implements CommandHandler {

	//View지정(p598 16)
	private static final String FORM_VIEW = "/member/join.jsp";
	private JoinService joinService = new JoinService();
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.println("JoinHandler 진입성공");

		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("joinForm.jsp의 method방식="+request.getMethod());
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) { //POST방식으로 요청이 들어오면
			System.out.println("joinForm.jsp의 method방식="+request.getMethod());
			return processSubmit(request,response);
		}else {
			//405에러
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	//GET방식으로  요청이 들어오면  폼(/view/member/joinForm.jsp)을 보여주기
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("JoinHandler의 processForm()호출");
		return FORM_VIEW;
	}
	
	
	//POST방식으로 요청이 들어오면  회원가입요청을 진행 (P598-35)
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("JoinHandler의 processSubmit()호출");
		//할일
		//1.요청파라미터 받기
		//JoinRequest는 유저가 입력한 폼의 내용을 객체로 묶어서 처리
		MemberVO member = new MemberVO();
		
		member.setId(request.getParameter("id"));
		member.setNickname(request.getParameter("nickname"));
		member.setPw(request.getParameter("pw"));
		member.setName(request.getParameter("name"));
		member.setMail(request.getParameter("email1")+"@"+request.getParameter("email2"));
		member.setPhone(request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3"));
		

		//2.비즈니스로직수행(<->Service<->DAO<->DB)
		joinService.join(member);
		
		//3.Model
		//4.View지정
		return "/member/joinSuccess.jsp";
		
	}
	

}








