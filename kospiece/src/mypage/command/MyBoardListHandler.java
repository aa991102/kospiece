package mypage.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.FreeBoardVO;
import mypage.service.MyBoardListService;
import mypage.service.MyBoardPage;

public class MyBoardListHandler implements CommandHandler {

	private static final String FORM_VIEW = "/mypage/myBoard.jsp";
	private String path = "";
	MyBoardListService myBoardistService = new MyBoardListService(); //서비스객체 생성
	List<FreeBoardVO> myList = new ArrayList<FreeBoardVO>();
	HttpSession session = null;
	String column;
	String value;
	String page;
	String fmemnick;
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		System.out.print("MyBoardListHandler 진입 ");
		
		//session 정보 가져오기
		session = request.getSession();
		//session 정보가 없으면 /member/login.jsp로 강제이동
		if(session.getAttribute("AUTHUSER") == null) {
			return processForm(request, response);
		}
		
		fmemnick = (String)request.getSession().getAttribute("NICKNAME");
		column=request.getParameter("search");
		value=request.getParameter("content");
		page=request.getParameter("page");
		System.out.println(column+value+page);
		
		//검색내용 없을 때
		if(value==null) {
			return processTotal(request,response);
		//검색내용 있을 때
		}else if(value!=null) {
			return processSelected(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		path = request.getRequestURI(); 
		request.setAttribute("path", path);
		
		return "/member/login.jsp";
	}


	//검색내용 없을 때(처음화면과 페이징 눌렀을 때 화면)
	private String processTotal(HttpServletRequest request, HttpServletResponse response) {
		
		MyBoardPage myBoardPage;
		int pageNo;
		
		if(page==null) {//처음화면
			pageNo=1;
			System.out.print("처음화면 ");
		}else {
			pageNo=Integer.parseInt(page);
			System.out.print(pageNo+"페이지");
		}
		
		myBoardPage = myBoardistService.boardListService(pageNo,fmemnick);
		
		request.setAttribute("myBoardPage",myBoardPage);
		request.setAttribute("Total", true);
		//페이지에서 출력할 공지사항 객체 arrayList를 request속성에 담아보내기
		//<1번글객체,2번글객체.....>
		
		return FORM_VIEW;
		
	}
	
	
	//검색내용 있을 때
	private String processSelected(HttpServletRequest request, HttpServletResponse response) {
		
		MyBoardPage myBoardPage;
		int pageNo;
		
		if(request.getMethod().equalsIgnoreCase("POST")) {//처음화면
			pageNo=1;
			System.out.println("검색시 처음화면");
		}else {
			pageNo=Integer.parseInt(page);
			System.out.println(pageNo+"페이지");
		}
		
		System.out.print(column+"컬럼의 "+value+"가 들어있는 공지사항만 출력");
		
		myBoardPage=myBoardistService.boardListService(pageNo,column,value,fmemnick);
		
		request.setAttribute("search",column);
		request.setAttribute("content",value);
		request.setAttribute("Total", false);
		request.setAttribute("myBoardPage",myBoardPage);
		//페이지에서 출력할 공지사항 객체 arrayList를 request속성에 담아보내기
		//<1번글객체,2번글객체.....>
		
		return FORM_VIEW;
		
	}

}
