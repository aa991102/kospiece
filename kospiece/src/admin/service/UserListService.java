package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.AdminDAO;
import dto.MemberVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class UserListService {
	
	AdminDAO adminDao=new AdminDAO();
	List<MemberVO> memberlist=null;
	
	public List<MemberVO> service() {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작

			memberlist=adminDao.selectAllMember(conn);
			
			conn.commit(); //트랙잭션 반영
			
			return memberlist;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
}
