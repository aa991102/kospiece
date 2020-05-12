package article.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import article.MODEL.Article;
import article.MODEL.Writer;
import jdbc.JdbcUtil;

//p634
//article 테이블과 연동하는 DAO

public class ArticleDAO {

	public Article insert(Connection conn, Article article) throws SQLException{
		
		System.out.println("ArticleDAO의 insert() article="+article);
		PreparedStatement pstmt = null;
		
		//article 테이블에서 입력된 마지막 article_id값을 가져오는 쿼리 실행을 위해 필요한 stmt,
		// 그 실행 결과를 담기위한 rs변수 선언
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "insert into article"
							+ "(writer_id,writer_name,title,regdate,moddate,read_cnt) " 
							+ "values(?,?,?,?,?,0)";
			pstmt = conn.prepareStatement(sql);
			
			Writer writer = article.getWriter();
			String id	  = writer.getId();
			
			pstmt.setString(1,id);
			pstmt.setString(2,writer.getName());
			pstmt.setString(3,article.getTitle());
			pstmt.setTimestamp(4,toTimeStamp(article.getRegDate()));
			pstmt.setTimestamp(5,toTimeStamp(article.getModifiedDate()));
			
			int insertedCount = pstmt.executeUpdate();
			
			//p635 31
			if(insertedCount > 0) { //insert 성공시
				//insert시 들어갔던 article_no컬럼 값을 가져오자
				//그래야지 article_content컬럼에 insert할 때 사용할 수 있다
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from article");
				//last_insert_id() : MySQL에서 auto_increment 가 걸려있는 타입의 필드에 저장된 값을 구할 때 사용
				
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					
					return new Article(newNum,
									   article.getWriter(),
									   article.getTitle(),
									   article.getRegDate(),
									   article.getModifiedDate(),
									   0);
				}
			}
			return null;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	//Date타음을 Timestamp타입으로 변환
	private Timestamp toTimeStamp(Date date) {
		return new Timestamp(date.getTime()); //getTime은 date를 long타입으로 준다
	}

}
