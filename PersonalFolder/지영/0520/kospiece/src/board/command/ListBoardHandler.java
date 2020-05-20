package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ListBoard;
import board.service.ListBoardService;
import controller.command.CommandHandler;

public class ListBoardHandler implements CommandHandler {
	
	private ListBoardService listService = new ListBoardService();
	private static final String FORM_VIEW = "/board/board.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("\nboard/command/ListBoardHandler.process진입");
		String pageNoVal = req.getParameter("pageNo"); //현재 페이지
		int pageNo = 1;
		if( pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal); 
			//사용자가 페이지를 선택하면 pageNo에 사용자가 보려고 하는 페이지를 반환한다.
		}
		ListBoard listboard = listService.getBoardPage(pageNo);
		req.setAttribute("listboard", listboard);
		return FORM_VIEW;
	}

}
