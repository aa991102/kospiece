<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="admin-user">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	
	<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
	<a href="<%= request.getContextPath()%>/noticeList.do" class="notice-button">공지사항</a><br/>
	<div class="checkAdminPw">
		<form name="adminPwForm">
			<p>비밀번호를 다시 한 번 입력해주세요</p>
			<p><input type="password" name="adminPw"/>
			<p><input type="submit" value="충전"/><input type="reset" value="취소"/></p>
		</form>
	</div>
</div>