<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
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
	<% //request.setCharacterEncoding("utf-8"); ---> 이미 서버딴에서 처리했음
	
		 //1. user가 입력한 값을 받아 
			String memberID = (String)session.getAttribute("memberID1");
			String password = "";
			String name = "";
			String email = "";
			String temp = "";
			
			// null은 어떠한 값으로도 초기화 되지 않았다. "" 는 빈 값으로 초기화 되었다.
			// 여기서 조건에 == 와 .equals() 둘 다 해도 실행됨 ---> 
			if(request.getParameter("password")== ""){
				password = (String)session.getAttribute("password1");
			}else{
				password = request.getParameter("password");
			}
			if(request.getParameter("name")== ""){
				name = (String)session.getAttribute("name1");
			}else{
				name = request.getParameter("name");
			}
			if(request.getParameter("email")== ""){
				email = (String)session.getAttribute("email1");
			}else{
				email = request.getParameter("email");
			}
			
		 //2. db와 연동(member테이블에 insert쿼리실행)
		 	//2-1) JDBC 드라이버 로딩
			//Class.forName("com.mysql.jdbc.Driver");
		  Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			int cnt = 0;
			
			try {
				String jdbcDriver = "jdbc:mysql://localhost:3307/chap14?" +
						"useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		 							
				String dbUser = "scott";
				String dbPass = "tiger";
				
				String query = "UPDATE member SET PASSWORD=?,NAME=?,EMAIL=? WHERE MEMBERID='"+memberID+"'";
				//2-2) 데이터베이스 커넥션 생성
				conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
				
				//2-3) Statement 생성
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, password);
				pstmt.setString(2, name);
				pstmt.setString(3, email);
				
				//2-4) 쿼리 실행
				cnt = pstmt.executeUpdate();
				
				//3. 그 결과를 브라우저에 출력 
				if(cnt>0){ //insert쿼리 실행 성공
					out.print("회원정보가 성공적으로 변경되었습니다<br/>"); //브라우저 출력
	%>
					<a href='viewMember.jsp?memberID=<%= memberID %>'>바뀐정보확인</a>
	<%
				}else{ //insert쿼리 실행 실패
					out.print(memberID+"의 회원정보 입력되지 않았습니다"); //브라우저 출력
				}
			} catch(SQLException e) {
				System.out.println("insert쿼리실행관련 에러="+e);
			}finally {
				// 6. 사용한 Statement 종료
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				
				// 7. 커넥션 종료
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
		 
	%>

</body>
</html>




