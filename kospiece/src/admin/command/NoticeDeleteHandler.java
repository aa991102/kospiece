package admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.NoticeDeleteService;
import controller.command.CommandHandler;

public class NoticeDeleteHandler implements CommandHandler {

	NoticeDeleteService noticeDeleteService=new NoticeDeleteService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String nno=request.getParameter("no");
		int no = Integer.parseInt(nno);
		
		noticeDeleteService.deleteService(no);
		
		return "noticeManage.do";
		
	}

}
