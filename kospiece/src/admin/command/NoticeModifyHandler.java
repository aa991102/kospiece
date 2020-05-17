package admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.CommandHandler;

public class NoticeModifyHandler implements CommandHandler  {

	private static final String FORM_VIEW = "/noticeManage.do";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		return FORM_VIEW;
	}

}
