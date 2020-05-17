package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import admin.model.Visitor;

public class AdminDAO {
	
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	/*관리자페이지 홈화면-today&total 통계 데이터 구하기*/
	
	//전체 회원 수 구하는 메서드
	public int selectTotalMember(Connection conn) throws SQLException {
		String sql = "select count(*) from member";
		
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	//신규(오늘 가입한) 회원 수 구하는 메서드
	public int selectTodayMember(Connection conn) throws SQLException {
			String sql = "select count(*) from member where mdate=?";
			
			pstmt = conn.prepareStatement(sql);
			
			Calendar cal = Calendar.getInstance();
			 
			//현재 년도, 월, 일
			int year = cal.get ( Calendar.YEAR );
			int month = cal.get ( Calendar.MONTH );
			int date = cal.get ( Calendar.DATE );
			String today=year+"-"+(month+1)+"-"+(date+1);
			
			Date now=Date.valueOf(today);
			
			pstmt.setDate(1, now);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
	}
		
	//전체 게시글 구하는 메서드
	public int selectTotalPost(Connection conn) throws SQLException {
		String sql = "select count(*) from freeboard";
		
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		rs.next();
		return rs.getInt(1);
	}

	//오늘 업로드된 게시글 구하는 메서드
	public int selectTodayPost(Connection conn) throws SQLException {
		String sql = "select count(*) from freeboard where fdate>=?";
		
		pstmt = conn.prepareStatement(sql);
		
		Calendar cal = Calendar.getInstance();
		 
		//현재 년도, 월, 일
		int year = cal.get ( Calendar.YEAR );
		int month = cal.get ( Calendar.MONTH );
		int date = cal.get ( Calendar.DATE );
		String today=year+"-"+(month+1)+"-"+(date+1);
		
		Date now=Date.valueOf(today);
		
		pstmt.setDate(1, now);
		rs=pstmt.executeQuery();
		rs.next();
		return rs.getInt(1);
	}

	//오늘 방문자 수에 +1하는 메서드(만약 오늘 처음 방문한 방문자라면 새로운 날짜에 해당하는 행을 하나 생성한다)
	public void setVisitTotalCount(Connection conn) throws SQLException {
		String sql = "UPDATE visitor SET vscount = vscount+1 where vsdate=?";
		
		pstmt = conn.prepareStatement(sql);
		
		Calendar cal = Calendar.getInstance();
		 
		//현재 년도, 월, 일
		int year = cal.get ( Calendar.YEAR );
		int month = cal.get ( Calendar.MONTH );
		int date = cal.get ( Calendar.DATE );
		String today=year+"-"+(month+1)+"-"+(date+1);
		
		Date now=Date.valueOf(today);
		
		pstmt.setDate(1, now);
	
		if(pstmt.executeUpdate()!=0) { //오늘 날짜의 방문자 데이터가 있으면
			System.out.println("방문자수 1명 증가 ");
		}else {
			
			PreparedStatement pstmt2;
			sql = "insert INTO visitor (vsdate,vscount) values (?,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setDate(1, now);
			pstmt2.setInt(2, 1);
			System.out.println(now+"일자 첫 방문자");
			
			pstmt2.executeUpdate();
		}
	}

	//오늘 방문자 수 구하는 메서드
	public int getVisitTodayCount(Connection conn) throws SQLException {
		String sql = "select vscount from visitor where vsdate=?";
		
		pstmt = conn.prepareStatement(sql);
		
		Calendar cal = Calendar.getInstance();
		 
		//현재 년도, 월, 일
		int year = cal.get ( Calendar.YEAR );
		int month = cal.get ( Calendar.MONTH );
		int date = cal.get ( Calendar.DATE );
		String today=year+"-"+(month+1)+"-"+(date+1);
		
		Date now=Date.valueOf(today);
		
		pstmt.setDate(1, now);
		rs=pstmt.executeQuery();
		rs.next();
		return rs.getInt(1);
	}

	//전체 방문자 수 구하는 메서드
	public int getVisitTotalCount(Connection conn) throws SQLException {
		String sql = "select sum(vscount) from visitor";
		
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		rs.next();
		return rs.getInt(1);
	}

	/*관리자페이지 홈화면-단위기간별 방문자 수 추이 구하기*/
	
	//한 주간 방문자 수 검색
	public List<Visitor> selectWeekVisitor(Connection conn) throws SQLException {
		
		String sql="select vscount from visitor order by vsdate desc limit 7";
		
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		Calendar today = Calendar.getInstance();
		List<Visitor> visitor=new ArrayList<Visitor>();
		
		if(rs.next()) {
			for(int i=1;i<8;i++) {
				
				int weekNum=today.get(Calendar.DAY_OF_WEEK)-i; //오늘부터 7일전까지의 요일 구하기
				String week="";
				
				if(weekNum==0 || weekNum==-7 ) {
					week="일";
				}else if(weekNum==1 || weekNum==-6) {
					week="월";
				}else if(weekNum==2 || weekNum==-5) {
					week="화";
				}else if(weekNum==3 || weekNum==-4) {
					week="수";
				}else if(weekNum==4 || weekNum==-3) {
					week="목";
				}else if(weekNum==5 || weekNum==-2) {
					week="금";
				}else if(weekNum==6 || weekNum==-1) {
					week="토";
				}
				
				visitor.add(new Visitor(week,rs.getInt("vscount")));
				
				rs.next();
			}
			Collections.reverse(visitor); //리스트 순서를 반대로
			
			return visitor;
		}else {
			return Collections.emptyList();
		}
		
	}

	//한 달간 방문자수 검색
	public List<Visitor> selectMonthVisitor(Connection conn) throws SQLException {

		String sql = "SELECT DATE_FORMAT(DATE_SUB(`vsdate`, INTERVAL (DAYOFWEEK(`vsdate`)-1) DAY), '%Y/%m/%d') as start," + 
				" DATE_FORMAT(DATE_SUB(`vsdate`, INTERVAL (DAYOFWEEK(`vsdate`)-7) DAY), '%Y/%m/%d') as end," + 
				" DATE_FORMAT(`vsdate`, '%Y%U') AS `date`," + 
				" sum(`vscount`) as sum" + 
				" FROM visitor" + 
				" GROUP BY date" + 
				" ORDER BY DATE DESC" + 
				" LIMIT 1,4;";
		
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		List<Visitor> visitor=new ArrayList<Visitor>();
		
		if(rs.next()) {
			for(int i=1;i<5;i++) {
				
				String term=i+"주전";
				
				visitor.add(new Visitor(term,rs.getInt("sum")));
				
				rs.next();
			}
			Collections.reverse(visitor); //리스트 순서를 반대로
			
			return visitor;
		}else {
			return Collections.emptyList();
		}
		
	}

	//1년간 방문자수 검색
	public List<Visitor> selectYearVisitor(Connection conn) throws SQLException {

		String sql = "SELECT sum(`vscount`) as sum" + 
				" FROM visitor" + 
				" GROUP BY  MONTH(`vsdate`)" + 
				" ORDER BY  MONTH(`vsdate`) desc" + 
				" LIMIT 12;";
		
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		Calendar today = Calendar.getInstance();
		
		List<Visitor> visitor=new ArrayList<Visitor>();
		
		if(rs.next()) {
			
			for(int i=0;i<12;i++) {//이번달부터 12달 전까지의 월 구하기
				
				int monthNum=today.get(Calendar.MONTH)+1-i;
				String month="";
				
				if(monthNum==1 || monthNum==-11 ) {
					month="1월";
				}else if(monthNum==2 || monthNum==-10 ) {
					month="2월";
				}else if(monthNum==3 || monthNum==-9 ) {
					month="3월";
				}else if(monthNum==4 || monthNum==-8 ) {
					month="4월";
				}else if(monthNum==5 || monthNum==-7 ) {
					month="5월";
				}else if(monthNum==6 || monthNum==-6 ) {
					month="6월";
				}else if(monthNum==7 || monthNum==-5 ) {
					month="7월";
				}else if(monthNum==8 || monthNum==-4 ) {
					month="8월";
				}else if(monthNum==9 || monthNum==-3 ) {
					month="9월";
				}else if(monthNum==10 || monthNum==-2 ) {
					month="10월";
				}else if(monthNum==11 || monthNum==-1 ) {
					month="11월";
				}else if(monthNum==12 || monthNum==0 ) {
					month="12월";
				}
				
				visitor.add(new Visitor(month,rs.getInt("sum")));
				
				rs.next();
			}
			Collections.reverse(visitor); //리스트 순서를 반대로
			
			return visitor;
		}else {
			return Collections.emptyList();
		}
	}
	
}




















