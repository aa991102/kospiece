package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import admin.model.MemberPage;
import dao.AdminDAO;
import dao.MemberDAO;
import dto.MemberVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class UserListService {
	
	MemberDAO memberDao=new MemberDAO();
	AdminDAO adminDao=new AdminDAO();
	List<MemberVO> memberlist=null;
	MemberPage memberpage;
	
	int size=10;//한 페이지에 나타낼 행 수
	
	public MemberPage userListService(int page,String column,String value) {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			int total=memberDao.selectedMemberCount(conn,column,value);
			memberlist=memberDao.selectedMember(conn,(page-1)*size,size,column,value);
			
			conn.commit(); //트랙잭션 반영
			
			return new MemberPage(total,page,size,memberlist);
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}

	public MemberPage userListService(int page) {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			int total=adminDao.selectTotalMember(conn);
			memberlist=memberDao.selectAllMember(conn,(page-1)*size,size);
			
			conn.commit(); //트랙잭션 반영
			
			return new MemberPage(total,page,size,memberlist);
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
}
