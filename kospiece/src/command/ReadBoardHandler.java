package board.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ArticleNotFoundException;
import board.service.ReadBoardService;
import comment.service.LikeCountService;
import comment.service.ListComment;
import comment.service.ListCommentService;
import controller.command.CommandHandler;
import dao.SessionffbhDAO;
import dto.FreeBoardVO;
import dto.SessionffbhVO;
import util.ConnectionProvider;

public class ReadBoardHandler implements CommandHandler {
	private ReadBoardService readService = new ReadBoardService();
	private ListCommentService listCommentService = new ListCommentService();
	private LikeCountService countservice = new LikeCountService();
	private SessionffbhVO sessionVO = new SessionffbhVO();
	Date accesstime = new Date();
	String FORM_VIEW = "/board/boardContent.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("\nReadBoardHandler.process진입");

		// 글 번호를 넘겨줌
		String fnoVal = req.getParameter("fno");
		int fno = Integer.parseInt(fnoVal);
		
		// 조회수 처리를 위한 세션정보 받아옴
		accesstime.setTime(req.getSession(false).getLastAccessedTime()); //long -> Date로 형변환
		String sessionid = req.getSession(false).getId();
		sessionVO = new SessionffbhVO(fno, sessionid ,accesstime);
		
		// 댓글 페이지 번호를 넘겨줌
		String commentPageNoVal = req.getParameter("commentPageNo");
		int commentPageNo = 1;
		if (commentPageNoVal != null) {
			commentPageNo = Integer.parseInt(commentPageNoVal);
		}

		// 선택된 게시글 읽어오기
		try {
			//sessionCheck = true이면 조회수 증가
			FreeBoardVO boardVO = readService.getBoard(fno, sessionCheck(req,sessionVO)); 
			req.setAttribute("board", boardVO); // jsp에서 사용하기위한 객체로 저장
		} catch (ArticleNotFoundException e) {
			req.getServletContext().log("no article", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// 게시글에 해당하는 댓글 읽어오기
		try {
			ListComment listcomment = listCommentService.getComments(fno, commentPageNo);
			req.setAttribute("listcomment", listcomment); // jsp에서 사용하기위한 객체로 저장
			return FORM_VIEW;

		} catch (ArticleNotFoundException e) {
			req.getServletContext().log("no Comment", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	// 글 번호, 세션id, 세션 최근 접근 시간을 남겨 글 번호,
	// 세션id가 일치할 때 세션최근 접근시간이 10분이 넘을때만 조회수를 증가시킴) {
	// 만약 세션이 종료되면, 관련 테이블에 세션id에 관련된 모든 정보를 삭제한다.
	private boolean sessionCheck(HttpServletRequest req, SessionffbhVO sessionVO) throws SQLException {
		SessionffbhDAO sessionDao = new SessionffbhDAO();
		Calendar cal = Calendar.getInstance();

		try (Connection conn = ConnectionProvider.getConnection()) {
			System.out.println("전달받은 sessionVO = "+sessionVO.toString());
			//db의 세션 시간정보 +30분해서 가져오기
	        cal.setTime(sessionDao.select(conn, 
	        		sessionVO.getFno(),sessionVO.getSessionid()));
	        cal.add(Calendar.MINUTE, 30);
	        
			//compare : DB날짜와 현재 시간을 비교한 결과를 넣음
			int compare = new Date().compareTo(cal.getTime());
			
			System.out.println("compare = "+compare
			+"\nselect = "	+ 	sessionDao.select(conn, sessionVO.getFno(),sessionVO.getSessionid())
			+"\ngetTime = "+cal.getTime());
			
			if(compare > 0) {
				sessionDao.insert(conn, sessionVO);
				return true;
			}else if(compare<=0)
				return false;
			else {
				return false;
			}
		}
	}
}
