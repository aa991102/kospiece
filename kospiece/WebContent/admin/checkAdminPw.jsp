<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="admin-user">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	
	<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
	<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a><br/>
	<div class="checkAdminPw">
		<form name="adminPwForm" method="post"
				action="<%= request.getContextPath()%>/checkAdminPw.do">
			<p>비밀번호를 입력해주세요</p>
			<p><input type="password" name="adminPw"/></p>
			<p>${error}</p>
			<input type="hidden" name="userId" value="${param.userId}">
			<input type="hidden" name="service" value="${param.service}">
			${param.userId}
			<input type="submit" value="확인"/>
			<input type="reset" value="취소"/>
		</form>
	</div>
</div>