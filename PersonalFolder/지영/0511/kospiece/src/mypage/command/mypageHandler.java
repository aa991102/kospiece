package mypage.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dao.MemberDAO;
import dto.MemberVO;

public class mypageHandler implements CommandHandler {
	
	/* 마이페이지 핸들러
	 * 메인페이지에서
	 * 1. 내정보가 없이 들어올 경우 login 페이지로 이동
	 * 2. 내정보를 가지고 들어올 경우 내정보를 토대로 관심주식,가상투자,내게시글의 최대 5개의 정보를 가지고 온다.
	 *    해당 정보들을 mypage.jsp에서 보여준다.
	 */
	
	HttpSession session = null;
	
	private static final String FORM_LOGIN ="/member/login.jsp";  // 로그인페이지
	private static final String FORM_MYPAGE = "/mypage/mypage.jsp";  //마이페이지
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//session 정보 가져오기
		session = req.getSession();
		if(session == null) {//user session 정보가 없으면 login.jsp로 강제이동
			return processForm(req, res);
		}else {//user session 정보가 있으면 process 실행
			return processSubmit(req, res);
		}
		
	}//process() end
	
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_LOGIN;
	}//processForm() end


	//로그인 했을때 
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
	
		session = req.getSession();
		int mno = 1;//(Integer)session.getAttribute("a");//회원 아이디 가져오기
		MemberDAO memberDAO = new MemberDAO();
		try {
			Connection conn = ConnectionProvider.getConnection();
			MemberVO member = memberDAO.getMemberVO(conn, mno);
			
			//내가 모의투자 업체 목록을 ArrayList에 담기
			SimulationDAO simulationdao = new SimulationDAO();
			ArrayList<MyStockVO> mysimulationList = simulationdao.getMySimulationList(conn, mno);
			req.setAttribute("member", member);
			req.setAttribute("mysumlationList", mysimulationList);
			return FORM_MYPAGE;
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//에외 발생 시 일단 로그인화면으로... 
			return FORM_LOGIN;
		}
		
	}//processSubmit() end

}
