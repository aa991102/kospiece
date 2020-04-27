package util;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class MySQLDriverLoader extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
        try {
//          Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("연결성공");
        } catch(Exception e) {
            System.out.println("jdbc드라이버오류"+e);
        }
    }
}
