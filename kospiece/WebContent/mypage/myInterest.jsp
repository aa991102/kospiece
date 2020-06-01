<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<a style="padding:10px 10px 10px 140px;font-size:1.6rem;font-weight:bold;line-height:50px;">도움말</a>
			<input type="button" id="helpClose" value="✕"/>
			<br/>현재가: 시간에 따라 변동하는 주식의 현재 가격</a>
			<br/>전일비: 전일에 비해서 가격이 올랐거나 내린 정도
			<br/>등락률: 기준시점에 대한 비교시점의 증감률(%)
			<br/>거래량: 매매거래가 성립된 수량
			<br/>거래대금: (거래된 주식의 가격*거래량)
			<br/>시가총액: 상장주식을 시가로 평가하여 회사의 규모<br/>&#9;(발행주식수*주가) 
			<br/>52주고가: 1년 동안 최고가
		</div>
		<div class="insertFavorite">
			<div class="inlineDiv">종목추가</div>
			<div class="inlineDiv">
			    <input type="text" name="snameSearh" id="sname"/>
			    <input type="submit" formaction="<%= request.getContextPath()%>/myInterestInsert.do" name="insertBtn" id="insertBtn" value="추가" class="button"/>
		    	<input type="submit" formaction="<%= request.getContextPath()%>/myInterestDelete.do" name="deleteBtn" value="삭제" onclick="return deleteCheck();" class="button"/>
		    	<input type="submit" formaction="<%= request.getContextPath()%>/simulation.do" id="investBtn" value="가상투자하기" onclick="return goToInvest();" class="button"/>
		    	<input type="button" value="?" id="helpBtn2"/>
		    </div>
	    </div>

		<table class="myT">
			<tr>
				<th style="text-align:center"><input type="checkbox" id="allCheck" /></th>
				<th>회사명</th>
				<th>업종</th>
				<th>세부업종</th>
				<th style="text-align:right">현재가</th>
				<th style="text-align:right">전일비</th>
				<th style="text-align:right">등락율</th>
				<th style="text-align:right">거래량</th>
				<th style="text-align:right">거래대금</th>
				<th style="text-align:right">시가총액</th>
				<th style="text-align:right">52주고가</th>
			</tr>
			
				<c:forEach var="list" items="${myInterestList}" varStatus="status">
			    <tr>
						<c:if test="${status.getCount()%5!=0 || status.last}">
							<td align="center" class="interestTd"><input type="checkbox" name="sname" value="${list.name}" class="interestTd"></td>
							<td class="interestTd">${list.name}</td>
							<td class="interestTd">${list.field}</td>
							<td class="interestTd">${list.detail}</td>
							<td align="right" class="interestTd"><fmt:formatNumber value="${list.price}" pattern="#,##0" /></td>
							<td align="right" class="interestTd">${list.dayrate}</td>
							<td align="right" class="interestTd">${list.changerate}</td>
							<td align="right" class="interestTd">${list.volume}</td>
							<td align="right" class="interestTd">${list.dealprice}</td>
							<td align="right" class="interestTd"><fmt:formatNumber value="${list.total}" pattern="#,##0" /></td>
							<td align="right" class="interestTd">${list.high52}</td>
						</c:if>
						<c:if test="${status.getCount()%5==0 && !status.last}">
							<td align="center" class="td5"><input type="checkbox" name="sname" value="${list.name}"></td>
							<td class="td5">${list.name}</td>
							<td class="td5">${list.field}</td>
							<td class="td5">${list.detail}</td>
							<td align="right" class="td5"><fmt:formatNumber value="${list.price}" pattern="#,##0" /></td>
							<td align="right" class="td5">${list.dayrate}</td>
							<td align="right" class="td5">${list.changerate}</td>
							<td align="right" class="td5">${list.volume}</td>
							<td align="right" class="td5">${list.dealprice}</td>
							<td align="right" class="td5"><fmt:formatNumber value="${list.total}" pattern="#,##0" /></td>
							<td align="right" class="td5">${list.high52}</td>
						</c:if>
					</tr>
		    </c:forEach>
		    <c:if test="${empty myInterestList}">
		    	<tr>
		    		<td colspan="100%" class="empty">
		    			<img src="<%= request.getContextPath()%>/img/alert.png" style="max-width:10%;height:auto;"><br/>
		    			관심 종목이 없습니다. 종목을 추가해주세요.
	    			</td>
	    		</tr>
		    </c:if>
		</table>
	</form>
</div>
</body>
</html>