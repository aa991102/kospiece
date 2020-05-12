package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.MyInterestDAO;
import dao.StockDAO;
import dto.MemberVO;
import dto.StockVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MyInterestListService {
	
	MyInterestDAO myInterestDao = new MyInterestDAO();
	StockDAO stockDao = new StockDAO();
	List<String> snoList = null;
	List<StockVO> myInterestList = null;
	
	public List<StockVO> myInterestListService(int mno) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			System.out.println("myInterestListService-" + mno);
			System.out.println("myInterestListService-conn="+conn);
			snoList = myInterestDao.selectSno(conn, mno);
			myInterestList = stockDao.selectStocks(conn, snoList);
			System.out.println("myInterestListService-myInterestList"+myInterestList.toString());
			conn.commit(); //트랙잭션 반영
			
			return myInterestList;
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
			
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
}
