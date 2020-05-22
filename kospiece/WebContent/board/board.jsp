<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="title">자유게시판</div>
<div class="title-sub" >
	<a href="<%= request.getContextPath()%>/main.do">홈</a>
	&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/board.do">자유게시판</a>
</div>
<div class="board" id="1">

	<div class="" id="1-1"></div>

	<div class="board-search" id="1-2">
		<form action="<%=request.getContextPath()%>/board.do" method="post"
			class="board-searchbar" onsubmit="return checkForm();">
			<div class="insertFavorite">
				<select name="searchMethod" class="selectCss">
					<option value="ftitle">제목</option>
					<option value="fmemnick">글쓴이</option>
					<option value="fcontent">내용</option>
				</select> <input type="text" name="searchValue" id="search-value" /> <input
					type="submit" value="검색" class="button"/>
				</div>
		</form>

		<table class="myT">
			<tr>
				<th class="center" width="10%">글번호</th>
				<th class="center" width="50%">제목</th>
				<th style="font-weight:bold;" width="10%">작성자</th>
				<th class="center" width="20%">작성일</th>
				<th class="center" width="10%">조회</th>
			</tr>
			<c:if test="${listboard.hasNoArticles()}">
				<tr>
					<th colspan="4">게시글이 없습니다.</th>
				</tr>
			</c:if>
			<%--기본 게시글의 경로--%>
			<c:if test="${listboard.hasArticles()&&null eq content}">
				<c:forEach var="board" items="${listboard.content}">
					<tr>
						<td class="center">${board.fno}</td>
						<td><a
							href="<%= request.getContextPath()%>/board/read.do?pageNo=${listboard.currentPage}&fno=${board.fno}">
								<c:out value="[${board.horsehead}]${board.title}" />
						</a></td>
						<td>${board.nickname}</td>
						<td class="center2">${board.uploaddate}</td>
						<td class="center2">${board.hit}</td>
					</tr>
				</c:forEach>
			</c:if>
			<%--검색 시 나오는 게시글의 경로조정 --%>
			<c:if test="${null ne content}">
				<c:forEach var="board" items="${listboard.content}">
					<tr>
						<td class="center">${board.fno}</td>
						<td><a
							href="<%= request.getContextPath()%>/board/read.do?
							pageNo=${listboard.currentPage}&fno=${board.fno}&
							searchMethod=${method}&searchValue=${content}">
								<c:out value="[${board.horsehead}]${board.title}" />
						</a></td>
						<td>${board.nickname}</td>
						<td class="center2">${board.uploaddate}</td>
						<td class="center2">${board.hit}</td>
					</tr>
				</c:forEach>
			</c:if>

			<c:if test="${listboard.hasArticles()&&null eq content}">
				<tr>
					<td colspan="100%" class="center2"><c:if test="${listboard.startPage > 5}">
							<a
								href="board.do?pageNo=${listboard.startPage - listboard.page }">[이전]</a>
						</c:if> <c:forEach var="pNo" begin="${listboard.startPage }"
							end="${listboard.endPage }">
							<a href="board.do?pageNo=${pNo }">[${pNo }]</a>
						</c:forEach> <c:if test="${listboard.endPage < listboard.totalPages}">
							<a
								href="board.do?pageNo=${listboard.startPage + listboard.page }">[다음]</a>
						</c:if></td>
				</tr>
			</c:if>
			<c:if test="${null ne content}">
				<tr>
					<td colspan="100%" class="center2"><c:if test="${listboard.startPage > 5}">
							<a
								href="board.do?pageNo=${listboard.startPage - listboard.page }&searchMethod=${method}&searchValue=${content}">
								[이전]</a>
						</c:if> <c:forEach var="pNo" begin="${listboard.startPage }"
							end="${listboard.endPage }">
							<a href="board.do?pageNo=${pNo }&searchMethod=${method}&searchValue=${content}">[${pNo}]</a>
						</c:forEach> <c:if test="${listboard.endPage < listboard.totalPages}">
							<a
								href="board.do?pageNo=${listboard.startPage + listboard.page }&searchMethod=${method}&searchValue=${content}">[다음]</a>
						</c:if></td>
				</tr>
			</c:if>
		</table>
	</div>
	
	<div class="board-writebutton" id="1-3">
		<a href="<%=request.getContextPath()%>/board/write.do"> 
			<input type="button" value="글쓰기" class="button" style="float:right;"/>
		</a>
	</div>
	
</div>
</body>
</html>