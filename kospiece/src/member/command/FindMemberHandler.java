package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.CommandHandler;
import member.service.FindService;

public class FindMemberHandler implements CommandHandler{

	FindService find = new FindService();
	
	private String mname;
	private String mmail;
	private String mid;
	
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		mname = request.getParameter("mname");
		mmail = request.getParameter("mmail");
		mid = request.getParameter("mid");
		
		if(mid==null) {
			return poccessfirst(request,response);
		}else {
			return processSecond(request,response);
		}
		
	}


	//아이디 찾기
	private String poccessfirst(HttpServletRequest request, HttpServletResponse response) {
		
		//mname, mmail이 존재하는지 확인
		
		String result = find.findid(mname, mmail);
		System.out.println("result="+result);
		request.setAttribute("result", result);
		
		return "/user/findId.jsp";
	}
	

	private String processSecond(HttpServletRequest request, HttpServletResponse response) {
		
		boolean result = find.validateMember(mname,mmail,mid);
		
		if(result) {
			return "/user/ChangePw.jsp";
		}else {
			request.setAttribute("error", "존재하지 않는 회원 입니다.");
			return "/user/findPw.jsp";
		}
		
		
		
		
	}

	
	
}
