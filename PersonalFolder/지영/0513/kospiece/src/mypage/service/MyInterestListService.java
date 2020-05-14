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
	
	// 1. 회원번호로 관심 회사번호 select한 값을 List(snoList)에 저장
	// 2. List에 저장된 관심회원번호로 각 회사의 정보 가져와서 List(myInterestList)에 저장
	// 3. 회사들 정보 저장된 리스트 반환
	public List<StockVO> myInterestListService(int mno) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			// 1. 회원번호로 관심 회사번호 select한 값을 List(snoList)에 저장
			System.out.println("myInterestListService-" + mno);
			snoList = myInterestDao.selectSno(conn, mno);
			
			// 2. List에 저장된 관심회원번호로 각 회사의 정보 가져와서 List(myInterestList)에 저장
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
