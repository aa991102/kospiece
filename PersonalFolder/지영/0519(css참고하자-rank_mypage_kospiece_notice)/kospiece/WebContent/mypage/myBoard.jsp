<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="title">내 게시글</div>
<div class="title-sub" >
	<a href="<%= request.getContextPath()%>/main.do">홈</a>
	&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/mypage.do">마이페이지</a>
	&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/myBoardList.do">내 게시글</a>
</div>
<div class="notice">
	<form name="myBoard-search" method ="post" class="notice-search" 
		action="<%= request.getContextPath()%>/myBoardList.do">
	    <select name="search">
	        <option value="ftitle">제목</option>
	        <option value="fcontent">내용</option> 
	    </select>
	    <input type="text" name="content" />
	    <input type="submit" value="검색"/>
	</form>
	<table border="1" width="1000" align="center">
        <tr>
        	<th>글번호</th>
        	<th>제목</th>
        	<th>작성일</th>
        	<th>조회</th>
        </tr>
			<c:forEach var="myBoard" items="${myBoardPage.content}">
        <tr>
        	<td>${myBoard.fno}</td>
        	<td><a href="myBoardDetail.do?no=${myBoard.fno}">${myBoard.title}</a></td>
        	<td>${myBoard.uploaddate}</td>
        	<td>${myBoard.hit}</td>
        </tr>
        </c:forEach>
        <c:if test="${myBoardPage.total==0}">
					<tr>
						<td colspan="4">작성 게시글이 없습니다.</td>
					</tr>
				</c:if>
        <c:if test="${myBoardPage.total>0}">
					<tr>
						<th colspan="4">
						<!-- 검색조건이 없을 때는 페이지넘버만 파라미터로 보내기 -->
						<c:if test="${null eq content}">
							<%-- [이전prev]출력 --%>
							<c:if test="${myBoardPage.currentPage>5}">
							<a href="myBoardList.do?page=${myBoardPage.startPage-5}">[이전]</a>
							</c:if>
							
							<%-- 페이지출력 [이전] [1] [2] [3] [4] [5] --%>
							<c:forEach var="pNo" begin="${myBoardPage.startPage}" end="${myBoardPage.endPage}">
							<a href="myBoardList.do?page=${pNo}">[${pNo}]</a>
							</c:forEach>
							
							<%-- [다음next]출력 --%>
							<c:if test="${myBoardPage.endPage<myBoardPage.totalPages}">
							<a href="myBoardList.do?page=${myBoardPage.startPage+5}">[다음]</a>
							</c:if>
						</c:if>
						
						<!-- 검색조건이 있을 때는 페이지넘버와 검색조건도 파라미터로 보내기 -->
						<c:if test="${null ne content}">
							<%-- [이전prev]출력 --%>
							<c:if test="${myBoardPage.currentPage>5}">
							<a href="myBoardList.do?page=${myBoardPage.startPage-5}&search=${search}&content=${content}">
								[이전]</a>
							</c:if>
							
							<%-- 페이지출력 [이전] [1] [2] [3] [4] [5] --%>
							<c:forEach var="pNo" begin="${myBoardPage.startPage}" end="${myBoardPage.endPage}">
							<a href="myBoardList.do?page=${pNo}&search=${search}&content=${content}">
								[${pNo}]</a>
							</c:forEach>
							
							<%-- [다음next]출력 --%>
							<c:if test="${myBoardPage.endPage<myBoardPage.totalPages}">
							<a href="myBoardList.do?page=${myBoardPage.startPage+5}&search=${search}&content=${content}">
								[다음]</a>
							</c:if>
						</c:if>
						
							</th>
					</tr>
				</c:if>
     </table>
</div>
