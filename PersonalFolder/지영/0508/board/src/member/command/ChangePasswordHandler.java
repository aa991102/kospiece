package member.command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import member.service.ChangePasswordService;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

//p622
//비밀번호 변경 요청 컨트롤러
/*GET방식   요청이면  비밀번호변경폼으로 이동
 *POST방식 요청이면  비밀번호변경처리 진행
 */
public class ChangePasswordHandler implements CommandHandler {

	//View
	private static final String FORM_VIEW =
			"/view/member/changePwdFrm.jsp";
	
	private ChangePasswordService changePwdSvc = new ChangePasswordService();
	 
	
	@Override
	public String process(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("ChangePasswordHandler 진입성공");
		
		//할일
		//1.요청파라미터 받기
		//2.비즈니스로직수행(<->Service<->DAO<->DB)
		//3.Model
		//4.View지정
		//p598 21~
		//GET방식으로  요청이 들어오면
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("changePwdFrm.jsp의 method방식="+request.getMethod());
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) { //POST방식으로 요청이 들어오면
			System.out.println("changePwdFrm.jsp의 method방식="+request.getMethod());
			return processSubmit(request,response);
		}else {
			//405에러
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	//GET방식으로  요청이 들어오면  폼(/view/member/chagePwdFrm.jsp)을 보여주기
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ChangePasswordHandler의 processForm()호출");
		return FORM_VIEW;
	}
	
	//POST방식으로 요청이 들어오면   비밀번호변경요청을 진행 (P623-37)
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {
		System.out.println("ChangePasswordHandler의 processSubmit()호출");
		
		//1.파라미터 받기
		String curPwd = request.getParameter("curPwd"); //old비밀번호
		String newPwd = request.getParameter("newPwd"); //new비밀번호
		
		//3.Model
		Map<String,Boolean> errors = new HashMap<>();
		request.setAttribute("errors",errors); //View에 Model로 전달
		
		if(curPwd==null || curPwd.isEmpty()) {
			errors.put("curPwd",Boolean.TRUE);
		}
		if(newPwd==null || newPwd.isEmpty()) { //p623 50
			errors.put("newPwd",Boolean.TRUE);
		}
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		
		//2.비즈니스로직(<->Service<->DAO<->DB)
		/*UPDATE  member
			SET		name=?, password = ?
			WHERE   memberid = ?; 
		로그인을 하면 세션에 로그인유저의 id와 name이 저장된 상태
		session.setAttribute("AUTHUSER", user);
			<- session.setAttribute("AUTHUSER_ID", user.geId());
		*/
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("AUTHUSER");
		
		changePwdSvc.changePassword(user.getId(),newPwd,curPwd);
		
		try {
			
			return "/view/member/changePwdSuccess.jsp";
		
		
		//4.View
		}catch(InvalidPasswordException e) {
			errors.put("badCurPwd", Boolean.TRUE);
			return FORM_VIEW;
		}catch(MemberNotFoundException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

}








