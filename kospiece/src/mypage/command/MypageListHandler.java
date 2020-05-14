package mypage.command;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.MemberVO;
import dto.StockVO;
import jdbc.connection.ConnectionProvider;
import mypage.service.MyInterestListService;

public class MypageListHandler implements CommandHandler {

	//마이페이지 핸들러

	HttpSession session = null;
	private static final String FORM_VIEW ="/login.do";  // 로그인페이지
	
	MyInterestListService myInterestListSvc = new MyInterestListService();
	List<StockVO> myInterestList = null;
	List<StockVO> myInterestList5 = new ArrayList<StockVO>();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MyInterestListHandler-process()");
		
		//session 정보 가져오기
		session = request.getSession();
		
		if(session.getAttribute("AUTHUSER") == null) {//user session 정보가 없으면 login.do로 강제이동
			return processForm(request, response);
		}else {//user session 정보가 있으면 process 실행
			return processSubmit(request, response);
		}
	}

	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}
	
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		MemberVO member = null;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			member = (MemberVO)request.getSession(false).getAttribute("AUTHUSER");
			int mno = member.getMno();
			myInterestList = myInterestListSvc.myInterestListService(mno);
			
			
			//해당 회원의 관심주식회사가 5개보다 많으면 5개만 가져오기
			System.out.println("myInterestList.size()="+myInterestList.size());
			if(myInterestList.size()>5) {
				if(myInterestList5.size()<5) {
					for(int i=0;i<5;i++) {
						myInterestList5.add(myInterestList.get(i));
					}
				}
				if(!myInterestList5.isEmpty()) {
					request.setAttribute("myInterestList", myInterestList5);
				}
				System.out.println("myInterestList5="+myInterestList5);
			}else {
				request.setAttribute("myInterestList", myInterestList);
			}
			return "/mypage/mypage.jsp";
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//예외 발생시 일단 로그인화면
			return FORM_VIEW;
		}
	}

}
