package comment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ListBoard;
import comment.service.ListComment;
import comment.service.ListCommentService;
import controller.command.CommandHandler;

public class ListCommentHandler implements CommandHandler {

	private ListCommentService listCommentService = new ListCommentService();
	String FORM_VIEW = "/board/boardContent.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("\ncomment/command/ListCommentHandler.process진입");
		String commentPageNoVal = req.getParameter("commentPageNo"); //현재 페이지
		
		int commentPageNo = 1;
		if( commentPageNoVal != null) {
			commentPageNo = Integer.parseInt(commentPageNoVal); 
			//사용자가 페이지를 선택하면 pageNo에 사용자가 보려고 하는 페이지를 반환한다.
		}
		ListComment listcomment = listCommentService.getCommentPage(commentPageNo);
		req.setAttribute("listcomment", listcomment);
		return FORM_VIEW;
		}
}
