package admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.NoticeWriteService;
import controller.command.CommandHandler;

public class NoticeWriteHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/admin/noticeWrite.jsp";
	
	NoticeWriteService noticeWriteService = new NoticeWriteService();  //서비스객체 생성
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		
		System.out.println("NoticeWriteHandler진입-공지사항 등록 완료");

		String title=request.getParameter("title");
		String content=request.getParameter("content");
		noticeWriteService.service(title,content);//통계값 리턴받아 통계객체에 저장
		
		return "/noticeManage.do";
	}
}
