package admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.NoticeDeleteService;
import controller.command.CommandHandler;

//게시글을 삭제하는 로직
public class NoticeDeleteHandler implements CommandHandler {

	NoticeDeleteService noticeDeleteService=new NoticeDeleteService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String nno=request.getParameter("no");//삭제할 게시글 번호 받아오기
		int no = Integer.parseInt(nno);
		
		System.out.print("NoticeDeleteHandler 진입-");
		System.out.println(no+"번 글 삭제 완료");
		
		noticeDeleteService.deleteService(no);
		
		request.setAttribute("service", "delete");
		
		return "noticeManage.do";
	}

}
