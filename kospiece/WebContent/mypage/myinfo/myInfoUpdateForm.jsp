<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="title">정보수정</div>
<div class="myDiv">
<form action="/kospiece/infoUpdate.do" method="post" >
	<table class="myDivT">
	    <tr>
	        <th>아이디</th>
	        <td>${user.id}</td>
	    </tr>
	    <tr>
	        <th>이름</th>
	        <td>${user.name}</td>
	    </tr>
	    <tr>
	        <th>닉네임</th>
	        <td>
	        	<input type="text" name="changeNickName" class="" value="${user.nickname}"/>
	        	<c:if test="${errors.checkNick}">닉네임을 입력하세요.</c:if>
	        	<c:if test="${errors.dupliceteNick}">이미 사용중인 닉네임입니다.</c:if>
	        </td>
	    </tr>
	    <tr>
	        <th>이메일</th>
	        <td>
	        	<input type="email" name="changeMail" class=""  value="${user.mail}" />
	        	<c:if test="${errors.checkMail}">이메일을 입력하세요.</c:if>
	        	<c:if test="${errors.dupliceteMail}">이미 사용중인 이메일입니다.</c:if>
        	</td>
	    </tr>
	    <tr>
	        <th>전화번호</th>
	        <td>
        		<input type="text" name="changePhone" class="" value="${user.phone}"/>
        		<c:if test="${errors.checkPhone}">전화번호를 입력하세요.</c:if>
        		<c:if test="${errors.duplicetePhone}">이미 사용중인 전화번호입니다.</c:if>
	        </td>
	    </tr>
	    <tr>
	    	<td colspan="2" align="center">
	    		<c:if test="${errors.noChange}">변경사항이 없습니다.<br/></c:if>
	    		<input type="submit" formaction="/kospiece/infoUpdate.do" value="변경" class="" id="" /> 
	    		<input type="button" value="취소" class="" id="" />
	    	</td>
	    </tr>
	</table>
</form>
</div>
</body>
</html>