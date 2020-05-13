<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="title">실시간 순위</div>

<div class="rank">
	<div class="field-search">
		업종별
		<form action="rank.do" method="post" name="fieldForm" onChange="javascript:fieldForm.submit();">
			<select name="select">
				<option value="all"selected>전체보기</option>
				<c:set var="rank" value="0"/>
				<c:forEach var="fieldName" items="${fieldName}">
				<option value="${fieldName}">${fieldName}</option>
				</c:forEach>
			</select>
		</form>
	</div>
	<table border="1" width="1000" align="center">
		<tr>
			<th>순위</th>
			<th>업종</th>
			<th>회사명</th>
			<th>
			<form action="rank.do" method="post" name="changeRateForm">
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
			<form action="rank.do" method="post" name="totalForm">
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
						<td>
						<form name="simulation" id="simulation" method="post" action="simulation.do">
							<input type="hidden" name="sname" value="${stock.name}"/>
							<input type="button" value="투자하기" style="cursor:pointer"
								onclick="simulationClick(${!empty AUTHUSER})">
						</form>
						</td>
						<td>
						<c:if test="${stock.interest==0}">
							<form name="interestPlus" id="interestPlus"
								method="post" action="interest.do">
								<input type="hidden" name="interest" value="plus">
								<input type="hidden" name="sno" value="${stock.no}">
								<img src="<%= request.getContextPath()%>/img/star.png"
									style="cursor:pointer" onclick="interestPlusClick(${!empty AUTHUSER})">
							</form>
						</c:if>
						<c:if test="${stock.interest!=0}">
							<form name="interestDelete" id="interestDelete"
								method="post" action="interest.do">
								<input type="hidden" name="interest" value="delete">
								<input type="hidden" name="sno" value="${stock.no}">
								<img src="<%= request.getContextPath()%>/img/star-click.png"
									style="cursor:pointer" onclick="interestDeleteClick()">
							</form>
						</c:if>
						</td>
						
	        </tr>
     </c:forEach>
	</table>
</div>