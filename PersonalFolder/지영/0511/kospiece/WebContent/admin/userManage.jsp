<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 1.회원 리스트가 나오게 변경 -->
<!-- 2.선택한 닉네임이나 아이디에 맞는 회원 리스트가 나오게 변경 -->
<!-- 3.회원목록에 페이징 추가 -->

<div class="admin-user">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	
	<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
	<a href="<%= request.getContextPath()%>/noticeList.do" class="notice-button">공지사항</a><br/>
	
	<form action="<%= request.getContextPath()%>/userList.do"name="user-search" method ="post" class="user-search">
	    <select name="search">
    		<option value=""selected>전체</option>	
	        <option value="mnick">닉네임</option>
	        <option value="mid">아이디</option> 
	    </select>
	    <input type="text" name="user-inform" />
	    <input type="submit" value="검색"/>
	</form>
	

	
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
	 <c:forEach var="member" items="${memberList}">
 	 <c:set var="adminOK" value="${member.nickname=='관리자1'||member.nickname=='관리자2'||member.nickname=='관리자3'||member.nickname=='관리자4'
           	||member.nickname=='관리자5'||member.nickname=='관리자6'}"/>
  		<c:if test='${adminOK}'>
	        <tr class="admin-list">
	        	<td>${member.nickname}</td>
	        	<td>${member.id}</td>
	        	<c:set var="id" value="${admin.id}"/>
	        	<td>${member.name}</td>
	        	<td>${member.mail}</td>
	        	<td>${member.regdate}</td>
	        	<td>${member.deposit}
	        	<a href="<%= request.getContextPath()%>/pointCharge.do">충전</a>
	        	</td>
	        	<td></td>
	        </tr>
        </c:if>
     </c:forEach>
     <!-- 일반회원을 아래로 나열 -->
     <c:forEach var="member" items="${memberList}">
      	 <c:set var="adminOK" value="${member.nickname=='관리자1'||member.nickname=='관리자2'||member.nickname=='관리자3'||member.nickname=='관리자4'
           	||member.nickname=='관리자5'||member.nickname=='관리자6'}"/>
         <c:if test='${!adminOK}'>
	        <tr class="member-list">
	        	<td>${member.nickname}</td>
	        	<td>${member.id}</td>
	        	<c:set var="id" value="${member.id}"/>
	        	<td>${member.name}</td>
	        	<td>${member.mail}</td>
	        	<td>${member.regdate}</td>
	        	<td>${member.deposit}<a href="<%= request.getContextPath()%>/pointCharge.do">충전</a></td>
	        	<td>
	        	<form name="deleteUser" method="post" action="/checkAdminPw.do">
		        	<input type="hidden" name="id" value="${member.id}">
		        	<input type="submit" value="탈퇴" onclick="checkPW()">
	        	</form>
	        	</td>
	        </tr>
        </c:if>
		
	 </c:forEach>
     </table>
</div>