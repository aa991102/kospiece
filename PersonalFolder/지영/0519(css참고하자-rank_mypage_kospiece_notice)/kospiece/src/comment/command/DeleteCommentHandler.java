package comment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.DeleteCommentService;
import controller.command.CommandHandler;

public class DeleteCommentHandler implements CommandHandler {

	DeleteCommentService deleteService = new DeleteCommentService();
	private String FORM_VIEW = "/board/read.do";
	@Override
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("deletecommentHandler진입");
		String commentNumVal = req.getParameter("comment");
		int commentNum = Integer.parseInt(commentNumVal);
		deleteService.delete(commentNum);
		return FORM_VIEW;
	}

}
