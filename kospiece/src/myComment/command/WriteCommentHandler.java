package myComment.command;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.WriteCommentService;
import controller.command.CommandHandler;
import dto.FreeCommentVO;

public class WriteCommentHandler implements CommandHandler {

	private WriteCommentService writecomment = new WriteCommentService();
	String path="";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("WriteCommentHandler-process진입");
		
		String id = (String) req.getSession(false).getAttribute("ID");
		if (id == null) {//session 정보가 없으면 login화면으로 이동
			return processForm(req, res);
		}else {
			return processSubmit(req,res);
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("-> processSubmit 진입");
		//파라미터 받기
		String nickname = (String) req.getSession(false).getAttribute("NICKNAME");
		String fnoval = req.getParameter("fno"); 
		int fno = Integer.parseInt(fnoval);
		String content = req.getParameter("content");
		FreeCommentVO comment =  ParamToComment(fno, nickname, content);
		
		//유효성검사 5.18
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		writecomment.validate(errors, comment);
		if(!errors.isEmpty()) {
			return "/myBoardRead.do?fno="+fno;
		}

		writecomment.write(comment);
		
		req.setAttribute("writecomment", writecomment);
		return "/myBoardRead.do?fno="+fno;
	}

	private FreeCommentVO ParamToComment(int fno, String nickname, String content) {
		return new FreeCommentVO(	fno,	nickname, content, new Date());
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("-> processForm 진입");
		path = req.getRequestURI();
		req.setAttribute("path",path);
		
		return "/member/login.jsp";
	}
}
