<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="title">회원탈퇴</div>
	<div class="myDiv">
		<div class="center">
			비밀번호를 다시 한 번 입력해주세요.
		</div>
		<table class="myDivT">
			<tr>
				<th>아이디</th>
				<td>hongid1004</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password"/>
				</td>
			</tr>
		</table>
		<div class="infoBtn-wrapper">
			<div class="infoBtn" style="cursor: pointer;" onclick="location.href='myInfo/myinfoUpdateForm.jsp';">수정</div>
			<div class="infoBtn" style="cursor: pointer;" onclick="location.href='#';">취소</div>
		</div>
	</div>
</body>
</html>