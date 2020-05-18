<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
function checkForm() {
    if(document.getElementById("member-content").value==""){
    	alert("회원정보를 입력해주세요")
    	return false;
    }
}
function checkCharge(nick){
	result=confirm(nick+"님의 포인트를 충전하시겠습니까?")
	if(result){
		return true;
	}else{
		return false;
	}
	
}
function checkDelete(nick){
	result=confirm(nick+"님을 강제탈퇴하시겠습니까?")
	if(result){
		return true;
	}else{
		return false;
	}
}
</script>

<div class="admin-user">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	
	<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
	<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a><br/>
	
	<!-- 회원을 닉네임이나 아이디로 검색 가능 -->
	<form action="<%= request.getContextPath()%>/userList.do"name="user-search" 
		method ="post" class="user-search" onsubmit="return checkForm();">
	    <select name="search">	
	        <option value="mnick">닉네임</option>
	        <option value="mid">아이디</option> 
	    </select>
	    <input type="text" name="user-inform" id="member-content"/>
	    <input type="submit" value="검색"/>
	</form>
	
	<!-- 검색된 회원 리스트 -->
	<table border="1" width="1000" align="center">
        <tr>
        	<th>닉네임</th>
        	<th>아이디</th>
        	<th>회원명</th>
        	<th>이메일</th>
        	<th>가입일</th>
        	<th>예수금포인트</th>
        	<th>강제탈퇴</th>
        </tr>
        
     <!-- 멤버 중 관리자인 회원을 최 상단에 출력 -->
	 <c:forEach var="member" items="${memberPage.content}">
 	 <c:set var="adminOK" value="${member.nickname=='관리자1'||member.nickname=='관리자2'||member.nickname=='관리자3'||member.nickname=='관리자4'
            	||member.nickname=='관리자5'||member.nickname=='관리자6'}"/>
  		<c:if test='${adminOK}'>
	        <tr class="admin-list">
	        	<td>${member.nickname}</td>
	        	<td>${member.id}</td>
	        	<td>${member.name}</td>
	        	<td>${member.mail}</td>
	        	<td>${member.regdate}</td>
	        	<td>${member.deposit}
	        	<form name="delete" method="post" action="<%= request.getContextPath()%>/checkAdminPw.do"
	        		onsubmit="return checkCharge('${member.nickname}');">
	        		<input type="hidden" name="userNick" value="${member.nickname}"/>
	        		<input type="hidden" name="point" value="${member.deposit}"/>
					<input type="hidden" name="service" value="pointCharge"/>
					<input type="submit" value="충전">
				</form>
	        	</td>
	        	<td></td>
	        </tr>
        </c:if>
     </c:forEach>
     <!-- 일반회원을 아래로 나열 -->
     <c:forEach var="member" items="${memberPage.content}" varStatus="status">
      	 <c:set var="adminOK" value="${member.nickname=='관리자1'||member.nickname=='관리자2'||member.nickname=='관리자3'||member.nickname=='관리자4'
            	||member.nickname=='관리자5'||member.nickname=='관리자6'}"/>
         <c:if test='${!adminOK}'>
	        <tr class="member-list">
	        	<td>${member.nickname}</td>
	        	<td>${member.id}</td>
	        	<td>${member.name}</td>
	        	<td>${member.mail}</td>
	        	<td>${member.regdate}</td>
	        	<td>${member.deposit}
	        	<form name="delete" method="post" action="<%= request.getContextPath()%>/checkAdminPw.do"
	        		onsubmit="return checkCharge('${member.nickname}');">
	        		<input type="hidden" name="userNick" value="${member.nickname}"/>
	        		<input type="hidden" name="point" value="${member.deposit}"/>
					<input type="hidden" name="service" value="pointCharge"/>
					<input type="submit" value="충전">
				</form>
	        	</td>
	        	<td>
	        	<form name="delete" method="post" action="<%= request.getContextPath()%>/checkAdminPw.do"
	        		onsubmit="return checkDelete('${member.nickname}');">
	        		<input type="hidden" name="userNick" value="${member.nickname}"/>
					<input type="hidden" name="service" value="deleteMember"/>
					<input type="submit" value="탈퇴">
				</form>
	        	</td>
	        </tr>
        </c:if>
	 </c:forEach>
	 ${memberPage }
	 <!-- 검색된 공지사항이 없을 경우 출력 -->
   <c:if test="${memberPage.total==0}">
			<tr>
				<th colspan="7">
					공지사항이 없습니다.
			</tr>
		</c:if>
		
	 <!-- 회원목록 페이징 -->		
     <c:if test="${memberPage.total>0}">
			<tr>
				<th colspan="7">
				<!-- 검색조건이 없을 때는 페이지넘버만 파라미터로 보내기 -->
				<c:if test="${null eq content}">
					<%-- [이전prev]출력 --%>
					<c:if test="${memberPage.currentPage>5}">
						<a href="userList.do?page=${memberPage.startPage-5}">[이전]</a>
					</c:if>
					
					<%-- 페이지출력 [이전] [1] [2] [3] [4] [5] --%>
					<c:forEach var="pNo" begin="${memberPage.startPage}" end="${memberPage.endPage}">
						<a href="userList.do?page=${pNo}">[${pNo}]</a>
					</c:forEach>
					
					<%-- [다음next]출력 --%>
					<c:if test="${memberPage.endPage<memberPage.totalPages}">
						<a href="userList.do?page=${memberPage.startPage+5}">[다음]</a>
					</c:if>
				</c:if>
				
				<!-- 검색조건이 있을 때는 페이지넘버와 검색조건도 파라미터로 보내기 -->
				<c:if test="${null ne content}">
					<%-- [이전prev]출력 --%>
					<c:if test="${memberPage.currentPage>5}">
						<a href="userList.do?page=${memberPage.startPage-5}&search=${search}&inform=${content}">
							[이전]</a>
					</c:if>
					
					<%-- 페이지출력 [이전] [1] [2] [3] [4] [5] --%>
					<c:forEach var="pNo" begin="${memberPage.startPage}" end="${memberPage.endPage}">
						<a href="userList.do?page=${pNo}&search=${search}&inform=${content}">
							[${pNo}]</a>
					</c:forEach>
					
					<%-- [다음next]출력 --%>
					<c:if test="${memberPage.endPage<memberPage.totalPages}">
						<a href="userList.do?page=${memberPage.startPage+5}&search=${search}&inform=${content}">
							[다음]</a>
					</c:if>
					</c:if>
				</c:if>
     </table>
</div>