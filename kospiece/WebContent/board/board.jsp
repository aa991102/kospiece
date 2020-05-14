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
 		<table border="1" width="90%">
 			<tr>
 				<th width="50">글번호</th>
 				<th width="300">제목</th>
 				<th width="50">작성자</th>
 				<th width="50">작성일</th>
 				<th width="50">조회</th>
 			</tr>
 			<c:if test="${listboard.hasNoArticles()}">
	 			<tr>
	 				<th colspan="4">게시글이 없습니다.</th>
	 			</tr>		
 			</c:if>
 			<c:forEach var="board" items="${listboard.content}">
	 			<tr>
	 				<td>${board.fno} </td>
	 				<td>
	 					<a href="<%= request.getContextPath()%>/board/read.do?pageNo=${listboard.currentPage}&no=${board.fno}">
	 						<c:out value="[${board.horsehead}]${board.title}"/></a>
	 				</td>
	 				<td>${board.nickname}</td>
	 				<td>${board.uploaddate}</td>
	 				<td>${board.hit}</td>
	 			</tr>
 			</c:forEach>
 			<c:if test="${listboard.hasArticles()}">
	 			<tr>
	 				<td colspan="4">
	 					<c:if test="${listboard.startPage > 5}">
	 						<a href="board.do?pageNo=${listboard.startPage - listboard.page }">[이전]</a>
	 					</c:if>
	 					<c:forEach var="pNo" begin="${listboard.startPage }" end="${listboard.endPage }">
	 						<a href="board.do?pageNo=${pNo }">[ ${pNo } ]</a>
	 					</c:forEach>
	 					<c:if test="${listboard.endPage < listboard.totalPages}">
	 						<a href="board.do?pageNo=${listboard.startPage + listboard.page }">[다음]</a>
	 					</c:if>
 					</td>
	 			</tr>
 			</c:if>
 		</table>
 	</div>
 	
 	<div class="" id="1-3">
 		<a href="<%= request.getContextPath()%>/board/write.do">
 		<input type="button" value="글쓰기"/></a>
 	</div>
 </div>
</body>
</html>