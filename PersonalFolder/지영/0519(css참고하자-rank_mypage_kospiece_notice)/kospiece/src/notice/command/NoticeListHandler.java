package notice.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.CommandHandler;
import dto.NoticeVO;
import notice.service.NoticeListService;
import notice.service.NoticePage;

//공지사항 리스트를 보여줌
public class NoticeListHandler implements CommandHandler {

	private static final String FORM_VIEW = "/notice/notice.jsp";
	
	NoticeListService noticeListService = new NoticeListService(); //서비스객체 생성
	List<NoticeVO> noticeList=new ArrayList<NoticeVO>();
	
	String column;
	String value;
	String page;
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("NoticeListHandler 진입 ");
		
		column=request.getParameter("search");
		value=request.getParameter("content");
		page=request.getParameter("page");

		if(value==null) {
			System.out.println("검색내용 없을 때");
			return processTotalNotice(request,response);
		}else if(value!=null) {
			System.out.print("검색내용 있을 때 ");
			return processSelectedNotice(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processTotalNotice(HttpServletRequest request, HttpServletResponse response) {
		//검색내용 없을 때(처음화면과 페이징 눌렀을 때 화면)
		
		NoticePage noticePage;
		int pageNo;
		
		if(page==null) {//처음화면
			pageNo=1;
		}else {
			pageNo=Integer.parseInt(page);
			System.out.println("공지사항"+pageNo+"페이지");
		}
		
		noticePage = noticeListService.noticeListService(pageNo);
		
		request.setAttribute("noticePage",noticePage);
		request.setAttribute("Total", true);
		//페이지에서 출력할 공지사항 객체 arrayList를 request속성에 담아보내기
		//<1번글객체,2번글객체.....>
		
		return FORM_VIEW;
		
	}
	private String processSelectedNotice(HttpServletRequest request, HttpServletResponse response) {
		//검색내용 있을 때
		
		NoticePage noticePage;
		int pageNo;
		
		if(request.getMethod().equalsIgnoreCase("POST")) {//처음화면
			pageNo=1;
			System.out.print("검색시 처음화면-");
		}else {
			pageNo=Integer.parseInt(page);
			System.out.print(pageNo+"페이지-");
		}
		
		System.out.println(column+"컬럼의 "+value+"라는 내용이 포함되는 공지사항만 출력");
		
		noticePage=noticeListService.noticeListService(pageNo,column,value);
		
		request.setAttribute("search",column);
		request.setAttribute("content",value);
		request.setAttribute("Total", false);
		request.setAttribute("noticePage",noticePage);
		//페이지에서 출력할 공지사항 객체 arrayList를 request속성에 담아보내기
		//<1번글객체,2번글객체.....>
		
		return FORM_VIEW;
		
	}
}
