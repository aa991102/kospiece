<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/kospiece/infoUpdatePwCheck.do" method="post" >
		<div class="title">회원탈퇴</div>
		<div class="myDiv">
			<div class="center">
				비밀번호를 다시 한 번 입력해주세요.
			</div>
			<table class="myDivT">
				<tr>
					<th>아이디</th>
					<td>${user.id}</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="passcheck"/>
						<c:if test="${errors.checkPw}">비밀번호를 입력하세요.</c:if>
            <c:if test="${errors.badCurPw}">비밀번호가 일치하지 않습니다.</c:if>
					</td>
				</tr>
			</table>
			<div class="infoBtn-wrapper">
				<input type="submit" class="infoBtn" value="수정"/>
				<input type="button" class="infoBtn" onclick="location.href='/kospiece/myInfo.do';" value="취소"/>
			</div>
		</div>
	</form>
</body>
</html>