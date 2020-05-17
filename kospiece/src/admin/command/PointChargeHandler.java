package admin.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.PointChargeService;
import controller.command.CommandHandler;

public class PointChargeHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/userList.do";
	
	PointChargeService pointChargeService=new PointChargeService();
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		
		System.out.print("PointChargeHandler 진입 ");
		
		String charge=request.getParameter("point");
		charge=charge.replaceAll(" ",""); //잘못입력한 공백을 제거
		
		int point=Integer.parseInt(charge);
		String nick=request.getParameter("nick");
		
		System.out.println(nick+"님에게 "+point+"포인트를 충전");
		
		pointChargeService.pointCharge(nick,point);
		
		return FORM_VIEW;
		
	}
}
