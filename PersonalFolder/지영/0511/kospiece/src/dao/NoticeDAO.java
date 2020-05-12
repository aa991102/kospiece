package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dto.NoticeVO;

public class NoticeDAO {
	
	PreparedStatement pstmt = null;
	ResultSet rs  = null;
	
	//NoticeVO 셋팅해주는 메서드
	private NoticeVO noticeListResultSet(ResultSet rs) throws SQLException{
		NoticeVO noticevo=new NoticeVO();
		noticevo.setNno(rs.getInt("nno"));
		noticevo.setTitle(rs.getString("ntitle"));
		noticevo.setContent(rs.getString("ncontent"));
		noticevo.setUploadDate(rs.getDate("ndate"));
		noticevo.setHit(rs.getInt("nhit"));
		return noticevo;
	}
	
	//관리자페이지의 게시글 관리에서 공지사항 전체보기
	public List<NoticeVO> selectAllNotice(Connection conn) throws SQLException {
	
		String sql = "select nno,ntitle,ncontent,ndate,nhit from notice";
		
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			
			List<NoticeVO> noticelist=new ArrayList<NoticeVO>();
			
			do{
				noticelist.add(noticeListResultSet(rs));
			}while(rs.next());
			return noticelist;
		}else {
			return Collections.emptyList();
		}
	}

	//관리자페이지의 게시글 관리에서 선택된 공지사항 전체보기
	public List<NoticeVO> selectedNotice(Connection conn, String column, String value) throws SQLException {
		String sql = "select nno,ntitle,ncontent,ndate,nhit from notice where "+column+" like ?";
		value="%"+value+"%";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			
			List<NoticeVO> noticelist=new ArrayList<NoticeVO>();
			
			do{
				noticelist.add(noticeListResultSet(rs));
			}while(rs.next());
			return noticelist;
		}else {
			return Collections.emptyList();
		}
	}
	
	//관리자페이지의 공지사항 작성
	public void insertNotice(Connection conn,String title, String content) throws SQLException {
		
		String sql = "insert into notice(ntitle,ncontent) value(?,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.executeUpdate();
		
	}

	
}
