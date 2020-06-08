package member.command;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberVO;
import jdbc.connection.ConnectionProvider;
import controller.command.CommandHandler;
import dao.MemberDAO;
import member.service.DuplicateMailException;
import member.service.DuplicateNickException;
import member.service.DuplicatePhoneException;
import member.service.InfoUpdateService;
import member.service.MemberNotFoundException;

public class InfoUpdateHandler implements CommandHandler {
	
	/* 회원정보 수정 핸들러
	 * post방식 
	 *  - 실패시 /mypage/myInfo/myInfoUpdateForm.jsp
	 *        기존에 입력했던 값을 가지고 보내주기
	 *  - 성공시 /mypage/myInfo/myInfoUpdateSuccess.jsp
	 */
	
	HttpSession session = null;
	MemberVO member = null;
	private static final String CUR_VIEW = "/mypage/myInfo/myInfoUpdateForm.jsp";
	private static final String NEXT_VIEW = "/mypage/myInfo/myInfoUpdateSuccess.jsp";
	private InfoUpdateService infoUpSvc = new InfoUpdateService();
	private String path = "";

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		session = request.getSession();
		
		//session 정보가 없으면 processForm
		if(session.getAttribute("AUTHUSER") == null) {
			return processForm(request, response);
		
		//session 정보가 있으면 process 실행
		}else {
			
			//post방식이면 processSubmit
			if(request.getMethod().equalsIgnoreCase("POST")) {
				return processSubmit(request,response);
				
			//포스트 방식 아니면...
			}else {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return null;
			}
		}
	}
	
	
	//미로그인시
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		path = request.getRequestURI(); 
		request.setAttribute("path", path);
		
		return "/member/login.jsp";
	}
	
	
	/* post방식 
	 *  - 실패시 /mypage/myInfo/myInfoUpdateForm.jsp
	 *        기존에 입력했던 값을 가지고 보내주기
	 *  - 성공시 /mypage/myInfo/myInfoUpdateSuccess.jsp
	 */
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//기존의 회원 정보(변경전)
		Connection conn = ConnectionProvider.getConnection();
		MemberDAO memberDao = new MemberDAO();
		String mid = (String)session.getAttribute("ID");
		member = memberDao.selectById2(conn, mid);
		
		//회원이 입력한 파라미터값 받기
		String changeNick = request.getParameter("changeNickName");
		String changeMail = request.getParameter("changeMail");
		String changePhone = request.getParameter("changePhone");
		
		MemberVO user = new MemberVO(); //수정한 회원정보를 저장할 객체 생성
		
		String id = member.getId();     //기존의 회원 아이디(변경X)
		String name = member.getName(); //기존의 회원 이름(변경X)
		
		Map<String,Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		
		//수정한 회원정보 저장
		user.setId(id);
		user.setName(name);
		user.setNickname(changeNick);
		user.setMail(changeMail);
		user.setPhone(changePhone);
		
		if(changeNick == null || changeNick.isEmpty()) {
			errors.put("checkNick", Boolean.TRUE);
		}
		if(changeMail == null || changeMail.isEmpty()) {
			errors.put("checkMail", Boolean.TRUE);
		}
		if(changePhone == null || changePhone.isEmpty()) {
			errors.put("checkPhone", Boolean.TRUE);
		}
		if((member.getNickname().equals(changeNick)) && 
				(member.getMail().equals(changeMail)) && 
				(member.getPhone().equals(changePhone))) {
			errors.put("noChange", Boolean.TRUE);
		}
		// 에러가 발생했다면 입력했던 정보 가지고 다시 수정폼 보내주기
		if(!errors.isEmpty()) {
			request.setAttribute("user",user);
			return CUR_VIEW;
		}
		
		
		try {
			
			user = infoUpSvc.infoUpdate(id, changeNick, changeMail, changePhone);
			System.out.println("user는!!!"+user.toString());
			
			session.setAttribute("MNO", id);
			session.setAttribute("NICKNAME", changeNick);
			
			System.out.println("회원정보 수정완료");
			return NEXT_VIEW;
			
		}catch(DuplicateNickException e) {
			errors.put("dupliceteNick", Boolean.TRUE);
			request.setAttribute("user",user);
			return CUR_VIEW;
			
		}catch(DuplicateMailException e) {
			errors.put("dupliceteMail", Boolean.TRUE);
			request.setAttribute("user",user);
			return CUR_VIEW;
			
		}catch(DuplicatePhoneException e) {
			errors.put("duplicetePhone", Boolean.TRUE);
			request.setAttribute("user",user);
			return CUR_VIEW;
			
		}catch(MemberNotFoundException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return "/mypage/myInfo/myInfoUpdateFail_NoMember.jsp";
		}catch(Exception e){
			e.getStackTrace();
			
			path = request.getRequestURI(); 
			request.setAttribute("path", path);
			
			return "/mypage/myInfo/myInfoUpdateFail.jsp";
		}
	}

}
