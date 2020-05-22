<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="title">비밀번호 찾기</div>
<div class="findPw">
	<form action="<%= request.getContextPath()%>/find.do" method="post">
		<table style="margin:0 auto;">
			<tr>
				<th style="border-top:none;">사용자 이름 : </th>
				<td><input type="text" name="mname"/></td>
			</tr>
			<tr>
				<th>e-mail : </th>
				<td><input type="text" name="mmail"/></td>
			</tr>
			<tr>
				<th>아 이 디 : </th>
				<td><input type="text" name="mid"/></td>
			</tr>
			
		</table>
			<c:if test="${!empty result}">
				${result}
			</c:if>
			<div class="infoBtn-wrapper">
	     	<input type="submit" class="infoBtn" value="인증"/>
	     	<input type="button" value="취소" id="Nope" class="infoBtn" style="cursor: pointer;" onclick="location.href='<%= request.getContextPath()%>/member/login.jsp'"/>
	   	</div>
	</form>
</div>
</body>
</html>