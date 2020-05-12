package article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import article.MODEL.Writer;
import article.service.WriteArticleService;
import auth.service.LoginFailException;
import auth.service.User;
import mvc.command.CommandHandler;

//p640
//글등록 폼 및 글등록 요청 담당 컨트롤러
/* 글등록 폼 요청 	/article/write.do	(GET방식)	
 * 뷰 		/view/article/newArticleForm.jsp
 * 
 * 글등록 요청	/article/write.do  (POST방식)
 * 성공시 		/view/article/newArticleAccess.jsp
 * 실패시		/view/article/newArticleForm.jsp
 */

public class WriteArticleHandler implements CommandHandler {
	//View
	private static final String FORM_VIEW = "/view/article/newArticleForm.jsp";
	
	//Service
	private WriteArticleService writeService = new WriteArticleService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WriteArticleHandler의 process()진입");

		if(request.getMethod().equalsIgnoreCase("GET")) { //GET방식으로 요청 들어오면
			System.out.println("newArticleForm.jsp의 method방식="+request.getMethod());
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) { //POST방식으로 요청이 들어오면
			System.out.println("newArticleForm.jsp의 method방식="+request.getMethod());
			return processSubmit(request,response);
		}else {
			//405에러
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	
	//GET방식으로  요청이 들어오면  폼(/view/article/newArticleForm.jsp)을 보여주기
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("WriteArticleHandler의 processForm()호출");
		return FORM_VIEW;
	}
	
	
	//POST방식으로 요청이 들어오면
	private String processSubmit(HttpServletRequest request,HttpServletResponse response)throws Exception {
		System.out.println("WriteArticleHandler의 processSubmit()진입");
		Map<String,Boolean> errors = new HashMap<>();
		request.setAttribute("errors",errors);
		//1. 파라미터 받기
		String title = request.getParameter("title"); //제목
		String content= request.getParameter("content"); //내용
		
		
		//2. 비즈니스로직(->Service -> DAO -> DB) - p640 39
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("AUTHUSER");
		/* new User(member.getId(),member.getName())*/
		
		
		//글등록 - p641 40
		WriteRequest writeReq = createWriteRequest(user,title,content);
		writeReq.validate(errors);
		
		//유효성검사를 불통하여 글등록폼으로 이동
		if( !errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		//db에  insert성공시의 해당글번호가 newArticleNo에 리턴
		int newArticleNo = writeService.write(writeReq);
		
		
		//3. Model(Request,session)
		request.setAttribute("newArticleNo",newArticleNo);
		
		//4. View
		
		return "/view/article/newArticleAccess.jsp";
		
	}
	
	
	//641 53
	private WriteRequest createWriteRequest(User user, String title,String content) {
		
		return new WriteRequest(
								new Writer(user.getId(),user.getName()),
								title,
								content
								);
	}//createWriteRequest

}





