<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 1.공지사항 리스트가 나오게 변경 -->
<!-- 2.선택한 제목이나 내용에 맞는 공지사항 리스트가 나오게 변경 -->
<!-- 3.글목록에 페이징 추가 -->

<div class="admin-notice">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	
	<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
	<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a><br/>
	
	<form name="notice-search" method ="post" class="notice-search">
	    <select name="search">
  	        <option value="" selected>전체보기</option>
	        <option value="ntitle">제목</option>
	        <option value="ncontent">내용</option> 
	    </select>
	    <input type="text" name="notice-inform" />
	    <input type="submit" value="검색"/>
	</form>
	<table border="1" width="1000" align="center">
		<tr>
        	<th>글번호</th>
        	<th>제목</th>
        	<th>업로드날짜</th>
        	<th>조회수</th>
        	<th>수정</th>
        	<th>삭제</th>
        </tr>
		<c:forEach var="notice" items="${noticePage.content}">
        <tr>
        	<td>${notice.nno}</td>
        	<td><a href="noticeRead.do?no=${notice.nno}">${notice.title}</a></td>
        	<td>${notice.uploadDate}</td>
        	<td>${notice.hit}</td>
        </tr>
        </c:forEach>
        <c:if test="${noticePage.total==0}">
					<tr>
						<th colspan="4">
							공지사항이 없습니다.
					</tr>
				</c:if>
        <c:if test="${noticePage.total>0}">
					<tr>
						<th colspan="4">
							<%-- [이전prev]출력 --%>
							<c:if test="${noticePage.currentPage>5}">
							<a href="noticeManage.do?page=${noticePage.startPage-5}">[이전]</a>
							</c:if>
							
							<%-- 페이지출력 [이전] [1] [2] [3] [4] [5] --%>
							<c:forEach var="pNo" begin="${noticePage.startPage}" end="${noticePage.endPage}">
							<a href="noticeManage.do?page=${pNo}">[${pNo}]</a>
							</c:forEach>
							
							<%-- [다음next]출력 --%>
							<c:if test="${notice.endPage<notice.totalPages}">
							<a href="noticeManage.do?page=${notice.startPage+5}">[다음]</a>
							</c:if>
							</th>
					</tr>
				</c:if>
     </table>
     <a href="<%= request.getContextPath()%>/admin/noticeWrite.jsp">공지사항작성</a>

</div>