package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ArticleNotFoundException;
import board.service.ReadBoardService;
import controller.command.CommandHandler;
import dto.FreeBoardVO;
import dto.MemberVO;

public class ReadBoardHandler implements CommandHandler {
	private ReadBoardService readService = new ReadBoardService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//페이지 번호를 넘겨줌
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = Integer.parseInt(pageNoVal); 

		//글 번호를 넘겨줌
		String fnoVal = req.getParameter("fno");
		int fno = Integer.parseInt(fnoVal);
		try {
			FreeBoardVO boardVO = readService.getBoard(fno, true);
			req.setAttribute("board", boardVO); //jsp에서 사용하기위한 객체로 저장
			return "/board/boardContent.jsp";
			
		} catch (ArticleNotFoundException e) {
			req.getServletContext().log("no article", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
