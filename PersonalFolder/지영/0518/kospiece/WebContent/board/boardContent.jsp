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
			<a href="<%= request.getContextPath()%>/board.do?pageNo=${param.pageNo}">
				<input type="button" value="목록" class="목록">
			</a>
			<a href="<%= request.getContextPath()%>/board/write.do">
				<input type="button" value="글쓰기" class="글쓰기">
			</a>
			<c:if test="${NICKNAME == board.nickname}">
				<a href="<%= request.getContextPath()%>/board/modify.do?fno=${param.fno}">
					<input type="button" value="수정" class="수정">
				</a>
				<a href="<%= request.getContextPath()%>/board/delete.do?fno=${param.fno}">
					<input type="button" value="삭제" class="삭제">
				</a>
			</c:if>
			
		</div>
		<div class="" id="1-3">
			<table style="width=100%" border=1>
				<tr>
					<td>제목</td>
					<td>${board.title }</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${board.nickname }</td>
				</tr>
				<tr>
					<td>상세내용</td>
					<td>${board.content }</td>
				</tr>
			</table>
		</div>
	</div>
		
	<div class="" id="2">
		<div class="" id="2-1">
			댓글
		</div>
		<div class="" id="2-2">
			<form action="<%=request.getContextPath() %>/comment/write.do?pageNo=${param.pageNo}&fno=${param.fno}" method="post">
				<input type="text" name="content"/> <input type="submit" value="등록"/>
			</form>
		</div>
		<div class="" id="2-3">
			<table style="width=100%" border=1>
				<tr>
					<th>작성자</th>
					<th>댓글 내용</th>
					<th>작성일</th>
					<th>엄지업</th>
					<th>엄지다운</th>
					<th>삭제버튼이 들어갈 컬럼</th>
				</tr>
<%-- 	 			<c:forEach var="board" items="${listboard.content}">
		 			<tr>
		 				<td>${board.fno} </td>
		 				<td>
		 					<a href="<%= request.getContextPath()%>/board/read.do?pageNo=${listboard.currentPage}&fno=${board.fno}">
		 						<c:out value="[${board.horsehead}]${board.title}"/></a>
		 				</td>
		 				<td>${board.nickname}</td>
		 				<td>${board.uploaddate}</td>
		 				<td>${board.hit}</td>
		 			</tr> 
	 			</c:forEach>
	 			--%>
			</table>
		</div>
	</div>
</body>
</html>