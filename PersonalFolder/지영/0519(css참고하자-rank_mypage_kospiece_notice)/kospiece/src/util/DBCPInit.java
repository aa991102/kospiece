package util;

import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//메소드1
	@Override
	public void init() throws ServletException {
		loadJDBCDriver();		//JDBC 드라이버 로딩
		initConnectionPool();	//커넥션풀 초기화
	}

	//메소드2
	private void loadJDBCDriver() {
		try {
			//커넥션풀이 내부에서 사용할    JDBC 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}

	//메소드3-커넥션풀 초기화 및 POOL등록
	private void initConnectionPool() {
		try {
			String jdbcUrl = "jdbc:mysql://192.168.56.66:3306/project?" +
					"useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
			String username = "najin";
			String pw = "1234";
   
			//커넥션풀이 	새로운 커넥션을 생성할 때 사용할	 ConnectionFactory를 생성
			ConnectionFactory connFactory = 
					new DriverManagerConnectionFactory(jdbcUrl, username, pw);

			/* PoolableConnection을 생성하는 팩토리 생성.
			 * 	DBCP는 커넥션을 보관할 때 PoolableConnection 을 사용
             * 	실제 커넥션을 담고 있있으며, 커넥션 풀을 관리하는데 필요한 기능을 제공한다.
             * 	커넥션을 close하면 종료하지 않고 커넥션 풀에 반환*/
			PoolableConnectionFactory poolableConnFactory = 
					new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1");

			
			//커넥션풀 정보 설정하기(p423)
			/*커넥션 풀에 있는 커넥션 중 오랜 시간 동안 사용되지 않는 커넥션은 
			 * DBMS와 연결이 끊길 가능성이 높고, 
			 * 연결이   끊기면 프로그램 실행 도중 오류가 발생하므로
			 * ->주기적으로 커넥션 풀에 있는 커넥션을 검사해서 
			 * 	  사전에 제거하는 것이 필요
			 */
			GenericObjectPoolConfig<PoolableConnection> poolConfig = new GenericObjectPoolConfig<PoolableConnection>();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);//풀에 있는 유휴커넥션 검사주기.단위:밀리초
			poolConfig.setTestWhileIdle(true); //true이면 유휴커넥션이 유효한지 검사.기본false
			poolConfig.setMinIdle(4);//풀이 유지할 커넥션의 최소유휴개수.기본값0.
			poolConfig.setMaxTotal(50);//풀이 관리하는 커넥션의 최대개수.기본값8.음수이면 무한

			//커넥션 풀을 생성. 
			//생성자는 PoolabeConnectionFactory와 GenericObjectPoolConfig를 사용
			GenericObjectPool<PoolableConnection> connectionPool = 
					new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			//커넥션 풀 드라이버에 생성한 커넥션 풀을 등록
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = 
					(PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("kospiece", connectionPool); //kospiece 커넥션풀을 등록
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}





