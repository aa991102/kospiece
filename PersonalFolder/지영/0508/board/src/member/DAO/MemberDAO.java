package member.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import member.MODEL.Member;

//p592
//Mysql의  board데이터베이스의 member테이블에 쿼리
//LoginService, JoinService에서 호출되는 함수를 선언
public class MemberDAO {

	//유저가 입력한 id를 가진 회원이 존재하는 확인
	//회원으로 존재하면 회원정보가 Member객체로 리턴
	//회원으로 존재x  회원정보가 Member객체가 null리턴
	/*SELECT  memberid, name, password, regdate
		FROM 	member
		WHERE	memberid=?;*/
	//p593
	public Member selectById(Connection conn, String id) 
			throws SQLException {
		System.out.println("MemberDAO-selectById(id)호출="+id);
		
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		Member member = null;
		
		String sql = "SELECT  memberid, name, password, regdate " + 
					 "	FROM 	member " + 
					 "	WHERE	memberid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {  //유저가 입력한 id인 회원이 존재하는 경우
				//교재p592 25~ p593 29
				/*member = new Member(rs.getString("memberid"),
						rs.getString("name"),
						rs.getString("password"),
						toDate(rs.getTimestamp("regdate"));*/
				
				String memberid = rs.getString("memberid");
				String name     = rs.getString("name");
				String password = rs.getString("password");
				Date regDate = toDate(rs.getTimestamp("regdate"));
				
				member = new Member(memberid,name,password,regDate);	
			}
			return member;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}//end of selectById()
		
		
	//p593 38
	//Timestamp타입의 데이터를   Date타입으로 변경
	private Date toDate(Timestamp date) {
		return  date==null? null: new Date(date.getTime());
	}


	//회원가입 p593-42
	/*INSERT INTO member(memberid,name,password,regdate)
		VALUES(memberid?,name?,password?,regdate?)*/
	public void  insert(Connection conn,Member mem)
		throws SQLException {
		System.out.println("MemberDAO-insert()호출");
		String sql = "INSERT INTO member(memberid,name,password,regdate) " + 
				     " VALUES(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,mem.getId());
		pstmt.setString(2,mem.getName());
		pstmt.setString(3,mem.getPassword());
		pstmt.setTimestamp(4, new Timestamp(mem.getRegDate().getTime() ) );
		pstmt.executeUpdate();
	}

	
	//p620 17
	public void update(Connection conn, Member member) throws SQLException{
		System.out.println("MemberDAO-update()호출");
		String sql = "UPDATE  member " + 
				"			SET		name=?, password = ? " + 
				"			WHERE   memberid = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,member.getName());
		pstmt.setString(2,member.getPassword());
		pstmt.setString(3,member.getId());
		pstmt.executeUpdate();
	}

	
}







