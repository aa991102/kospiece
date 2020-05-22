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
<div class="title-sub" >
	<a href="<%= request.getContextPath()%>/main.do">홈</a>
	&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/mypage.do">마이페이지</a>
	&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/myInfo.do">내 정보</a>
</div>
<div class="myDiv">
	<form action="changePw.do" method="post" id="passChangeFrm">
	    <table class="myDivT">
	        <tr>
	            <td class="infoH">현재 비밀번호</td>
	            <td class="infoContent">
	                <input type="password" name="curPw" class="curPw" />
	                <input type="button" class="deleteCurBtn" value="✕"/>
	                <input type="button" class="seeCurBtn" value="보기"/>
	                <c:if test="${errors.curPw}"><br/>현재 비밀번호를 입력하세요.</c:if>
	                <c:if test="${errors.badCurPw}"><br/>현재 비밀번호가 일치하지 않습니다.</c:if>
	            </td>
	        </tr>
	        <tr>
	            <td class="infoH">새로운 비밀번호</td>
	            <td class="infoContent">
	                <input type="password" name="newPw" class="newPw" />
	                <input type="button" class="deleteNewBtn" value="✕"/>
	                <input type="button" class="seeNewBtn" value="보기"/>
	                <c:if test="${errors.newPw}"><br/>새로운 비밀번호를 입력하세요.</c:if>
	            </td>
	        </tr>
	    </table>
      <div class="infoBtn-wrapper">
      	<input type="submit" value="변경" class="infoBtn" />
         <input type="button" value="취소" id="Nope" onClick="location.href='<%=request.getContextPath()%>/myInfo.do'" class="infoBtn" />
      </div>
	</form>
</div>
</body>
</html>