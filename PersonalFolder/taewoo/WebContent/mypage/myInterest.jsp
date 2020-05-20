<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
</script>
</head>
<body>
<div class="title">관심주식</div>
<div class="title-sub" >
	<a href="<%= request.getContextPath()%>/main.do">홈</a>
	&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/mypage.do">마이페이지</a>
	&nbsp;|&nbsp;<a href="<%= request.getContextPath()%>/myInterest.do">관심주식</a>
</div>
<div class="interest">
	
	<form name="interestFrm" id="interestFrm" method="post" >
		<c:set var="error" value="${errors}"/>
		<script type="text/javascript">if("${error}"!=""){$(function(){alert("${error}")})}</script>
		<div class="moveFavorite">
			<div class="inlineDiv">
				
			</div>
		</div>
		<div id="help2" class="helpDiv2">
			도움말<input type="button" id="helpClose" value="✕"/>
			<br/>~~~~~~~~~입니다.
		</div>
		<div class="insertFavorite">
			<div class="inlineDiv">종목추가</div>
			<div class="inlineDiv">
			    <input type="text" name="snameSearh" id="sname"/>
			    <input type="submit" formaction="<%= request.getContextPath()%>/myInterestInsert.do" name="insertBtn" id="insertBtn" value="추가"/>
		    	<input type="submit" formaction="<%= request.getContextPath()%>/myInterestDelete.do" name="deleteBtn" class="btn-del" value="삭제" onclick="return deleteCheck();"/>
		    	<input type="submit" formaction="<%= request.getContextPath()%>/simulation.do" id="investBtn" value="가상투자하기" onclick="return goToInvest();"/>
		    	<input type="button" value="?" id="helpBtn2"/>
		    </div>
	    </div>

		<table class="myT">
			<tr>
				<th><input type="checkbox" id="allCheck" /></th>
				<th>회사명</th>
				<th>업종</th>
				<th>세부업종</th>
				<th>현재가</th>
				<th>전일비</th>
				<th>등락율</th>
				<th>거래량</th>
				<th>거래대금</th>
				<th>시가총액</th>
				<th>52주고가</th>
			</tr>
			
				<c:forEach var="list" items="${myInterestList}" varStatus="status">
			    <tr>
						<td align="center"><input type="checkbox" name="sname" value="${list.name}"></td>
						<td>${list.name}</td>
						<td>${list.field}</td>
						<td>${list.detail}</td>
						<td>${list.price}</td>
						<td>${list.dayrate}</td>
						<td>${list.changerate}</td>
						<td>${list.volume}</td>
						<td>${list.dealprice}</td>
						<td>${list.total}</td>
						<td>${list.high52}</td>
					</tr>
		    </c:forEach>
		</table>
	</form>
</div>
</body>
</html>