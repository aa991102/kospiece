package mypage.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.MemberVO;
import dto.StockVO;
import mypage.service.MyBoardDeleteService;

public class MyBoardDeleteHandler implements CommandHandler {

	/* 내 게시글 삭제 핸들러
	 * 삭제 성공시: /myBoardList.do
	 * 삭제 실패시: /myBoardList.do
	 */
	
	HttpSession session = null;
	MyBoardDeleteService myBoardDeleteSvc = new MyBoardDeleteService();
	List<StockVO> myInterestList = null;
	MemberVO member;
	private String path = "";
	private static final String FORM_VIEW ="/myBoardList.do";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("MyBoardDeleteHandler-process()");
		//session 정보 가져오기
		session = req.getSession();
		member = (MemberVO)session.getAttribute("AUTHUSER");
		
		if(member == null) {//user session 정보가 없으면 login.jsp로 강제이동
			return processForm(req, res);
		}else {//user session 정보가 있으면 process 실행
			return processSubmit(req, res);
		}
		
	}//process() end
	
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		path = req.getRequestURI(); 
		req.setAttribute("path", path);
		return FORM_VIEW;
	}//processForm() end

	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		String[] fnoArray = req.getParameterValues("fno");
		
		if(fnoArray == null) {
			req.setAttribute("errors", "삭제할 게시글을 선택해주세요");
		}
		
		try {
		
			myBoardDeleteSvc.myBoardDeleteService(fnoArray);
			
		} catch (Exception e) {
			System.out.println("MyBoardDeleteHandler-processSubmit() 오류발생");
			e.printStackTrace();
		}
		
		return FORM_VIEW;
		
	}//processSubmit() end

}
