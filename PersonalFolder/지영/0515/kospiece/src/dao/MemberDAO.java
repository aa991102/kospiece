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
import jdbc.JdbcUtil;

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
		
		String sql = "SELECT * FROM member WHERE mid = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if( rs.next() ) {
			int mno = rs.getInt("mno");
			String memberid = rs.getString("mid");
			String mnick    = rs.getString("mnick");
			String password = rs.getString("mpw");
			String mname 	= rs.getString("mname");
			String mmail	= rs.getString("mmail");
			String mphone	= rs.getString("mphone");
			int deposit		= rs.getInt("mdeposit");
			int asset		= rs.getInt("masset");
			
			member = new MemberVO(mno, memberid,mnick,password,mname,mmail,mphone,deposit,asset);	
		}
		return member;
	}
	
	//비밀번호 변경 기능
	public void pwUpdate(Connection conn,MemberVO member) throws SQLException{
		String sql = "update member set mpw=? where mid=?";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getId());
			pstmt.executeUpdate();
		}
	}
	
	//닉네임,이메일,전화번호 변경 기능
	public void UpdateInfos(Connection conn,String nick,String mail,String phone,String id) throws SQLException{
		String sql = "update member set mnick=?,mmail=?,mphone=? where mid=?";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, nick);
			pstmt.setString(2, mail);
			pstmt.setString(3, phone);
			pstmt.setString(4, id);
			
			pstmt.executeUpdate();
		}
	}

	//닉네임으로 select(동일한 닉네임있는지 체크하기위해)
	public String selectByNick(Connection conn,String userId,String nick) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		String id = null;
		
		String sql = "SELECT mid FROM member WHERE mnick = ? ANd mid!=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nick);
			pstmt.setString(2, userId);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("mid");
			}
		} catch (SQLException e) {
			System.out.println("selectByNick 에러발생");
			e.printStackTrace();
		}
		return id;
	}
	
	//이메일로 select(동일한 이메일있는지 체크하기위해)
	public String selectByMail(Connection conn,String userId,String mail) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		String id = null;
		
		String sql = "SELECT mid FROM member WHERE mmail = ? ANd mid!=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mail);
			pstmt.setString(2, userId);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("mid");
			}
		} catch (SQLException e) {
			System.out.println("selectByMail 에러발생");
			e.printStackTrace();
		}
		return id;
	}
	
	//전화번호로 select(동일한 전화번호 있는지 체크하기위해)
	public String selectByPhone(Connection conn,String userId,String phone) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		String id = null;
		
		String sql = "SELECT mid FROM member WHERE mphone = ? ANd mid!=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, userId);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("mid");
			}
		} catch (SQLException e) {
			System.out.println("selectByPhone 에러발생");
			e.printStackTrace();
		}
		return id;
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
			
			pstmt.setDate(1, now);
			rs=pstmt.executeQuery();
			rs.next();
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
	public List<MemberVO> selectedMember(Connection conn, String column, String value) throws SQLException {
		String sql = "select mnick,mid,mname,mmail,mdate,mdeposit from member where "+column+" like ?";
		value="%"+value+"%";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value);
		System.out.println(pstmt);
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
	
	//관리자 비밀번호 맞는지 확인
			public Boolean checkPw(Connection conn,String id, String pw) throws SQLException {
				
				String sql = "select mpw from member where mid=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					System.out.print("진짜 번호는 "+rs.getString("mpw"));
					if(rs.getString("mpw").equals(pw)) {
						return true;
					}else {
						return false;
					}
					
				}else {
					return false;
				}
			}
	
	//관리자가 회원 강제탈퇴시키기
	public void deleteMember(Connection conn,String id) throws SQLException {
		String sql = "delete from member where mid=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.executeUpdate();
		
	}

	//관리자가 회원 포인트 충전
	public void pointCharge(Connection conn, int point) {
			String sql = "update";
		
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//회원정보 업데이트
	public void update(Connection conn, MemberVO member2) {
		
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET mdeposit=? where mno=?";
		System.out.println("member2="+member2);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member2.getDeposit());
			pstmt.setInt(2, member2.getMno());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("update 에러발생");
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		}
		
	}
	
	
}






