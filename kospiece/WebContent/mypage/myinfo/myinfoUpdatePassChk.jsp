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
		<div class="title">정보 수정</div>
		<div class="title-sub" >
			<a href="<%= request.getContextPath()%>/main.do">홈</a>
			&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/mypage.do">마이페이지</a>
			&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/myInfo.do">내 정보</a>
		</div>
		<div class="myDiv">
			<div class="center">
				비밀번호를 다시 한 번 입력해주세요.
			</div>
			<table class="myDivT">
				<tr>
					<th class="infoH">아이디</th>
					<td class="infoContent">${user.id}</td>
				</tr>
				<tr>
					<th class="infoH">비밀번호</th>
					<td class="infoContent">
						<input type="password" name="passcheck" class="curPw" />
						<input type="button" class="deleteCurBtn" value="✕"/>
            <input type="button" class="seeCurBtn" value="보기"/>
						<c:if test="${errors.checkPw}"><br/>비밀번호를 입력하세요.</c:if>
            <c:if test="${errors.badCurPw}"><br/>비밀번호가 일치하지 않습니다.</c:if>
					</td>
				</tr>
			</table>
			<div class="infoBtn-wrapper">
				<input type="submit" class="infoBtn" value="수정"/>
				<input type="button" class="infoBtn" value="취소" id="Nope" onClick="location.href='<%=request.getContextPath()%>/myInfo.do'"/>
			</div>
		</div>
	</form>
</body>
</html>