package myComment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.LikeCountService;
import controller.command.CommandHandler;

public class LikeCountHandler implements CommandHandler {

	LikeCountService countService = new LikeCountService();
	private String FORM_VIEW ="/myBoardRead.do";
	String path="";
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("\n좋아요 핸들러진입");
		
		String id = (String) req.getSession(false).getAttribute("ID");
		if (id == null) {//session 정보가 없으면 login화면으로 이동
			return processForm(req, res);
		}else {
			return processSubmit(id, req,res); //세션의 id를 파라미터 넘김
		}
	}

	private String processSubmit(String id, HttpServletRequest req, HttpServletResponse res) {
		System.out.println("-> processSubmit 진입");
		String commentNoVal = req.getParameter("comment"); 
		int commentNo = Integer.parseInt(commentNoVal);
		System.out.println("전달받은 id와 commentNo = "+id+commentNo);
		
		countService.increaseCount(commentNo, id); //service 호출
		
		return FORM_VIEW;
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("-> processForm 진입");
		path = req.getRequestURI();
		req.setAttribute("path",path);
		
		return "/login.do";
	}
		

}
