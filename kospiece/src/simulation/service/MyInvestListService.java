package simulation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MemberDAO;
import dao.SimulationDAO;
import dto.MemberVO;
import dto.MyStockVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MyInvestListService {
	
	Connection conn = null;
	
	public MemberVO getMemberVOById(String mid) {
		
		MemberDAO memberDAO = new MemberDAO();
		
		try {
			return memberDAO.selectById(conn=ConnectionProvider.getConnection(), mid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}

	public ArrayList<MyStockVO> getMyList(int mno){
		
		SimulationDAO service = new SimulationDAO();
		try {
			return service.getMySimulationList(conn=ConnectionProvider.getConnection(), mno);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
