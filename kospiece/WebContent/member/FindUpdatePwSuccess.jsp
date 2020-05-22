
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="title">비밀번호 찾기, 변경</div>

<div class="myDiv">
	비밀번호 변경이 완료되었습니다
	
	<div class="infoBtn-wrapper">
		<div class="infoBtn" style="cursor: pointer;" onclick="location.href='<%= request.getContextPath()%>/member/login.jsp'">로그인</div>
	</div>
</div>