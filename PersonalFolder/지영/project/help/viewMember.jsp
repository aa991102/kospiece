<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.ResultSet" %> <%-- select할 때 필요 --%>
<%@ page import = "java.sql.SQLException" %>

<%
	//~~~viewMember.jsp?memberID=hongid
	String memberID = request.getParameter("memberID");
%>
<html>
<head>
<meta charset="utf-8" />
<title>회원 목록</title>
</head>
<body>

MEMBER 테이블의 내용
<a href="<%= request.getContextPath() %>/index.html">HOME</a>
<hr>
<table width="800" border="1">
	<tr>
		<td>이름</td>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>주소</td>
		<td>이메일</td>
	</tr>
	<%
		String name = "";	
		String password = "";	
		String email = "";	
		// 1. JDBC 드라이버 로딩
		//Class.forName("com.mysql.jdbc.Driver");
	  Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String jdbcDriver = "jdbc:mysql://localhost:3307/chap14?" +
					"useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	 							
			String dbUser = "scott";
			String dbPass = "tiger";
			
			String query = "select * from MEMBER WHERE MEMBERID='"+memberID+"' order by MEMBERID";
			
			// 2. 데이터베이스 커넥션 생성
			conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			
			// 3. Statement 생성
			stmt = conn.createStatement();
			
			// 4. 쿼리 실행
			rs = stmt.executeQuery(query);
			
			// 5. 쿼리 실행 결과 출력
			if(rs.next()) {
				name = rs.getString("NAME");
				password = rs.getString("PASSWORD");
				email = rs.getString("EMAIL");
	%>
	<tr>
		<td><%= name %></td>
		<td><%= rs.getString("MEMBERID") %></td>
		<td><%= password %></td>
		<td><%= email %></td>
	</tr>
	<%
			}else{
				response.sendRedirect("../main.jsp");	
			}
		} catch(SQLException ex) {
			out.println(ex.getMessage());//브라우저출력
			ex.printStackTrace();
		} finally {
			// 6. 사용한 Statement 종료
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (stmt != null) try { stmt.close(); } catch(SQLException ex) {}
			
			// 7. 커넥션 종료
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	%>
		<tr>
			<td colspan="4">
				<a href='viewMemberList.jsp'>회원목록조회</a> 
				<a href='insert/insertForm.jsp'>회원정보입력</a> 
				<a href='updateForm.jsp?memberID=<%= memberID %>'>회원정보수정</a></td>
		</tr>
</table>
	<%
		session.setAttribute("name1",name);
		session.setAttribute("memberID1",memberID);
		session.setAttribute("password1",password);
		session.setAttribute("email1",email);
	%>
</body>
</html>
