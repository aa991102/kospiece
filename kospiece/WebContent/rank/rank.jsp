<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 1.업종별 리스트 추가 -->
<!-- 2.클릭하면 화살표 방향 바뀌는 스크립트 추가 -->
<!-- 3.순위 보여주는 로직 추가 -->

<div class="title">실시간 순위</div>

<div class="rank">
	<div class="field-search">
		업종별
		<form action="rank.do" method="get" name="fieldForm" onChange="javascript:fieldForm.submit();">
			<select name="select">
				<option value="all"selected>전체보기</option>
				<option value="화학" ${field == "화학" ? "selected" :"" }>화학</option>
				<option value="운수장비" ${field == "운수장비" ? "selected" :"" }>운수장비</option>
				<option value="의약품" ${field == "의약품" ? "selected" :"" }>의약품</option>
			</select>
		</form>
	</div>
	<table border="1" width="1000" align="center">
		<tr>
			<th>순위</th>
			<th>업종</th>
			<th>회사명</th>
			<th>
			<form action="rank.do" method="get" name="changeRateForm">
				<a href="javascript:changeRateForm.submit();">등락률</a>
				<input type="hidden" name="column" value="schangerate">
				<c:if test="${type=='schangerate'&&sort=='desc'}">
					<input type="hidden" name="orderBy" value="asc">
					<img src="<%= request.getContextPath()%>/img/down.png">
				</c:if>
				<c:if test="${type=='schangerate'&&sort=='asc'}">
					<input type="hidden" name="orderBy" value="desc">
					<img src="<%= request.getContextPath()%>/img/up.png">
				</c:if>
				<input type="hidden" name="select" value="${field }">
			</form>
			</th>
			<th>
			<form action="rank.do" method="get" name="totalForm">
				<a href="javascript:totalForm.submit();">시가총액</a>
				<input type="hidden" name="column" value="stotal">
				<c:if test="${type=='stotal'&&sort=='desc'}">
					<input type="hidden" name="orderBy" value="asc">
					<img src="<%= request.getContextPath()%>/img/down.png">
				</c:if>
				<c:if test="${type=='stotal'&&sort=='asc'}">
					<input type="hidden" name="orderBy" value="desc">
					<img src="<%= request.getContextPath()%>/img/up.png">
				</c:if>
				<input type="hidden" name="select" value="${field }">
			</form>
			</th>
			<th>현재가</th>
			<th>전일비</th>
			<th>거래량</th>
			<th>거래대금</th>
			<th>52주고가</th>
			<th>가상투자</th>
			<th>관심주식</th>
		</tr>
		<c:set var="rank" value="0"/>
		<c:forEach var="stock" items="${stockList}">
	        <tr>
						<td>${rank=rank+1}</td>
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