package mypage.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.FreeBoardVO;
import dto.MyStockVO;
import dto.StockVO;
import mypage.service.MyBoardListService;
import mypage.service.MyInterestListService;
import simulation.service.MyInvestListService;

public class MypageListHandler implements CommandHandler {

	//마이페이지 핸들러

	HttpSession session = null;
	private static final String FORM_VIEW ="/member/login.jsp";  // 로그인페이지
	
	MyInterestListService myInterestListSvc = new MyInterestListService();
	MyBoardListService myBoardListService = new MyBoardListService();
	MyInvestListService myInvestListService = new MyInvestListService();
	List<StockVO> myInterestList = null;
	List<StockVO> myInterestList5 = new ArrayList<StockVO>();
	List<FreeBoardVO> myBoardList = null;
	ArrayList<MyStockVO> myInvestList = null;
	private String path = "";
	
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
		path = request.getRequestURI(); 
		request.setAttribute("path", path);
		System.out.println("MypageListHandler의 path="+path);
		return FORM_VIEW;
	}
	
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			int mno = (int)session.getAttribute("MNO");
			String fmemnick = (String)session.getAttribute("NICKNAME");
			
			
			
			//관심주식 리스트 가져오기(최대 5개)
			myInterestList = myInterestListSvc.myInterestListService(mno);
			request.setAttribute("myInterestList", myInterestList);
			//해당 회원의 관심주식회사가 없는 경우 없다는 멘트 띄우기
			if(myInterestList.size()<1) {
				request.setAttribute("noInterest", Boolean.TRUE);
			}
			
			
			
			//가상투자 리스트 가져오기(최대 5개)
			ArrayList<MyStockVO> myInvestList = myInvestListService.getMyList(mno);
			request.setAttribute("myInvestList", myInvestList);
			//해당 회원의 게시글이 없는 경우 없다는 멘트 띄우기
			if(myInvestList.size()<1 | myInvestList==null) {
				request.setAttribute("noInvest", Boolean.TRUE);
			}
			
			
			
			//내 게시글 리스트 가져오기(최신순으로 최대 5개까지)
			myBoardList = myBoardListService.boardListService5(fmemnick);
			request.setAttribute("myBoardList", myBoardList);
			//해당 회원의 게시글이 없는 경우 없다는 멘트 띄우기
			if(myBoardList.size()<1 | myBoardList==null) {
				request.setAttribute("noBoard", Boolean.TRUE);
			}
			
			
			return "/mypage/mypage.jsp";
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//예외 발생시 일단 로그인화면
			return FORM_VIEW;
		}
	}

}
