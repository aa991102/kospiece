package board.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.WriteBoardService;
import board.service.WriteRequest;
import controller.command.CommandHandler;
import dto.MemberVO;

public class WriteBoardHandler implements CommandHandler {
	private static final String FORM_VIEW = "/board/boardWrite.jsp";
	private WriteBoardService writeService = new WriteBoardService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("board/command/WriteBoardHandler.process진입");
		if(req.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("\nget방식요청");
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("\npost방식요청");
			System.out.println("제목 : "+req.getParameter("title"));
			System.out.println("내용 : "+req.getParameter("content"));
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
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
	//요청파라미터받기
		//사용자 정보 확인
		MemberVO member = (MemberVO)req.getSession(false).getAttribute("AUTHUSER");
		System.out.println("로그인세션호출 : "+member);
		
		//요청 파라미터 받기
		//writeReq객체에 서블릿에서 전달받은
		//사용자의 닉네임, 사용자가 입력한 제목과 내용을 전달한다.
		WriteRequest writeReq = createWriteRequest(member, req);
		writeReq.validate(errors);
		
		//5.8 제목을 입력 안했을 시 alert문 띄워주는 기능 추가해야함
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newArticleNo = writeService.write(writeReq);
		req.setAttribute("newArticleNo", newArticleNo);
		
		return "/board/board.jsp";
	}

	private WriteRequest createWriteRequest(MemberVO member, HttpServletRequest req) {
		return new WriteRequest(member.getNickname(),
				req.getParameter("title"), req.getParameter("content"));
	}


}
