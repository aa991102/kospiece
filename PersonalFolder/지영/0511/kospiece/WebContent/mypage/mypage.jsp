<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<body>
<div class="title">마이페이지</div>

<div class="my">
	<div class="TH" style=" cursor: pointer;" onclick="location.href='myInterest.jsp';" >
		<a>관심주식 더보기</a>
	</div>
	<table class="myT">
		<tr>
			<th>번호</th>
			<th>업종</th>
			<th>회사명</th>
			<th>등락률</th>
			<th>시가총액</th>
			<th>현재가</th>
			<th>전일비</th>
		</tr>
		
		<tr>
			<td>1</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>2</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>3</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>4</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>5</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
</div>

<div class="my" >
	<div class="TH" style=" cursor: pointer;" onclick="location.href='/simulationlist.do';" >
		<a>가상투자 더보기</a>
	</div>
	<table class="myT" >
		<tr>
			<th>종목</th>
            <th>현재가</th>
            <th>전일비</th>
            <th>등략률</th>
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
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
		</tr>
		<tr>
			<td>1</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>2</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>3</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>4</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>5</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
</div>

<div class="myInfo" style=" cursor: pointer;" onclick="location.href='/myInfo.do';" >
	<a href="myInfo.jsp">내 정보 확인</a>
</div>

</body>
</html>