<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="title">비밀번호 변경</div>
<div class="myDiv">
	<form action="changePw.do" method="post">
	    <table class="myDivT">
	        <tr>
	            <td>현재 비밀번호</td>
	            <td>
	                <input type="password" name="curPw" id="" class="" />
	                <input type="button" class="" id="" value="✕"/>
	                <input type="button" class="" id="" value="보기"/>
	                <c:if test="${errors.curPw}"><br/>현재 비밀번호를 입력하세요.</c:if>
	                <c:if test="${errors.badCurPw}"><br/>현재 비밀번호가 일치하지 않습니다.</c:if>
	            </td>
	        </tr>
	        <tr>
	            <td>새로운 비밀번호</td>
	            <td>
	                <input type="password" name="newPw" id="" class="" />
	                <input type="button" class="" id="" value="✕"/>
	                <input type="button" class="" id="" value="보기"/>
	                <c:if test="${errors.newPw}"><br/>새로운 비밀번호를 입력하세요.</c:if>
	            </td>
	        </tr>
	        <tr>
	            <th colspan="100%">
	                <input type="submit" class="" id="" value="변경"/>
	                <input type="button" class="" id="" value="취소"/>
	            </th>
	        </tr>
	    </table>
	</form>
</div>
</body>
</html>