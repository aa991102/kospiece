<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
function checkForm() {
    if(document.getElementById("notice-content").value==""){
    	alert("검색어를 입력해주세요")
    	return false;
    }
}
</script>

<div class="admin-notice">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	
	<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
	<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a><br/>
	
	<form name="notice-search" id="notice-search" method ="post" class="notice-search" 
		action="<%= request.getContextPath()%>/noticeManage.do" onsubmit="return checkForm();">
	    <select name="search">
	        <option value="ntitle">제목</option>
	        <option value="ncontent">내용</option> 
	    </select>
	    <input type="text" name="inform" id="notice-content"/>
	    <input type="submit" name="notice-submit" value="검색"/>
	</form>
	<table border="1" width="1000" align="center">
        <tr>
        	<th>글번호</th>
        	<th>제목</th>
        	<th>작성일</th>
        	<th>조회</th>
        	<th>수정</th>
        	<th>삭제</th>
        </tr>
			<c:forEach var="notice" items="${noticePage.content}">
        <tr>
        	<td>${notice.nno}</td>
        	<td><a href="<%= request.getContextPath()%>/noticeRead.do?no=${notice.nno}">${notice.title}</a></td>
        	<td>${notice.uploadDate}</td>
        	<td>${notice.hit}</td>
        	<td><a href="<%= request.getContextPath()%>/checkAdminPw.do?service=modify&no=${notice.nno}">수정</a></td>
        	<td><a href="<%= request.getContextPath()%>/checkAdminPw.do?service=delete&no=${notice.nno}">삭제</a></td>
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
						<th colspan="6">
						<!-- 검색조건이 없을 때는 페이지넘버만 파라미터로 보내기 -->
						<c:if test="${null eq content}">
							<%-- [이전prev]출력 --%>
							<c:if test="${noticePage.currentPage>5}">
							<a href="noticeList.do?page=${noticePage.startPage-5}">[이전]</a>
							</c:if>
							
							<%-- 페이지출력 [이전] [1] [2] [3] [4] [5] --%>
							<c:forEach var="pNo" begin="${noticePage.startPage}" end="${noticePage.endPage}">
							<a href="noticeList.do?page=${pNo}">[${pNo}]</a>
							</c:forEach>
							
							<%-- [다음next]출력 --%>
							<c:if test="${noticePage.endPage<noticePage.totalPages}">
							<a href="noticeList.do?page=${noticePage.startPage+5}">[다음]</a>
							</c:if>
						</c:if>
						
						<!-- 검색조건이 있을 때는 페이지넘버와 검색조건도 파라미터로 보내기 -->
						<c:if test="${null ne content}">
							<%-- [이전prev]출력 --%>
							<c:if test="${noticePage.currentPage>5}">
							<a href="noticeList.do?page=${noticePage.startPage-5}&search=${search}&content=${content}">
								[이전]</a>
							</c:if>
							
							<%-- 페이지출력 [이전] [1] [2] [3] [4] [5] --%>
							<c:forEach var="pNo" begin="${noticePage.startPage}" end="${noticePage.endPage}">
							<a href="noticeList.do?page=${pNo}&search=${search}&content=${content}">
								[${pNo}]</a>
							</c:forEach>
							
							<%-- [다음next]출력 --%>
							<c:if test="${noticePage.endPage<noticePage.totalPages}">
							<a href="noticeList.do?page=${noticePage.startPage+5}&search=${search}&content=${content}">
								[다음]</a>
							</c:if>
						</c:if>
						
							</th>
					</tr>
				</c:if>
     </table>
     <a href="<%= request.getContextPath()%>/admin/noticeWrite.jsp">공지사항작성</a>

</div>