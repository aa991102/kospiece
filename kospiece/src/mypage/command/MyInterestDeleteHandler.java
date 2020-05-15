package mypage.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.StockVO;
import mypage.service.MyInterestDeleteService;

public class MyInterestDeleteHandler implements CommandHandler {

	/* 관심주식 삭제 핸들러
	 * 삭제 성공시: /myInterest.do
	 * 삭제 실패시: /myInterest.do
	 */
	
	HttpSession session = null;
	MyInterestDeleteService myInterestDeleteSvc = new MyInterestDeleteService();
	List<StockVO> myInterestList = null;
	
	private static final String FORM_VIEW ="myInterest.do";  // 로그인페이지
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("MyInterestDeleteHandler-process()");
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

	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		String[] snameArray = req.getParameterValues("sname");
		int mno = (int)session.getAttribute("MNO");
		
		if(snameArray == null) {
			req.setAttribute("errors", "삭제할 주식을 선택해주세요");
		}
		
		try {
		
			myInterestDeleteSvc.myInterestDeleteService(mno, snameArray);
			
		} catch (Exception e) {
			System.out.println("MyInterestDeleteHandler-processSubmit() 오류발생");
			e.printStackTrace();
		}
		
		return FORM_VIEW;
		
	}//processSubmit() end

}
