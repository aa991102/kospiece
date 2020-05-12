<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="" id="1">
 	<div class="" id="1-1">
 	자유게시판 
 	</div>
 	<div class="" id="1-2">
 		<table border=1>
 			<tr>
 				<th>글번호</th>
 				<th>제목</th>
 				<th>작성자</th>
 				<th>작성일</th>
 				<th>조회</th>
 			</tr>
 			<c:if test="${freeBoard.hasNoarticles()}">
	 			<tr>
	 				<td colspan="4">게시글이 없습니다.</td>
	 			</tr>		
 			</c:if>
 			<tr>
 				<th><%=request.getAttribute("nickname") %></th>
 				<th>제목</th>
 				<th>작성자</th>
 				<th>작성일</th>
 				<th>조회</th>
 			</tr>
 		</table>
 	</div>
 	
 	<div class="" id="1-3">
 		<< < 1 2 3 4 5 6 7 8 9 > >> 
 		<a href="<%= request.getContextPath()%>/board/write.do">
 		<input type="button" value="글쓰기"/></a>
 	</div>
 </div>
</body>
</html>