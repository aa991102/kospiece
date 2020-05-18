<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 관리자페이지에서의 모든 로직은 비밀번호를 확인한 후 실행한다. -->

<div class="admin-user">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	

	<div class="checkAdminPw" style="width: 300px; height: 300px; margin:0 auto;">
		<form name="adminPwForm" method="post" action="<%= request.getContextPath()%>/checkAdminPw.do">
			<p>비밀번호를 입력해주세요</p>
			<p><input type="password" name="adminPw"/></p>
			
			<!-- 비밀번호가 틀렸다면 에러메시지 출력 -->
			<p>${error}</p>
			
			<!-- 로직을 실행하는 데 필요한 파라미터들을 셋팅 -->
			<input type="hidden" name="userNick" value="${param.userNick}">
			<input type="hidden" name="service" value="${param.service}">
			<input type="hidden" name="point" value="${param.point}">
			<input type="hidden" name="no" value="${param.no}">
			
			<input type="submit" value="확인"/>
			<input type="button" value="취소" onclick="history.back()"/>
		</form>
	</div>
</div>