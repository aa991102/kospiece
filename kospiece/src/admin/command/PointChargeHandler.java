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
		
		int point=Integer.parseInt(request.getParameter("point"));
		String id=request.getParameter("id");
		
		System.out.println(id+"님에게 "+point+"포인트를 충전");
		
		pointChargeService.pointCharge(id,point);
		
		return FORM_VIEW;
		
	}
}
