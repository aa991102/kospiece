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
		System.out.print("NoticeWriteHandler 진입성공 ");

		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.print("get방식 ");
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.print("post방식 ");
			return processWrite(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		return FORM_VIEW;
	}
	private String processWrite(HttpServletRequest request, HttpServletResponse response) {
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		noticeWriteService.service(title,content);//통계값 리턴받아 통계객체에 저장
		
		return "/noticeManage.do";
	}
}
