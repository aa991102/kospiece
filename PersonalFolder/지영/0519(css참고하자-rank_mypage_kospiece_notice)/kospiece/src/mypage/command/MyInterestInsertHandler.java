package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import mypage.service.MyInterestInsertService;

public class MyInterestInsertHandler implements CommandHandler {

	/*관심주식 추가  핸들러
	 * 추가 성공시: /myInterest.do
	 * 추가 실패시: /myInterest.do
	 */
	
	HttpSession session = null;
	private static final String FORM_VIEW ="/myInterest.do";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MyInterestInsertHandler-process()");
		
		//session 정보 가져오기
		session = request.getSession();
		
		if(session == null) {//user session 정보가 없으면 login.do로 강제이동
			return processForm(request, response);
		}else {//user session 정보가 있으면 process 실행
			return processSubmit(request, response);
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("MyInterestInsertHandler-processSubmit()");
		
		//파라미터 가져오기
		String sname = request.getParameter("snameSearh");
		session = request.getSession();
		int mno = (int)session.getAttribute("MNO");
		
		//비즈니스 수행
		MyInterestInsertService service = new MyInterestInsertService();
		
		String sno = service.selectSnoBySname(sname);
		
		try {
			service.insertMyInterest(mno, sno);
			
			//model 
			if(sno == null ) {
				request.setAttribute("errors", "해당하는 회사정보가 없습니다.");
			}
		}catch(Exception e) {
			//일단은 실패할 경우를 회사명 중복된 경우라고 하겠다!
			request.setAttribute("errors", "이미 존재하는 회사정보입니다.");
		}
		//view 지정
		return FORM_VIEW;
	}

}
