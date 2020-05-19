package rank.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.StockDAO;
import dto.StockVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class RankService {

	StockDAO stockDao=new StockDAO();
	List<StockVO> stocklist=new ArrayList<StockVO>();
	List<String> field=new ArrayList<String>();
	
	//조건에맞는 데이터를 검색하는 메서드
	public List<StockVO> service(int mno,String field,String column,String orderBy) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			stocklist=stockDao.selectAllStock(conn,mno,field,column,orderBy);
			
			conn.commit(); //트랙잭션 반영
			
			return stocklist;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}

	//업종명 찾는 서비스
	public List<String> fieldFind() {
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			field=stockDao.selectField(conn);
			
			conn.commit(); //트랙잭션 반영
			
			return field;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
