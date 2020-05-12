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
				<input type="button" class="btn-del" value="▲"/>
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
				<th>종목명</th>
				<th>현재가</th>
				<th>전일비</th>
				<th>거래량</th>
				<th>거래대금</th>
				<th>등락율</th>
				<th>시가총액</th>
				<th>가상투자</th>
			</tr>
			<tr>
				<td align="center"><input type="checkbox"></td>
				<td>삼성전자</td>
				<td>50,000</td>
				<td>1.3%</td>
				<td>46,000</td>
				<td>50,000</td>
				<td>300</td>
				<td>50,000</td>
				<td><input type="button" value="투자하기"/></td>
			</tr>
			<tr>
				<td align="center"><input type="checkbox"></td>
				<td>삼성전자</td>
				<td>50,000</td>
				<td>1.3%</td>
				<td>46,000</td>
				<td>50,000</td>
				<td>300</td>
				<td>50,000</td>
				<td><input type="button" value="투자하기"/></td>
			</tr>
			<tr>
				<td align="center"><input type="checkbox"></td>
				<td>삼성전자</td>
				<td>50,000</td>
				<td>1.3%</td>
				<td>46,000</td>
				<td>50,000</td>
				<td>300</td>
				<td>50,000</td>
				<td><input type="button" value="투자하기"/></td>
			</tr>
			<tr>
				<td align="center"><input type="checkbox"></td>
				<td>삼성전자</td>
				<td>50,000</td>
				<td>1.3%</td>
				<td>46,000</td>
				<td>50,000</td>
				<td>300</td>
				<td>50,000</td>
				<td><input type="button" value="투자하기"/></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>