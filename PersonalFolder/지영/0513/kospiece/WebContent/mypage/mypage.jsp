<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<body>
<div class="title">마이페이지</div>

<div class="my">
	<div class="TH" style=" cursor: pointer;" onclick="location.href='/kospiece/myInterest.do';" >
		<a>관심주식 더보기</a>
	</div>
	<table class="myT">
		<tr>
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
</div>

<div class="my" >
	<div class="TH" style=" cursor: pointer;" onclick="location.href='/kospiece/simulationlist.do';" >
		<a>가상투자 더보기</a>
	</div>
	<table class="myT" >
		<tr>
			<th>종목</th>
            <th>현재가</th>
            <th>전일비</th>
            <th>등락률</th>
            <th>기사총액</th>
            <th>보유량</th>
		</tr>
		<c:forEach var="list" items="${mysumlationList}" end="4" varStatus="status">
	    <tr>
	        <td><a herf="/simulationlist.do">${list.stock.name}</a></td>
	        <td><a herf="/simulationlist.do">${list.stock.price}</a></td>
	        <td><a herf="/simulationlist.do">${list.stock.dayrate}</a></td>
	        <td><a herf="/simulationlist.do">${list.stock.changerate}</a></td>
	        <td><a herf="/simulationlist.do">${list.stock.total}</a></td>
	        <td><a herf="/simulationlist.do">${list.totalquantity}</a></td>
	        <td>
	    </tr>
    </c:forEach>
	</table>
</div>

<div class="my" >
	<div class="TH" style=" cursor: pointer;" onclick="location.href='myPost.jsp';" >
		<a>내 게시글 더 보기</a>
	</div>
	<table class="myT" >
		<tr>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
</div>

<div class="myInfo" style=" cursor: pointer;" onclick="location.href='/kospiece/myInfo.do';" >
	<a href="/kospiece/myInfo.do">내 정보 확인</a>
</div>

</body>
</html>