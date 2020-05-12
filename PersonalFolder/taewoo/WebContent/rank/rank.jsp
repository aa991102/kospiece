<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 1.업종별 리스트 추가 -->
<!-- 2.클릭하면 화살표 방향 바뀌는 스크립트 추가 -->
<!-- 3.순위 보여주는 로직 추가 -->

<div class="title">실시간 순위</div>

<div class="rank">
	<div class="field-search">
		업종별
		<select>
			<option>전체보기</option>
			<option>화학</option>
			<option>전자전기</option>
		</select>
	</div>
	<table border="1" width="1000" align="center">
		<tr>
			<th>순위</th>
			<th>업종</th>
			<th>회사명</th>
			<th>등락률<img src="<%= request.getContextPath()%>/img/down.png"></th>
			<th>시가총액<img src="<%= request.getContextPath()%>/img/down.png"></th>
			<th>현재가</th>
			<th>전일비</th>
			<th>거래량</th>
			<th>거래대금</th>
			<th>52주고가</th>
			<th>가상투자</th>
			<th>관심주식</th>
		</tr>
		<c:forEach var="stock" items="${stockList}">
	        <tr>
						<td>순위</td>
						<td>${stock.field}</td>
						<td>${stock.name}</td>
						<td>${stock.changerate}</td>
						<td>${stock.total}</td>
						<td>${stock.price}</td>
						<td>${stock.dayrate}</td>
						<td>${stock.volume}</td>
						<td>${stock.dealprice}</td>
						<td>${stock.high52}</td>
						<td>가상투자</td>
						<td>관심주식</td>
	        </tr>
     </c:forEach>
	</table>
</div>