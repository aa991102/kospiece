package board.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import board.model.Writer;
import board.service.WriteBoardService;
import board.service.WriteRequest;
import controller.command.CommandHandler;

public class WriteBoardHandler implements CommandHandler {
	private static final String FORM_VIEW = "/board/boardWrite.jsp";
	private WriteBoardService writeService = new WriteBoardService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("게시판글쓰기process함수진입");
		if(req.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("get방식요청");
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("post방식요청");
			System.out.println("제목 : "+req.getParameter("title"));
			System.out.println("내용"+req.getParameter("content"));
			return processSubmit(req,res);
		}else {
			System.out.println("요청없음");
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	/*
	 * get방식으로 들어오면 글쓰기페이지 반환, 여기서 get방식으로 들어오게되는 경로를 설정해야함
	 * */
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		/*
		//현재 사용자 정보 확인(User 객체의 authUser파라미터로)
		//5.6 코드 작성 중 오류 session관련 현재 사용자정보를 따올 수 없음 - 구현해야함
		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		*/
		
		//사용자 정보 확인 임시 코드
		User user= new User("Test용id","Test용Nick");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newArticleNo = writeService.write(writeReq);
		req.setAttribute("newArticleNo", newArticleNo);
		
		return "/board/board.jsp";
	}

	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(new Writer(user.getId(), user.getNick()), 
				req.getParameter("title"), req.getParameter("content"));
	}


}
