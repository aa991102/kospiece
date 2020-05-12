<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
 			<tr>
 				<td>1</td>
 				<td>테스트</td>
 				<td>유태우</td>
 				<td>20-05-03</td>
 				<td>cnt</td>
 			</tr>
 		</table>
 	</div>
 	
 	<div class="" id="1-3">
 		<< < 1 2 3 4 5 6 7 8 9 > >> 
 		<a href="<%= request.getContextPath()%>/board/boardWrite.jsp">
 		<input type="button" value="글쓰기"/></a>
 	</div>
 </div>
</body>
</html>