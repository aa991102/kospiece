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
	
	public List<StockVO> service(String field,String column,String orderBy) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//트랜잭션 시작
			
			stocklist=stockDao.selectAllStock(conn,field,column,orderBy);
			
			conn.commit(); //트랙잭션 반영
			
			return stocklist;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn); //트랙잭션 취소
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
}
