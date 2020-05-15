package board.command;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.WriteBoardService;
import controller.command.CommandHandler;
import dto.FreeBoardVO;
import dto.MemberVO;

public class WriteBoardHandler implements CommandHandler {
	private static final String FORM_VIEW = "/board/boardWrite.jsp";
	private WriteBoardService writeService = new WriteBoardService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("\nboard/command/WriteBoardHandler.process진입");
		if(req.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("get방식요청");
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("post방식요청");
			//System.out.println("제목 : "+req.getParameter("title"));
			//System.out.println("내용 : "+req.getParameter("content"));
			return processSubmit(req,res);
		}else {
			System.out.println("요청없음");
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("-> processForm 진입");
		return FORM_VIEW;
	}
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("-> processSubmit 진입");

	//요청파라미터받기
		//사용자 정보(세션)
		MemberVO member = (MemberVO)req.getSession(false).getAttribute("NICKNAME");
		System.out.println("전달받은 세션 정보"+member);
		//파라미터
		FreeBoardVO board = ParamToBoard(member, req);
		System.out.println("등록한 BoardVO파라미터 = \n"+board);
		/*//5.8 제목을 입력 안했을 시 alert문 띄워주는 기능 추가해야함	
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		*/
		
		int newArticleNo = writeService.write(board);
		req.setAttribute("newArticleNo", newArticleNo);
		
		return "/board.do";
	}

	private FreeBoardVO ParamToBoard(MemberVO member, HttpServletRequest req) {
		return new FreeBoardVO(
				req.getParameter("horsehead"), 
				member.getNickname(),
				req.getParameter("title"), 
				req.getParameter("content"),
				new Date());
	}

}
