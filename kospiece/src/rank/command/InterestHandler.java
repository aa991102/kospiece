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
		System.out.print("interestHandler진입-");
		
		//관심주식 추가인지 삭제인지 파라미터로 받아오기
		String interest=request.getParameter("interest");
		//해당 회사의 번호 받아오기
		String sno=request.getParameter("sno");
		//로그인한 회원번호 불러오기
		HttpSession session = request.getSession();
		int mno=(int) session.getAttribute("MNO");
		
		System.out.print("받은 회사 번호:"+sno+",");
		
		if(interest.equals("plus")) {
			interestService.plusService(mno,sno);
			System.out.println("즐겨찾기 추가 완료");
		}
		if(interest.equals("delete")) {
			interestService.deleteService(mno,sno);
			System.out.println("즐겨찾기 삭제 완료");
		}
		
		if(request.getParameter("form")!=null) {
			return "/search.do";
		}
		return FORM_VIEW;
	
	}
}
