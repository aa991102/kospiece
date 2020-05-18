package board.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ArticleNotFoundException;
import board.service.ModifyBoardService;
import board.service.PermissionDeniedException;
import board.service.ReadBoardService;
import controller.command.CommandHandler;
import dto.FreeBoardVO;

public class ModifyBoardHandler implements CommandHandler {
	private static final String FORM_VIEW = "/board/boardModify.jsp";

	private ReadBoardService readService = new ReadBoardService();
	private ModifyBoardService modifyService = new ModifyBoardService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("\nboard/command/ModifyBoardHandler.process진입");
		if (req.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("get방식요청");
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("post방식요청");
			return processSubmit(req, res);
		} else {
			System.out.println("요청없음");
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException{
		System.out.println("processForm 진입");
		try {
			String fnoVal = req.getParameter("fno");
			int fno = Integer.parseInt(fnoVal);
			//선택한 글 번호를 통해 board정보를 가져옴
			FreeBoardVO boardVO = readService.getBoard(fno, false);
			
			/*//세션정보와 글쓴이가 일치하는지 확인
			 * MemberVO member = (MemberVO) req.getSession(false).getAttribute("NICKNAME");
			 * if (!canModify(member, boardVO)) { 
			 * res.sendError(HttpServletResponse.SC_FORBIDDEN); return null; }
			 */
			
			req.setAttribute("board", boardVO);
			return FORM_VIEW;
			
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	/*
	 * private boolean canModify(MemberVO member, FreeBoardVO boardVO) { String
	 * writerNickname = boardVO.getNickname(); return
	 * member.getNickname().equals(writerNickname); }
	 */
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) 
	throws Exception {
		String fnoVal = req.getParameter("fno");
		int fno = Integer.parseInt(fnoVal);
		
		//현재 글 정보 받아오기
		FreeBoardVO boardVO = readService.getBoard(fno, false);
		//VO에 말머리, 제목, 컨텐트만 수정
		boardVO = new FreeBoardVO(fno,	
				req.getParameter("horsehead"),
				req.getParameter("title"),
				req.getParameter("content")); 
		
		req.setAttribute("board", boardVO); // jsp에서 쓰기 위한 객체선언
		
		try {
			modifyService.modify(boardVO); //수정 쿼리 요청
			return "/board.do";
		}catch( ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch(PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}



}
