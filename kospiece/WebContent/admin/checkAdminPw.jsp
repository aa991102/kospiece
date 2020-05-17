<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="admin-user">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	

	<div class="checkAdminPw">
		<form name="adminPwForm" method="post"
				action="<%= request.getContextPath()%>/checkAdminPw.do">
			<p>비밀번호를 입력해주세요</p>
			<p><input type="password" name="adminPw"/></p>
			<p>${error}</p>
			<input type="hidden" name="userId" value="${param.userId}">
			<input type="hidden" name="service" value="${param.service}">
			<input type="hidden" name="point" value="${param.point}">
			<input type="hidden" name="no" value="${param.no}">
			<input type="submit" value="확인"/>
			<input type="reset" value="취소"/>
		</form>
	</div>
</div>