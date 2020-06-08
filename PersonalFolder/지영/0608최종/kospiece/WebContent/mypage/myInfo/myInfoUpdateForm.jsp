<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="title">정보 수정</div>
<div class="title-sub" >
	<a href="<%= request.getContextPath()%>/main.do">홈</a>
	&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/mypage.do">마이페이지</a>
	&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/myInfo.do">내 정보</a>
</div>
<div class="myDiv">
<form action="/kospiece/infoUpdate.do" method="post" id="infoUpdateFrm">
	<table class="myDivT">
	    <tr>
	        <th class="infoH">아이디</th>
	        <td class="infoContent">${user.id}</td>
	    </tr>
	    <tr>
	        <th class="infoH">이름</th>
	        <td class="infoContent">${user.name}</td>
	    </tr>
	    <tr>
	        <th class="infoH">닉네임</th>
	        <td class="infoContent">
	        	<input type="text" name="changeNickName" class="" value="${user.nickname}"/>
	        	<c:if test="${errors.checkNick}"><br/>닉네임을 입력하세요.</c:if>
	        	<c:if test="${errors.dupliceteNick}"><br/>이미 사용중인 닉네임입니다.</c:if>
	        </td>
	    </tr>
	    <tr>
	        <th class="infoH">이메일</th>
	        <td class="infoContent">
	        	<input type="email" name="changeMail" class=""  value="${user.mail}" />
	        	<c:if test="${errors.checkMail}">이메일을 입력하세요.</c:if>
	        	<c:if test="${errors.dupliceteMail}">이미 사용중인 이메일입니다.</c:if>
        	</td>
	    </tr>
	    <tr>
	        <th class="infoH">전화번호</th>
	        <td class="infoContent">
        		<input type="text" name="changePhone" class="" value="${user.phone}"/>
        		<c:if test="${errors.checkPhone}">전화번호를 입력하세요.</c:if>
        		<c:if test="${errors.duplicetePhone}">이미 사용중인 전화번호입니다.</c:if>
	        </td>
	    </tr>
   		<c:if test="${errors.noChange}">
		    <tr>
		    	<td colspan="2" align="center">변경사항이 없습니다.</td>
	   		</tr>
	    </c:if>
	</table>
	<div class="infoBtn-wrapper">
  	<input type="submit" formaction="/kospiece/infoUpdate.do" value="변경" class="infoBtn" />
    <input type="button" value="취소" id="Nope" onClick="location.href='<%=request.getContextPath()%>/myInfo.do'" class="infoBtn" />
  </div>
</form>
</div>
</body>
</html>