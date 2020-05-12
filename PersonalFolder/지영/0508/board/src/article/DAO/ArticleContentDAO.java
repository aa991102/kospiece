package article.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import article.MODEL.ArticleContent;
import jdbc.JdbcUtil;

//p636
//article_content 테이블과 연동하는 DAO
public class ArticleContentDAO {

	public ArticleContent insert(Connection conn, ArticleContent content) throws SQLException{
		System.out.println("ArticleContentDAO의 insert() content="+content);
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into article_content"
							+ " values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,content.getNumber());
			pstmt.setString(2,content.getContent());
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				return content;
			}else {
				return null;
			}
			
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}

}
