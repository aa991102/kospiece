<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="title">내 게시글</div>

<div class="boardContent" id="1">
	<div class="boardContent-buttons" id="1-2">
		<%-- 기본 목록가기 경로 --%>
			<a href="<%= request.getContextPath()%>/myBoardList.do">
				<input type="button" value="목록" class="목록">
			</a>
		
		<c:if test="${NICKNAME == board.nickname}">
			<a
				href="<%= request.getContextPath()%>/myBoardModify.do?fno=${param.fno}">
				<input type="button" value="수정" class="수정">
			</a>
			<a
				href="<%= request.getContextPath()%>/myBoardDelete.do?fno=${param.fno}">
				<input type="button" value="삭제" class="삭제" onclick="return deleteCheck1();">
			</a>
		</c:if>

	</div>
	<div class="" id="1-3">
		<table border=1 width="100%">
			<tr height=50>
				<td width="10%" align="center">제목</td>
				<td width="90%">${board.title }</td>
			</tr>
			<tr height=50>
				<td align="center">작성자</td>
				<td>${board.nickname }</td>
			</tr>
			<tr height=300>
				<td align="center">상세내용</td>
				<td>${board.content }</td>
			</tr>
		</table>
	</div>

	<hr>
	<div class="boardContent-Comment" id="2">
		<div class="boardContent-Comment-input" id="2-3">
			<form
				action="<%=request.getContextPath()%>/myCommentWrite.do?fno=${param.fno}"
				method="post">
				<input type="textarea" class="input" placeholder="댓글을 입력하세요" name="content" /> 
				<input type="submit" class="button" value="등록" />
			</form>
		</div>
		<div class="boardContent-Comment-comment" id="2-1">댓글(${listcomment.total})</div>

		<div class="boardContent-Comment-Table" id="2-2">
			<table border=1 width="100%">
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
						<td><a
							href="<%=request.getContextPath() %>/myCommentLike.do?
						fno=${param.fno}&comment=${comment.fcno}">
								업${comment.like }</a></td>
						<td><a
							href="<%=request.getContextPath() %>/myCommentHate.do?
						fno=${param.fno}&comment=${comment.fcno}">
								다운${comment.hate}</a></td>
						<td><c:if test="${NICKNAME == comment.nickname}">
								<a
									href="<%=request.getContextPath() %>/myCommentDelete.do?
						fno=${param.fno}&comment=${comment.fcno}">
									삭제</a>
							</c:if></td>
					</tr>
				</c:forEach>

				<c:if test="${listcomment.hasArticles()}">
					<tr>
						<td colspan="6"><c:if test="${listcomment.startPage > 5}">
								<a
									href="<%=request.getContextPath() %>/myBoardRead.do?pageNo=${param.pageNo }&fno=${param.fno}&commentPageNo=${listcomment.startPage - listcomment.page}">
									[이전]</a>
							</c:if> <c:forEach var="commentPno" begin="${listcomment.startPage }"
								end="${listcomment.endPage }">
								<a
									href="<%=request.getContextPath() %>/myBoardRead.do?pageNo=${param.pageNo }&fno=${param.fno}&commentPageNo=${commentPno}">
									[ ${commentPno } ]</a>
							</c:forEach> <c:if test="${listcomment.endPage < listcomment.totalPages}">
								<a
									href="<%=request.getContextPath() %>/myBoardRead.do?pageNo=${param.pageNo }&fno=${param.fno}&commentPageNo=${listcomment.startPage + listcomment.page}">
									[다음]</a>
							</c:if></td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
</div>