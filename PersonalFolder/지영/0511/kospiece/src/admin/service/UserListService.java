package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.MemberDAO;
import dto.MemberVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class UserListService {
	
	MemberDAO adminDao=new MemberDAO();
	List<MemberVO> memberlist=null;
	
	public List<MemberVO> userListService(String column,String value) {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			System.out.print(column+value);
			if(column==""||value==""||column==null||value==null) {
				memberlist=adminDao.selectAllMember(conn);
			}else {
				memberlist=adminDao.selectedMember(conn,column,value);
			}
			
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
