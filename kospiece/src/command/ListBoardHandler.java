package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ListBoard;
import board.service.ListBoardService;
import controller.command.CommandHandler;

public class ListBoardHandler implements CommandHandler {

	private ListBoardService listService = new ListBoardService();
	private static final String FORM_VIEW = "/board/board.jsp";
	String method;
	String content;

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("\nboard/command/ListBoardHandler.process진입");
		method = req.getParameter("searchMethod");
		content = req.getParameter("searchValue");
		System.out.println("전달받은 method = " + method + "\n전달받은 content = " + content);

		if (content == (null)) { // 검색결과가 없을 시
			return processForm(req, res);
		} else { // 검색결과가 있을 시
			return processSubmit(req, res);
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("\nprocessSubmit진입");

		String pageNoVal = req.getParameter("pageNo"); // 현재 페이지
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}

		ListBoard listboard = listService.getSearchBoardPage(pageNo, method, content);
		req.setAttribute("listboard", listboard);
		req.setAttribute("method", method);
		req.setAttribute("content", content);
		return FORM_VIEW;
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("\nprocessForm진입");
		String pageNoVal = req.getParameter("pageNo"); // 현재 페이지
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
			// 사용자가 페이지를 선택하면 pageNo에 사용자가 보려고 하는 페이지를 반환한다.
		}
		ListBoard listboard = listService.getBoardPage(pageNo);
		req.setAttribute("listboard", listboard);
		return FORM_VIEW;
	}

}
