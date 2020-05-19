package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ArticleNotFoundException;
import board.service.DeleteBoardService;
import controller.command.CommandHandler;

public class DeleteBoardHandler implements CommandHandler {

	DeleteBoardService deleteService = new DeleteBoardService();
	private static final String FORM_VIEW = "/board.do";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("\nboard/command/DeleteBoardHandler.process진입");
		String noVal = req.getParameter("fno"); //현재 페이지
		int fno = 0;
		if(noVal != null) {
			fno = Integer.parseInt(noVal);  
			//사용자가 페이지를 선택하면 pageNo에 사용자가 보려고 하는 페이지를 반환한다.
		}else {
			System.out.println("fno 파라미터를 전달받지 못함");
			throw new ArticleNotFoundException();
		}
		int success = deleteService.deleteBoard(fno);
		if(success==1) {
			System.out.println("삭제 성공");
		}else {
			System.out.println("삭제 실패");
		}
		return FORM_VIEW;
	}

}
