package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ArticleNotFoundException;
import comment.service.ListComment;
import comment.service.ListCommentService;
import controller.command.CommandHandler;
import dto.FreeBoardVO;
import mypage.service.MyBoardReadService;

public class MyBoardReadHandler implements CommandHandler {
	private MyBoardReadService readService = new MyBoardReadService();
	private ListCommentService listCommentService = new ListCommentService();
	String FORM_VIEW = "/mypage/myBoard/myBoardContent.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("MyBoardReadHandler.process진입");

		// 글 번호를 넘겨줌
		String fnoVal = req.getParameter("fno");
		int fno = Integer.parseInt(fnoVal);

		// 댓글 페이지 번호를 넘겨줌
		String commentPageNoVal = req.getParameter("commentPageNo");
		int commentPageNo = 1;
		if( commentPageNoVal != null) {
			commentPageNo = Integer.parseInt(commentPageNoVal);
		}
		
		//선택된 게시글 읽어오기
		try {
			FreeBoardVO boardVO = readService.getMyBoard(fno);
			req.setAttribute("board", boardVO); // jsp에서 사용하기위한 객체로 저장
		} catch (ArticleNotFoundException e) {
			req.getServletContext().log("no article", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			System.out.println("여기1");
			return null;
		}
		
		//게시글에 해당하는 댓글 읽어오기
		try {
			ListComment listcomment = listCommentService.getComments(fno,commentPageNo);
			req.setAttribute("listcomment", listcomment); // jsp에서 사용하기위한 객체로 저장
			return FORM_VIEW;
			
		} catch (ArticleNotFoundException e) {
			req.getServletContext().log("no Comment", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			System.out.println("여기2");
			return null;
		}
	}
}
