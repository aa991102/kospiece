package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import article.DAO.ArticleContentDAO;
import article.DAO.ArticleDAO;
import article.MODEL.Article;
import article.MODEL.ArticleContent;
import article.command.WriteRequest;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//p638
//글등록과 관련한 DAO와 연동

public class WriteArticleService {
	
	//article테이블과 연동하는 DAO
	private ArticleDAO articleDao = new ArticleDAO();
	//article_content테이블과 연동하는 DAO
	private ArticleContentDAO contentDao = new ArticleContentDAO();

	//p639 19
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); //트랜잭션시작
			
			//dao호출 p639 25
			Article article = toArticle(req);
			/* insert into article(article_no,writer_id,writer_name,title,regdate,moddate,read_cnt)
			   values(article_no,writer_id,writer_name,title,regdate,moddate,read_cnt);*/
			Article savedArticle = articleDao.insert(conn,article);
			
			if(savedArticle==null) {//insert 실패하면 null 리턴
				throw new RuntimeException("fail to insert article");
			}
			
			//p639 33
			//article_content에 insert
			/* insert into article_content(article_no,content)
				values(article_no,content); */
			ArticleContent content = new ArticleContent(
														savedArticle.getNumber(),
														req.getContent());
			
			ArticleContent savedContent = contentDao.insert(conn,content);
			
			if(savedContent == null) {
				throw new RuntimeException("fail to insert article_content");
			}
			conn.commit(); //트랜잭션반영
			
			return savedArticle.getNumber();
			
		}catch(SQLException e) {
			
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
			
		}catch(RuntimeException e) {
			
			JdbcUtil.rollback(conn);
			throw e;
		}
		
	}
	
	
	//p639 52
	//WriteRequest를 통해 받은 writer_id,writer_name,title,content +
	//나머지
	//regdate,moddate,read_cnt 데이터를
	//채우는 함수
	private Article toArticle(WriteRequest req) {
		Date now = new Date();
		return new Article(null,req.getWriter(),req.getTitle(),now,now,0);
		
	}//end of write

}
