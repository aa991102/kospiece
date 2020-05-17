package rank.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.CommandHandler;
import dto.StockVO;
import rank.service.RankService;

public class RankHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/rank/rank.jsp";
	RankService rankService=new RankService();
	List<StockVO> stockList=null;
	List<String> field=new ArrayList<String>();
	
	@Override
	public String process(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception {
		System.out.print("RankHandler 진입 ");
		
		field=rankService.fieldFind();
		request.setAttribute("fieldName",field);
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("처음 실시간순위 들어왔을 때 화면-전체 주식 데이터가 등락률을 기준으로 내림차순 정렬");
			return processTotalList(request,response);//파라미터가 없으면
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.print("파라미터를 받음-");
			return processSelectedList(request,response);//파라미터가 있으면
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); 
			return   null;
		}
	}
	
	private String processTotalList(HttpServletRequest request, HttpServletResponse response) {
		//get방식 - 폼만 보여주기
		
		HttpSession session = request.getSession();
		int mno=0;
		if(session.getAttribute("MNO")!=null) {
			//세션에 회원정보가 있으면(로그인 상태면) mno에 회원번호 넣어주기
			//비로그인 상태라면 mno는 0이된다
			mno=(int)session.getAttribute("MNO");
		}
		
		stockList=rankService.service(mno,"all","schangerate","desc");
		request.setAttribute("type","schangerate");
		request.setAttribute("sort","desc");
		request.setAttribute("field","all");
		request.setAttribute("stockList",stockList);
		return FORM_VIEW;
		
	}
	private String processSelectedList(HttpServletRequest request, HttpServletResponse response) {
	
		HttpSession session = request.getSession();
		
		int mno=0;
		
		if(session.getAttribute("MNO")!=null) {
			//세션에 회원정보가 있으면(로그인 상태면) mno에 회원번호 넣어주기
			//비로그인 상태라면 mno는 0이된다
			mno=(int)session.getAttribute("MNO");
			System.out.print("회원번호:"+mno+",");
		}
		
		//정렬을 하고싶은 컬럼명과 정렬방식을 파라미터로 받아온다
		String field=request.getParameter("select");
		String type=request.getParameter("column");
		String sort=request.getParameter("orderBy");
		
		//처음 실시간순위 페이지에 들어올때 기본값으로 등락률과 내림차순을 셋팅한다.
				if(type==null) {
					type="schangerate";
				}
				if(sort==null) {
					sort="desc";
				}
				if(field==null) {
					field="all";
				}
			
		System.out.println("선택한 업종:"+field+",정렬할 컬럼:"+type+",정렬방식:"+sort);
		
		stockList=rankService.service(mno,field,type,sort);
		
		//페이지에서 출력할 데이터 request객체에 담아보내기
		request.setAttribute("field",field);
		request.setAttribute("sort",sort);
		request.setAttribute("type",type);
		request.setAttribute("stockList",stockList);
		
		return FORM_VIEW;
		
	}
}
