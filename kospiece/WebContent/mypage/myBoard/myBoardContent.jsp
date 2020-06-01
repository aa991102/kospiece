<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="title">내 게시글</div>

<div class="boardContent" id="1">
	<div class="boardContent-buttons" id="1-2">
		<%-- 기본 목록가기 경로 --%>
			<a href="<%= request.getContextPath()%>/myBoardList.do">
				<input type="button" value="목록" class="button">
			</a>
		
		<c:if test="${NICKNAME == board.nickname}">
			<a
				href="<%= request.getContextPath()%>/myBoardDelete.do?fno=${param.fno}">
				<input type="button" value="삭제" class="button2" onclick="return deleteCheck2();" style="float:right;">
			</a>
			<a
				href="<%= request.getContextPath()%>/myBoardModify.do?fno=${param.fno}">
				<input type="button" value="수정" class="button" style="float:right;margin:0 15px;">
			</a>
		</c:if>

	</div>
	<div class="" id="1-3">
		<table width="100%">
			<tr>
				<td class="board-title">[${board.horsehead}] ${board.title}</td>
			</tr>
			<tr>
				<td class="board-info">
					<a class="board-info-nick">${board.nickname}</a> | 
					<a class="board-info-others">조회</a> 
					<a style="color:#ff5656;font-size:1.4rem;">${board.hit}</a>
					<a class="board-info-others"> | ${board.uploaddate }</a>
				</td>
			</tr>
			<tr>
				<td class="board-content"><div class="board-content-div">${board.content }</div></td>
			</tr>
		</table>
	</div>

	
	<div class="boardContent-Comment" id="2">
		<div class="boardContent-Comment-input" id="2-3">
			<form
				action="<%=request.getContextPath()%>/myCommentWrite.do?fno=${param.fno}"
				method="post">
				<input type="textarea" class="input" placeholder="댓글을 입력하세요" name="content" /> 
				<input type="submit" class="button" value="등록" />
			</form>
		</div>
		
		<div class="boardContent-Comment-comment" id="2-1" style="padding:10px;font-size:1.5rem;">
		댓글(<a style="color:#ff5656;">${listcomment.total}</a>)
		</div>
		
		<div class="boardContent-Comment-Table" id="2-2">
			<table width="100%" style="border-top:1px solid gray;">
				<c:forEach var="comment" items="${listcomment.content}">
					<tr>
						<td colspan="100%" class="board-comment-info">
							<a class="board-info-nick">${comment.nickname}</a>&nbsp;&nbsp;
							<a class="board-info-others">${comment.uploaddate}</a>
						</td>
					</tr>
					<tr>
						<td width="80%">${comment.content}</td>
						<td style="padding:0;text-align:center;">
							<a href="<%=request.getContextPath() %>/myCommentLike.do?
						fno=${param.fno}&comment=${comment.fcno}">
								<img class="likeandhate" src="<%= request.getContextPath()%>/img/like.png"/> ${comment.like }</a></td>
						<td style="padding:0;text-align:center;"><a
							href="<%=request.getContextPath() %>/myCommentHate.do?
						fno=${param.fno}&comment=${comment.fcno}">
								<img class="likeandhate" src="<%= request.getContextPath()%>/img/hate.png"/> ${comment.hate}</a></td>
						<td style="padding:0;text-align:center;">
							<c:if test="${NICKNAME == comment.nickname}">
								<a
									href="<%=request.getContextPath() %>/myCommentDelete.do?
						fno=${param.fno}&comment=${comment.fcno}" onclick="return deleteCheck2();" style="color:#ff5656;">
									삭제</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>

				<c:if test="${listcomment.hasArticles()}">
					<tr>
						<td colspan="100%" class="center2"><c:if test="${listcomment.startPage > 5}">
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