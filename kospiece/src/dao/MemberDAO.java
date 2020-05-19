package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dto.MemberVO;
import dto.UserVO;
import jdbc.JdbcUtil;

public class MemberDAO {
	
	PreparedStatement pstmt = null;
	ResultSet rs  = null;
	MemberVO member = null;
	public List<MemberVO> memberlist;
	
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
			Date lastTime 	= rs.getDate("mlastlogin");
			updateLoginTime(conn, mno);
			supplyPoint(conn, lastTime, mno);
			member = new MemberVO(mno, memberid,mnick,password,mname,mmail,mphone,deposit,asset);
			
		}
		return member;
	}
	
	//UserVO Session에 넣을 정보 가져오기
	public UserVO selectUser(Connection conn, String id) {

		String sql = "SELECT * FROM member WHERE mid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return new UserVO(rs.getInt("mno"),
						rs.getString("mid"),
						rs.getString("mnick"));
			}
			
			return null;			
		} catch (SQLException e) {
			System.out.println("MemberDAO selectUser error");
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	//로그인한 회원의 정보를 가져오기 위해
	public MemberVO selectById2(Connection conn, String id) 
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
		
		
	//로그인 시간 업데이트
	public void updateLoginTime(Connection conn, int mno) {
		PreparedStatement pstmt = null;
		
		String sql = "update member set mlastlogin=now() where mno=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			System.out.println("MemberDAO updateLoginTime error");
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}
	
	//로그인 시간 비교 후 포인트 증가 함수
	private void supplyPoint(Connection conn, Date lastTime, int mno) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date curTime = null;
		String sql = "select * from member where mno=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				curTime = rs.getDate("mlastlogin");
				if(lastTime.compareTo(curTime)<0) {
					sql = "update member set mdeposit=mdeposit+10 WHERE mno = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, mno);
					pstmt.executeUpdate();
				}				
			}
			
		} catch (SQLException e) {
			System.out.println("error 발생");
			e.printStackTrace();
			System.out.println();
		}
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
	
	/*UserListService(회원관리 페이지)*/
	
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
	public List<MemberVO> selectAllMember(Connection conn,int startRow,int size) throws SQLException {
	
		String sql = "select mnick,mid,mname,mmail,mdate,mdeposit from member"
				+ " limit ?,?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, size);
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
	public List<MemberVO> selectedMember(Connection conn,int startRow,int size, String column, String value) throws SQLException {
		String sql = "select mnick,mid,mname,mmail,mdate,mdeposit from member where "+column+" like ?"
				+ " limit ?,?";
		
		value="%"+value+"%";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value);
		pstmt.setInt(2, startRow);
		pstmt.setInt(3, size);
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
	
	//지정한 조건의 회원수 검색
		public int selectedMemberCount(Connection conn,String column, String value) throws SQLException {
			String sql = "select count(*) from member where "+column+" like ?";
			
			value="%"+value+"%";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			
			rs=pstmt.executeQuery();
			
			rs.next();
			return rs.getInt(1);
		}
	
	//관리자 비밀번호 맞는지 확인
	public Boolean checkPw(Connection conn,String id, String pw) throws SQLException {
		
		String sql = "select mpw from member where mid=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			System.out.print("회원님의 실제 비밀번호는 "+rs.getString("mpw")+"입니다");
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
	public void deleteMember(Connection conn,String nick) throws SQLException {
		String sql = "delete from member where mnick=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nick);
		pstmt.executeUpdate();
		
	}

	//관리자가 회원 포인트 충전
	public void pointCharge(Connection conn, String nick, int point) {
			String sql = "UPDATE member SET mdeposit=mdeposit+? where mnick=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, nick);
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
	
	//특정칼럼을 조건으로 검색하기
	public MemberVO findMemberByColumn(Connection conn, String column, String value) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where "+column+"=?";
		MemberVO member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = memberListResultSet(rs);
			}
			return member;
			
		} catch (SQLException e) {
			System.out.println("MemberDAO findMemberByColumn errors");
			e.printStackTrace();
			return null;
		}
		
	}
	
	//자산포인트 보유 순위
	public List<String> pointRank(Connection conn) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT masset,mdeposit,mnick FROM member" + 
				" ORDER BY masset DESC,mdate asc"+
				" limit 10";
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt);
		rs = pstmt.executeQuery();
		List<String> rank = new ArrayList<String>();
		
		if(rs.next()) {
			do{
				rank.add(rs.getString("mnick"));
			}while(rs.next());
		}
		return rank;
	}
	
}






