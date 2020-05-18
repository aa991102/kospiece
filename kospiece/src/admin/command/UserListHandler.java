package admin.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.MemberPage;
import admin.service.UserListService;
import controller.command.CommandHandler;
import dto.MemberVO;

public class UserListHandler implements CommandHandler{
	private static final String FORM_VIEW = "/admin/userManage.jsp";
	
	UserListService userlistService=new UserListService();
	List<MemberVO> memberList=null;
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("UserListHandler 진입-");

		String column=request.getParameter("search");
		String value=request.getParameter("user-inform");
		String page=request.getParameter("page");
		
		MemberPage memberPage;
		int pageNo;
		
		if(column==null) {//검색 안했을 때
			System.out.println("전체회원출력-검색어 없음");
			
			if(page==null) {//처음화면
				pageNo=1;
			}else {//페이지 눌렀을 때
				pageNo=Integer.parseInt(page);
				System.out.println("관리자페이지-회원목록 "+pageNo+"페이지");
			}
			memberPage=userlistService.userListService(pageNo);
			
		}else {//검색 했을 때
			System.out.print("검색된 회원출력:");
			
			if(page==null) {//처음화면
				pageNo=1;
			}else { //페이지 눌렀을 때
				pageNo=Integer.parseInt(page);
				System.out.println("관리자페이지-회원목록 "+pageNo+"페이지");
			}
			memberPage=userlistService.userListService(pageNo,column,value);
			System.out.println(column+"에 "+value+"가 포함되는 회원 출력");
		}
		
		request.setAttribute("memberPage",memberPage);
		//페이지에서 출력할 공지사항 객체 arrayList를 request속성에 담아보내기
		//<1번회원객체,2번회원객체.....>
		
		return FORM_VIEW;
		
	}
	
}
