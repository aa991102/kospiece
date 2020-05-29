<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="title">내 게시글</div>
<div class="title-sub" >
	<a href="<%= request.getContextPath()%>/main.do">홈</a>
	&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/mypage.do">마이페이지</a>
	&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/myBoardList.do">내 게시글</a>
</div>
<div class="board">
	
	<form name="myBoard-search" method ="post" onsubmit="return checkForm();"
		action="<%= request.getContextPath()%>/myBoardList.do" class="myBoard-search">
		<div class="insertFavorite">
	    <select name="search" class="selectCss">
	        <option value="ftitle">제목</option>
	        <option value="fcontent">내용</option> 
	    </select>
	    <input type="text" name="content" />
	    <input type="submit" value="검색" class="button"/>
    </div>
	</form>
	<form action="<%= request.getContextPath()%>/myBoardDelete.do" method="post">
		<c:set var="error" value="${errors}"/>
		<script type="text/javascript">if("${error}"!=""){$(function(){alert("${error}")})}</script>
		<input type="submit" value="삭제" style="margin:10px 0;" class="button" onclick="return deleteBoardCheck();"/>
		<table class="myT">
      <tr>
      	<th class="center" width="5%"><input type="checkbox" id="allCheck" /></th>
      	<th class="center" width="15%">글번호</th>
      	<th class="center">제목</th>
      	<th class="center" width="25%">작성일</th>
      	<th class="center" width="15%">조회</th>
      </tr>
			<c:forEach var="myBoard" items="${myBoardPage.content}">
        <tr>
        	<td class="center"><input type="checkbox" name="fno" value="${myBoard.fno}"></td>
        	<td class="center">${myBoard.fno}</td>
        	<td><a href="myBoardRead.do?fno=${myBoard.fno}">[${myBoard.horsehead}] ${myBoard.title}</a></td>
        	<td class="center2">${myBoard.uploaddate}</td>
        	<td class="center2">${myBoard.hit}</td>
        </tr>
        </c:forEach>
        <c:if test="${myBoardPage.total==0}">
					<tr>
						<td colspan="100%" class="empty">
		    			<img src="<%= request.getContextPath()%>/img/alert.png" style="max-width:10%;height:auto;"><br/>
		    			작성한 게시글이 없습니다.
	    			</td>
					</tr>
				</c:if>
        <c:if test="${myBoardPage.total>0}">
					<tr>
						<td colspan="100%" class="center2" >
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
						
							</td>
					</tr>
				</c:if>
     </table>
   </form>
</div>
