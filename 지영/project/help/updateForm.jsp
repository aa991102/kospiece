<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.ResultSet" %> <%-- select할 때 필요 --%>
<%@ page import = "java.sql.SQLException" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%= request.getContextPath() %>/index.html">HOME</a>
	<hr>
	<table width="700" border="1" >
		<tr>
			<th colspan="4">현재 회원 정보</th>
		</tr>
		<tr>
			<td>이름</td>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이메일</td>
		</tr>
		<%
			String memberID = request.getParameter("memberID");
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
					%>
					<tr>
						<td colspan="4">요청하신 <%=memberID %>가 존재하지 않습니다.</td>
					</tr>
		<%			
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
	</table>
	<br/>
	<br/>
	<form action="update.jsp" method="post">
		<table width="700" border="1">
			<tr>
				<th colspan="4">변경할 내용</th>
			</tr>
			<tr>
		    <td>아이디</td>
		    <td><%=memberID%></td>
		    <% session.setAttribute("memberID1", memberID); %>
		    <td>암호</td>
		    <td><input type="text" name="password" size="20"></td>
		    <% session.setAttribute("password1", password); %>
			</tr>
			<tr>
		    <td>이름</td>
		    <td><input type="text" name="name" size="20"></td>
		    <% session.setAttribute("name1", name); %>
		    <td>이메일</td>
		    <td><input type="text" name="email" size="20"></td>
		    <% session.setAttribute("email1", email); %>
			</tr>
			<tr>
		    <th colspan="4">
		    	<input type="submit" value="수정"/> 
		    	<input type="button" value="취소"/>
		    </th>
			</tr>
		</table>
	</form>
</body>
</html>