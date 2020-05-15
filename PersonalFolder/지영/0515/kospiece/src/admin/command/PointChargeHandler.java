package admin.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.PointChargeService;
import controller.command.CommandHandler;

public class PointChargeHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/admin/pointCharge.jsp";
	
	PointChargeService pointChargeService=new PointChargeService();
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("PointChargeHandler 진입 ");
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.print("get방식");
			return processForm(request,response);//파라미터가 없으면
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.print("post");
			return processCharge(request,response);//파라미터가 있으면
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		//파라미터 없을때 폼을 보여준다.
		
		return FORM_VIEW;
		
	}
	private String processCharge(HttpServletRequest request, HttpServletResponse response) {
		//파라미터 있을때 실행하는 로직. 선택된 조건의 회원리스트만 출력한다.
		
		int point=Integer.parseInt(request.getParameter("point"));
		String id=request.getParameter("id");
		System.out.println(point+id);
		pointChargeService.pointCharge(point);
		
		return FORM_VIEW;
		
	}
}
