<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 1.회원 리스트가 나오게 변경 -->
<!-- 2.선택한 닉네임이나 아이디에 맞는 회원 리스트가 나오게 변경 -->
<!-- 3.회원목록에 페이징 추가 -->

<div class="admin-user">
	<a href="admin.jsp" class="admin-logo">관리자 페이지</a>	
	<a href="userManage.jsp" class="user-button">회원관리</a>
	<a href="noticeManage.jsp" class="notice-button">공지사항</a><br/>
	
	<form name="user-search" method ="post" class="user-search">
	    <select name="search">
    		<option value="total" selected>전체</option>	
	        <option value="nickname">닉네임</option>
	        <option value="id">아이디</option> 
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
        	<th>자산포인트</th>
        	<th>강제탈퇴</th>
        </tr>
     </table>
</div>