<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="title">회원탈퇴</div>
<div class="title-sub" >
	<a href="<%= request.getContextPath()%>/main.do">홈</a>
	&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/mypage.do">마이페이지</a>
	&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/myInfo.do">내 정보</a>
</div>
<div class="myDiv">
<form action="/kospiece/withdrawal.do" method="post" id="withdrawalFrm">
	<table class="myDivT">
	    <tr>
	        <th class="infoH">아이디</th>
	        <td class="infoContent">${member.id}</td>
	    </tr>
	    <tr>
	        <th class="infoH">비밀번호</th>
	        <td class="infoContent">
	            <input type="password" name="passcheck" class="curPw"/>
	            <input type="button" class="deleteCurBtn" value="✕"/>
	            <input type="button" class="seeCurBtn" value="보기"/>
	            <c:if test="${errors.checkPw}"><br/>비밀번호를 입력하세요.</c:if>
              <c:if test="${errors.badCurPw}"><br/>비밀번호가 일치하지 않습니다.</c:if>
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2">
	            - 비밀번호 확인 후 아이디는 즉시 탈퇴됩니다.<br/>
	            - 탈퇴 후에는 기존의 아이디와 데이터는 복구할 수 없으니 신중하게 선택해주세요.
	        </td>
	    </tr>
	</table>
	<div class="infoBtn-wrapper">
  	<input type="submit" class="smbtn" id="smbtn" value="탈퇴"/>
    <input type="button" value="취소" id="Nope" onClick="location.href='<%=request.getContextPath()%>/myInfo.do'" class="infoBtn" />
  </div>
</form>
</div>
</body>
</html>