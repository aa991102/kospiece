<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>
	<%//request.setAttribute("errors",errors);
	
		//로그인이 성공되면 
	  //session.setAttribute("AUTHUSER", user);
	%>
	<h3>대문(index)</h3>
	<p>http://localhost/board</p>
	<p>http://localhost/board/index.jsp</p>
	<ul>
		<%-- 미로그인시 출력 내용 --%>
		<c:if test="${empty AUTHUSER }">
			<li><a href="./join.do">회원가입</a></li>
			<li><a href="./login.do">로그인</a></li>
			<li><a href="./article/write.do">글등록(미로그인시)</a></li>
			
		</c:if>
		
		<%-- 로그인시 출력 내용 --%>
		<c:if test="${! empty AUTHUSER }">
			<li><a href="./logout.do">로그아웃</a></li>
			<li><a href="./changePwd.do">암호변경</a></li>
			<li><a href="./article/write.do">글등록</a></li>
		</c:if>
		
		<li><a href="./article/list.do">글목록조회</a></li>
	</ul>
</body>
</html>     

