<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="title">관심주식</div>
<div class="my">
	<form>
		<div class="moveFavorite">
			<div class="inlineDiv">
				<input type="button" class="btn-up" value="▲"/>
				<input type="button" class="btn-down" value="▼"/>
				<input type="button" class="btn-del" value="삭제"/>
			</div>
		</div>
		
		<div class="insertFavorite">
			<div class="inlineDiv">종목추가</div>
			<div class="inlineDiv">
			    <input type="text"/>
			    <input type="button" name="insertBtn" id="insertBtn" value="추가"/>
		    </div>
	    </div>
	    
		<table class="myT">
			<tr>
				<th><input type="checkbox"></th>
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
				<th>가상투자</th>
			</tr>
			
				<c:forEach var="list" items="${myInterestList}" varStatus="status">
			    <tr>
						<td align="center"><input type="checkbox"></td>
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
						<td><input type="button" value="ㄱㄱ"/></td>
					</tr>
		    </c:forEach>
		</table>
	</form>
</div>
</body>
</html>