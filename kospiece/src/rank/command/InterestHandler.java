package rank.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import rank.service.InterestService;

public class InterestHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/rank.do";
	InterestService interestService=new InterestService();
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("interestHandler 진입 ");
		
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.print("get방식 ");
			return processTotalList(request,response);//파라미터가 없으면
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.print("post방식 ");
			return processSelectedList(request,response);//파라미터가 있으면
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processTotalList(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	
	}
	private String processSelectedList(HttpServletRequest request, HttpServletResponse response) {
		
		//관심주식 추가인지 삭제인지 파라미터로 받아오기
		String interest=request.getParameter("interest");
		//해당 회사의 번호 받아오기
		String sno=request.getParameter("sno");
		//로그인한 회원번호 불러오기
		HttpSession session = request.getSession();
		int mno=(int) session.getAttribute("MNO");
		
		System.out.print("받은 회사명"+sno);
		
		if(interest.equals("plus")) {
		interestService.plusService(mno,sno);
		}
		if(interest.equals("delete")) {
		interestService.deleteService(mno,sno);
		}
		
		return FORM_VIEW;
	
	}
}
