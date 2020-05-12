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

import dto.MemberVO;

public class MemberDAO {
	
	PreparedStatement pstmt = null;
	ResultSet rs  = null;
	MemberVO member = null;
	
	//회원가입 로직
	public void  insert(Connection conn,MemberVO mem)
		throws SQLException {
		System.out.println("MemberDAO-insert()호출");
		String sql = "INSERT INTO member(mid,mnick,mpw,mname,mmail,mphone) " + 
				     " VALUES(?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,mem.getId());
		pstmt.setString(2,mem.getNickname());
		pstmt.setString(3,mem.getPw());
		pstmt.setString(4,mem.getName());
		pstmt.setString(5,mem.getMail());
		pstmt.setString(6,mem.getPhone());
		pstmt.executeUpdate();
	}
	
	//로그인 로직
	public MemberVO selectById(Connection conn, String id) 
			throws SQLException {
		
		System.out.println("MemberDAO-selectById(id)호출="+id);
		
		String sql = "SELECT  mid,mnick,mpw " + 
					 "	FROM 	member " + 
					 "	WHERE	mid=?";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery();
		
		if( rs.next() ) { 
			
			String memberid = rs.getString("mid");
			String name     = rs.getString("mnick");
			String password = rs.getString("mpw");
			
			member = new MemberVO(memberid,name,password);	
		}
		return member;
	}
	
	/*1.AdminService(관리자페이지 홈화면)*/
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
			
			System.out.println(now);
			pstmt.setDate(1, now);
			rs=pstmt.executeQuery();
			System.out.println(rs);
			rs.next();
			System.out.println(rs.getInt(1));
			return rs.getInt(1);
	}
	
	/*2.UserListService(회원관리 페이지)*/
	
	//회원목록 셋팅해주는 메서드
	private MemberVO memberListResultSet(ResultSet rs) throws SQLException{
		MemberVO membervo=new MemberVO();
		membervo.setNickname(rs.getString("mnick"));
		membervo.setId(rs.getString("mid"));
		membervo.setName(rs.getString("mname"));
		membervo.setMail(rs.getString("mmail"));
		membervo.setRegdate(rs.getDate("mdate"));
		membervo.setDeposit(rs.getInt("mdeposit"));
		return membervo;
	}
	
	//관리자페이지에서 회원 전체보기
	public List<MemberVO> selectAllMember(Connection conn) throws SQLException {
	
		String sql = "select mnick,mid,mname,mmail,mdate,mdeposit from member";
		
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			
			List<MemberVO> memberlist=new ArrayList<MemberVO>();
			
			do{
				memberlist.add(memberListResultSet(rs));
			}while(rs.next());
			return memberlist;
		}else {
			return Collections.emptyList();
		}
	}

	//지정한 조건의 회원만 보기
	
	//관리자가 회원 강제탈퇴시키기
	public void deleteMember(Connection conn,String id) {
		String sql = "delete from member where mid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}






