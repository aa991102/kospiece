package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.CommandHandler;
import dto.NoticeVO;
import notice.service.NoticeDetailService;

public class NoticeDetailHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/notice/noticeDetail.jsp";
	
	NoticeDetailService noticeDetailService=new NoticeDetailService();//서비스객체 생성
	NoticeVO notice=new NoticeVO(); //vo객체 생성
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("NoticeDetailHandler 진입 ");

		int no= Integer.parseInt(request.getParameter("no"));//글번호 파라미터로 받아오기
		
		notice=noticeDetailService.selectNotice(no);//글번호로 select 로직 수행
		
		request.setAttribute("notice", notice);//글번호랑 맞는 notice객체를 리퀘스트에 set하기
		
		return FORM_VIEW;
		
	}
	
}
