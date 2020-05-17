package admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.NoticeModifyService;
import controller.command.CommandHandler;
import dto.NoticeVO;

public class NoticeModifyHandler implements CommandHandler  {

	private static final String FORM_VIEW = "/admin/noticeModify.jsp";
	NoticeModifyService noticeModifyService=new NoticeModifyService();
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("NoticeModifyHandler 진입 ");
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String nno=request.getParameter("no");
		int no = Integer.parseInt(nno);
		
		NoticeVO notice = new NoticeVO();
		
		if(title==null) {
			System.out.println(no+"번 글 공지사항 변경 form");
			notice=noticeModifyService.selectNotice(no);
			request.setAttribute("notice", notice);
			return FORM_VIEW;
		}else {
			System.out.println(no+"번 글 공지사항 변경 완료");
			noticeModifyService.writeNotice(no,title,content);
			return "noticeManage.do";
		}
		
	}
}
