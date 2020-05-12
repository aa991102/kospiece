package model.dao;

import java.sql.*;

import model.dto.BoardVO;
import util.JdbcUtil;

public class BoardDAO {
	
	private static BoardDAO postdao=new BoardDAO();
	
	public static BoardDAO getInstance() {
		return postdao;
	}
	
	public int insert(Connection conn, BoardVO post){
		
		PreparedStatement pstmt=null;
		
			String sql="insert into board(userno,title,contents) values(?,?,?)";
			
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, post.getUserno());
				pstmt.setString(2, post.getTitle());
				pstmt.setString(3, post.getContents());
				return pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("postdao의 SQL insert문 오류"+e);
				return 0;
			} finally {
				JdbcUtil.close(pstmt);
			}
			
		}
	}

