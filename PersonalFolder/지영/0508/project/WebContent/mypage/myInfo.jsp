<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="title">내 정보</div>

<div class="myDiv">
	<table class="myT">
		<tr>
			<th class="infoH">아이디</th>
			<td class="infoContent">hongid</td>
		</tr>
		<tr>
			<th class="infoH">이름</th>
			<td class="infoContent">홍길동</td>
		</tr>
		<tr>
			<th class="infoH">닉네임</th>
			<td class="infoContent">대박이야</td>
		</tr>
		<tr>
			<th class="infoH">예수금 포인트</th>
			<td class="infoContent">100,000 p</td>
		</tr>
		<tr>
			<th class="infoH">자산 포인트</th>
			<td class="infoContent">2,100,000 p</td>
		</tr>
		<tr>
			<th class="infoH">이메일</th>
			<td class="infoContent"></td>
		</tr>
		<tr>
			<th class="infoH">전화번호</th>
			<td class="infoContent"></td>
		</tr>
	</table>
	<div class="infoBtn-wrapper">
		<div class="infoBtn" style="cursor: pointer;" onclick="location.href='myInfo/myInfoUpdatePassChk.jsp';">정보 수정</div>
		<div class="infoBtn" style="cursor: pointer;" onclick="location.href='myInfo/passwordUpdateForm.jsp';">비밀번호 변경</div>
		<div class="infoBtn" style="cursor: pointer;" onclick="location.href='myInfo/withdrawalForm.jsp';">탈퇴하기</div>
	</div>
</div>
</body>
</html>