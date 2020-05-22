<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 검색어를 입력하지 않고 검색을 누르는 것을 방지 -->
<script>
function checkForm() {
    if(document.getElementById("notice-content").value==""){
    	alert("검색어를 입력해주세요")
    	return false;
    }
}
</script>

<div class="title">
	<a href="<%= request.getContextPath()%>/admin.do" >관리자 페이지</a>
</div>

<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a>

<div class="admin-notice">
	
	<div class="admin-div">
		<div class="board-search">
			<!-- 제목이나 내용으로 공지사항을 검색하는 form -->
			<form name="notice-search" id="notice-search" method ="post" class="notice-search" 
				action="<%= request.getContextPath()%>/noticeManage.do" onsubmit="return checkForm();">
		    <div class="insertFavorite">
			    <select name="search" class="selectCss">
			        <option value="ntitle">제목</option>
			        <option value="ncontent">내용</option> 
			    </select>
			    <input type="text" name="inform" id="notice-content"/>
			    <input type="submit" name="notice-submit" value="검색"/>
		    </div>
			</form>
		</div>
		<!-- 검색한 공지사항 출력 -->
		<table class="myT">
	        <tr>
	        	<th class="center">글번호</th>
	        	<th>제목</th>
	        	<th class="center">작성일</th>
	        	<th class="center">조회</th>
	        	<th class="center" width="10%">수정</th>
	        	<th class="center" width="10%">삭제</th>
	        </tr>
	        
	        <!-- 검색된 공지사항 전부 출력 -->
			<c:forEach var="notice" items="${noticePage.content}">
	        <tr>
	        	<td class="center">${notice.nno}</td>
	        	<td><a href="<%= request.getContextPath()%>/noticeRead.do?no=${notice.nno}">${notice.title}</a></td>
	        	<td class="center2">${notice.uploadDate}</td>
	        	<td class="center2">${notice.hit}</td>
	        	<td align="center"><input type="button" value="수정" onClick="location.href='<%= request.getContextPath()%>/checkAdminPw.do?service=modify&no=${notice.nno}'" class="button"></td>
	        	<td align="center"><input type="button" value="삭제" onClick="location.href='<%= request.getContextPath()%>/checkAdminPw.do?service=delete&no=${notice.nno}'" class="button2"></td>
	        </tr>
	        </c:forEach>
	        
	        <!-- 검색된 공지사항이 없을 경우 출력 -->
	        <c:if test="${noticePage.total==0}">
				<tr>
					<td colspan="6" class="center2">
						공지사항이 없습니다.
				</tr>
			</c:if>
					
			<!-- 공지사항 페이징 -->		
	        <c:if test="${noticePage.total>0}">
				<tr>
					<td colspan="6" class="center2">
					<!-- 검색조건이 없을 때는 페이지넘버만 파라미터로 보내기 -->
					<c:if test="${null eq content}">
						<%-- [이전prev]출력 --%>
						<c:if test="${noticePage.currentPage>5}">
							<a href="noticeManage.do?page=${noticePage.startPage-5}">[이전]</a>
						</c:if>
						
						<%-- 페이지출력 [이전] [1] [2] [3] [4] [5] --%>
						<c:forEach var="pNo" begin="${noticePage.startPage}" end="${noticePage.endPage}">
							<a href="noticeManage.do?page=${pNo}">[${pNo}]</a>
						</c:forEach>
						
						<%-- [다음next]출력 --%>
						<c:if test="${noticePage.endPage<noticePage.totalPages}">
							<a href="noticeManage.do?page=${noticePage.startPage+5}">[다음]</a>
						</c:if>
					</c:if>
					
					<!-- 검색조건이 있을 때는 페이지넘버와 검색조건도 파라미터로 보내기 -->
					<c:if test="${null ne content}">
						<%-- [이전prev]출력 --%>
						<c:if test="${noticePage.currentPage>5}">
							<a href="noticeManage.do?page=${noticePage.startPage-5}&search=${search}&inform=${content}">
								[이전]</a>
						</c:if>
						
						<%-- 페이지출력 [이전] [1] [2] [3] [4] [5] --%>
						<c:forEach var="pNo" begin="${noticePage.startPage}" end="${noticePage.endPage}">
							<a href="noticeManage.do?page=${pNo}&search=${search}&inform=${content}">
								[${pNo}]</a>
						</c:forEach>
						
						<%-- [다음next]출력 --%>
						<c:if test="${noticePage.endPage<noticePage.totalPages}">
							<a href="noticeManage.do?page=${noticePage.startPage+5}&search=${search}&inform=${content}">
								[다음]</a>
						</c:if>
					</c:if>
					</td>
				</tr>
			</c:if>
	     </table>
	     
	     <!-- 공지사항 작성 -->
	     <input type="button" value="공지사항 작성" onClick="location.href='<%= request.getContextPath()%>/checkAdminPw.do?service=write';" class="button" style="float:right;margin:0 10px 10px 10px;"/>
	     
	</div>
</div>