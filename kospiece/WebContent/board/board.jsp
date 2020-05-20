<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function checkForm() {
		if (document.getElementById("search-value").value == "") {
			alert("검색어를 입력해주세요")
			return false;
		}
	}
</script>
<div class="title">자유게시판</div>

<div class="board" id="1">

	<div class="" id="1-1"></div>

	<div class="board-search" id="1-2">
		<form action="<%=request.getContextPath()%>/board.do" method="post"
			class="board-searchbar" onsubmit="return checkForm();">
			<select name="searchMethod">
				<option value="ftitle">제목</option>
				<option value="fmemnick">글쓴이</option>
				<option value="fcontent">내용</option>
			</select> <input type="text" name="searchValue" id="search-value" /> <input
				type="submit" value="검색" />
		</form>

		<table border="1" width="100%" >
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
			<%--기본 게시글의 경로--%>
			<c:if test="${listboard.hasArticles()&&null eq content}">
				<c:forEach var="board" items="${listboard.content}">
					<tr>
						<td>${board.fno}</td>
						<td><a
							href="<%= request.getContextPath()%>/board/read.do?pageNo=${listboard.currentPage}&fno=${board.fno}">
								<c:out value="[${board.horsehead}]${board.title}" />
						</a></td>
						<td>${board.nickname}</td>
						<td>${board.uploaddate}</td>
						<td>${board.hit}</td>
					</tr>
				</c:forEach>
			</c:if>
			<%--검색 시 나오는 게시글의 경로조정 --%>
			<c:if test="${null ne content}">
				<c:forEach var="board" items="${listboard.content}">
					<tr>
						<td>${board.fno}</td>
						<td><a
							href="<%= request.getContextPath()%>/board/read.do?
							pageNo=${listboard.currentPage}&fno=${board.fno}&
							searchMethod=${method}&searchValue=${content}">
								<c:out value="[${board.horsehead}]${board.title}" />
						</a></td>
						<td>${board.nickname}</td>
						<td>${board.uploaddate}</td>
						<td>${board.hit}</td>
					</tr>
				</c:forEach>
			</c:if>

			<c:if test="${listboard.hasArticles()&&null eq content}">
				<tr>
					<td colspan="4"><c:if test="${listboard.startPage > 5}">
							<a
								href="board.do?pageNo=${listboard.startPage - listboard.page }">[이전]</a>
						</c:if> <c:forEach var="pNo" begin="${listboard.startPage }"
							end="${listboard.endPage }">
							<a href="board.do?pageNo=${pNo }">[ ${pNo } ]</a>
						</c:forEach> <c:if test="${listboard.endPage < listboard.totalPages}">
							<a
								href="board.do?pageNo=${listboard.startPage + listboard.page }">[다음]</a>
						</c:if></td>
				</tr>
			</c:if>
			<c:if test="${null ne content}">
				<tr>
					<td colspan="4"><c:if test="${listboard.startPage > 5}">
							<a
								href="board.do?pageNo=${listboard.startPage - listboard.page }&searchMethod=${method}&searchValue=${content}">
								[이전]</a>
						</c:if> <c:forEach var="pNo" begin="${listboard.startPage }"
							end="${listboard.endPage }">
							<a
								href="board.do?pageNo=${pNo }&searchMethod=${method}&searchValue=${content}">[
								${pNo } ]</a>
						</c:forEach> <c:if test="${listboard.endPage < listboard.totalPages}">
							<a
								href="board.do?pageNo=${listboard.startPage + listboard.page }&searchMethod=${method}&searchValue=${content}">[다음]</a>
						</c:if></td>
				</tr>
			</c:if>
		</table>
	</div>
	
	<div class="board-writebutton" id="1-3">
		<a href="<%=request.getContextPath()%>/board/write.do"> <input
			type="button" value="글쓰기" /></a>
	</div>
	
</div>
</body>
</html>