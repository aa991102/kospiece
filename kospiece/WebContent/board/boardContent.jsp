<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="" id="1">
		<div class="" id="1-1">자유게시판</div>
		<div class="" id="1-2">
		<%-- 기본 목록가기 경로 --%>
		<c:if test="${null eq param.searchMethod}">
		<a
				href="<%= request.getContextPath()%>/board.do?pageNo=${param.pageNo}">
				<input type="button" value="목록" class="목록"></a>
				</c:if>
		<%-- 검색으로 들어왔을 경우 목록가기 경로 수정 --%>
		<c:if test="${null ne param.searchMethod}">
			<a
				href="<%= request.getContextPath()%>/board.do?pageNo=${param.pageNo}
				&searchMethod=${param.searchMethod}&searchValue=${param.searchValue}">
				<input type="button" value="목록" class="목록">
			</a>
		</c:if>
			
			 <a href="<%=request.getContextPath()%>/board/write.do"> <input
				type="button" value="글쓰기" class="글쓰기">
			</a>
			<c:if test="${NICKNAME == board.nickname}">
				<a
					href="<%= request.getContextPath()%>/board/modify.do?fno=${param.fno}">
					<input type="button" value="수정" class="수정">
				</a>
				<a
					href="<%= request.getContextPath()%>/board/delete.do?fno=${param.fno}">
					<input type="button" value="삭제" class="삭제">
				</a>
			</c:if>

		</div>
		<div class="" id="1-3">
			<table style="" border=1>
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
	
		<div class="" id="2-1">댓글</div>
		<div class="" id="2-2">
			<form
				action="<%=request.getContextPath() %>/comment/write.do?pageNo=${param.pageNo}&fno=${param.fno}"
				method="post">
				<input type="text" name="content" /> <input type="submit"
					value="등록" />
			</form>
		</div>
		
		<div class="" id="2-3">
			<table style="" border=1>
				<tr>
					<th>작성자</th>
					<th>댓글 내용</th>
					<th>작성일</th>
					<th>엄지업</th>
					<th>엄지다운</th>
					<th>삭제</th>
				</tr>
				<c:forEach var="comment" items="${listcomment.content}">
					<tr>
						<td>${comment.nickname}</td>
						<td>${comment.content}</td>
						<td>${comment.uploaddate}</td>
						<td><a href="<%=request.getContextPath() %>/comment/like.do?
						pageNo=${param.pageNo}&fno=${param.fno}&comment=${comment.fcno}">
						업${comment.like }</a></td>
						<td><a href="<%=request.getContextPath() %>/comment/hate.do?
						pageNo=${param.pageNo}&fno=${param.fno}&comment=${comment.fcno}">
						다운${comment.hate}</a></td>
						<td><a href="<%=request.getContextPath() %>/comment/delete.do?
						pageNo=${param.pageNo}&fno=${param.fno}&comment=${comment.fcno}">
						삭제버튼</a></td>
					</tr>
				</c:forEach>

				<c:if test="${listcomment.hasArticles()}">
					<tr>
						<td colspan="4"><c:if test="${listcomment.startPage > 5}">
								<a
									href="<%=request.getContextPath() %>/board/read.do?pageNo=${param.pageNo }&fno=${param.fno}&commentPageNo=${listcomment.startPage - listcomment.page}">
									[이전]</a>
							</c:if> <c:forEach var="commentPno" begin="${listcomment.startPage }"
								end="${listcomment.endPage }">
								<a
									href="<%=request.getContextPath() %>/board/read.do?pageNo=${param.pageNo }&fno=${param.fno}&commentPageNo=${commentPno}">
									[ ${commentPno } ]</a>
							</c:forEach> <c:if test="${listcomment.endPage < listcomment.totalPages}">
								<a
									href="<%=request.getContextPath() %>/board/read.do?pageNo=${param.pageNo }&fno=${param.fno}&commentPageNo=${listcomment.startPage + listcomment.page}">
									[다음]</a>
							</c:if></td>
					</tr>
				</c:if>

			</table>
		</div>
	</div>
</body>
</html>