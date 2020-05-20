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
	String sql = null;
	
	NoticeVO notice=new NoticeVO();
	
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
	
	//전체 게시글 수를 구하는 메서드
		public int selectCount(Connection conn) throws SQLException {
			sql = "select count(*) from notice";
			
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}
		
	// 게시글 관리에서 공지사항 전체보기
	public List<NoticeVO> selectAllNotice(Connection conn,int startRow,int size) throws SQLException {
	
		sql = "select nno,ntitle,ncontent,ndate,nhit from notice "+
				"order by nno desc "+
				"limit ?,? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, size);
		rs=pstmt.executeQuery();
		
		List<NoticeVO> noticelist=new ArrayList<NoticeVO>();
		
		if(rs.next()) {
			
			do{
				noticelist.add(noticeListResultSet(rs));
			}while(rs.next());
			
			return noticelist;
		}else {
			return Collections.emptyList();
		}
	}
	
	//전체 게시글 수를 구하는 메서드
	public int selectedCount(Connection conn,String column, String value) throws SQLException {
			sql = "select count(*) from notice where "+column+" like ? ";
			
			value="%"+value+"%"; //해당 조건을 포함하는 조건문으로 설정
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}

	//조건이 선택된 공지사항 전체보기
	public List<NoticeVO> selectedNotice
		(Connection conn, int startRow, int size, String column, String value) 
			throws SQLException {
		
		List<NoticeVO> noticelist=new ArrayList<NoticeVO>();
		
		sql = "select nno,ntitle,ncontent,ndate,nhit from notice where "+column+" like ? "+
				"order by nno desc "+
				"limit ?,? ";
		
		value="%"+value+"%"; //해당 조건을 포함하는 조건문으로 설정
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value);
		pstmt.setInt(2, startRow);
		pstmt.setInt(3, size);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			
			do{
				noticelist.add(noticeListResultSet(rs));
			}while(rs.next());
			
			return noticelist;
		}else {
			return Collections.emptyList();
		}
	}
	
	//공지사항 상세보기를 하면 조회수를 1회 증가시켜주기
	public void increaseNoticeHit(Connection conn, int no) throws SQLException {
		
		sql = "update notice set nhit=nhit+1 where nno=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();		
	}
	
	//선택된 공지사항 세부내용 보기
	public NoticeVO selectNoticeDetail(Connection conn, int no) throws SQLException {
		
		sql = "select * from notice where nno=?"; //
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			
			notice=noticeListResultSet(rs);
			
			return notice;
		}else {
			return null;
		}
	}
	
	//공지사항 작성
	public void insertNotice(Connection conn,String title, String content) throws SQLException {
		
		sql = "insert into notice(ntitle,ncontent) value(?,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.executeUpdate();
	}
	
	//공지사항 수정
	public void updateNotice(Connection conn, int no, String title,String content) throws SQLException {
		
		sql = "update notice set ntitle=?, ncontent=? where nno=?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setInt(3, no);
		pstmt.executeUpdate();		
	}

	public void deleteNotice(Connection conn, int no) throws SQLException {
		sql = "delete from notice where nno=?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
	}
	
}
