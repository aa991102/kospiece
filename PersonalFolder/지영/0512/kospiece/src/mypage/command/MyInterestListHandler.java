package mypage.command;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.MemberVO;
import dto.StockVO;
import jdbc.connection.ConnectionProvider;
import mypage.service.MyInterestListService;

public class MyInterestListHandler implements CommandHandler {
	
	/* 관심주식 조회 핸들러
	 * 
	 */
	
	HttpSession session = null;
	MyInterestListService myInterestListSvc = new MyInterestListService();
	List<StockVO> myInterestList = null;
	
	private static final String FORM_VIEW ="/login.do";  // 로그인페이지
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("MyInterestListHandler-process()");
		//session 정보 가져오기
		session = req.getSession();
		if(session == null) {//user session 정보가 없으면 login.jsp로 강제이동
			return processForm(req, res);
		}else {//user session 정보가 있으면 process 실행
			return processSubmit(req, res);
		}
		
	}//process() end
	
	
	//로그인 안했을때
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}//processForm() end


	//로그인 했을때 
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		MemberVO member = null;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			member = (MemberVO)req.getSession(false).getAttribute("AUTHUSER");
			int mno = member.getMno();
			myInterestList = myInterestListSvc.myInterestListService(mno);
			System.out.println("processSubmit-myInterestList"+myInterestList.toString());
			
			int myListSize = myInterestList.size();
			/*
			int i = 0;
			for(StockVO myList : myInterestList) {
				
				req.setAttribute("myList"+i, myList);
				++i;
			}
			*/
			
			req.setAttribute("myListSize", myListSize);
			req.setAttribute("myInterestList", myInterestList);
			
			return "/mypage/myInterest.jsp";
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//예외 발생 시 일단 로그인화면
			return FORM_VIEW;
		}
		
	}//processSubmit() end

}

