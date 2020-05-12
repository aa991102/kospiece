package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	//외부에서는   
	//Connection 변수명= ConnectionProvider.getConnection();
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:apache:commons:dbcp:board");
	}
}






