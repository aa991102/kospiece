<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="title">가상투자/주식주문</div>
<a href="<%= request.getContextPath()%>/simulationlist.do" class="user-button">보유 주식</a>
<a href="<%= request.getContextPath()%>/simulation.do" class="notice-button">주식 주문</a>

<div class="sim">
	<div class="sim">
		<div class="sim-search">
   		<form method="POST" action="./simulation.do" class="board-searchbar">
   			<div class="insertFavorite">
		    	종목 검색  <input type="text" name="sname" />
		    	<input type="submit" value="검색하기" class="button" style="margin:0 30px;">
	    	</div>
 			</form>
    </div>
    <div style="display:inline;padding:0 10px;font-size:2rem;font-weight:bold;">업체 상세 정보</div>
   	<c:if test="${empty MyStock}"><p>회사명을 올바르게 입력하세요.</p></c:if>
   	<c:if test="${!empty MyStock}">
    	<table class="myT">
	        <tr>
				<th colspan="2" class="sim-TH2" style="border-top:1px solid #486587;">종목	</th>
				<td colspan="10" class="sim-TH2" style="font-size:2.5rem;text-align:left; padding:10px 50px; border-top:1px solid #486587;">${MyStock.stock.name}</td>
			</tr>
			<tr>
				<th colspan="2" class="sim-TH2" width="16.6%">현재가</th>
				<td colspan="2" width="16.6%">${MyStock.stock.price}</td>
				<th colspan="2" class="sim-TH2" width="16.6%">전일비</th>
				<td colspan="2" width="16.6%">${MyStock.stock.dayrate}</td>
				<th colspan="2" class="sim-TH2" width="16.6%">등락률</th>
				<td colspan="2">${MyStock.stock.changerate}</td>
			</tr>
			<tr>
				<th colspan="2" class="sim-TH2">거래량</th>
				<td colspan="2">${MyStock.stock.volume}</td>
				<th colspan="2" class="sim-TH2">거래대금</th>
				<td colspan="2">${MyStock.stock.dealprice}</td>
				<th colspan="2" class="sim-TH2">52주고가</th>
				<td colspan="2">${MyStock.stock.high52}</td>
			</tr>
			<tr>
				<th colspan="2" class="sim-TH2">시가총액</th>
				<td colspan="2">${MyStock.stock.total}</td>
				<th colspan="2" class="sim-TH2">업종</th>
				<td colspan="2">${MyStock.stock.field}</td>
				<th colspan="2" class="sim-TH2">세부업종</th>
				<td colspan="2">${MyStock.stock.detail}</td>
			</tr>
    		<tr>
    			<td colspan="4" style="border:none;"></td>
	    		<th colspan="2" class="sim-TH2">보유량</th>
	    		<td colspan="2">${MyStock.totalquantity} </td>
	    		<th colspan="2" class="sim-TH2">현재 보유 포인트</th>
	    		<td colspan="2">${MyStock.mdeposit} </td>
    		</tr>
    		<tr>
    			<td colspan="4" class="sim-TH2"></td>
	    		<td colspan="2" class="sim-TH2">거래량</td>
	    		<td colspan="6" class="sim-TH2">
		    		<form method="POST" action="./invest.do">
				    	<input type="number" name="quantity" style="width:300px"/>
					    <input type="hidden" name="sname" value="${MyStock.stock.name}"/>
					    <input type="hidden" name="totalquantity" value="${MyStock.totalquantity}"/>
					    <input type="submit" name="income" value="매수" class="button2" style="margin:0 10px" />
					    <input type="submit" value="매도" class="button"/>
	    			</form>
    			</td>
    		</tr>
    	</table>
   	</c:if>
    <p>${errors}</p>
    </div>
    <div class="side_buttonR">
    	<a href="./simulationlist.do">
    		<input type="button" class="button" value="목록으로" style="cursor:pointer;margin:20px 30px;"/>
   		</a>
    	</div>
   	<div class="scroll_div">
	   <table class="myT">
			<tr>
				<th>일자</th>
				<th>종목</th>
				<th colspan="2">거래량</th>
				<th>거래시 주식가</th>
				<th>거래금액</th>
			</tr>
			<c:if test="${empty historys}">
				<tr><td colspan="100%">거래 내역이 없습니다.</td></tr>
			</c:if>
			
			<c:set var="sum" value="${0}"/>
			<c:forEach var="history" items="${historys}">
				<tr>
					<td width="25%">${history.date}</td>
					<td>${history.sname}</td>
						<c:if test="${history.dmethod=='매수'}">
							<td width="10%" style="color: red">${history.dmethod}</td><td width="10%">${history.siquantity}</td></c:if>
						<c:if test="${history.dmethod=='매도'}">
							<td width="10%" style="color: blue">${history.dmethod}</td><td width="10%">${-history.siquantity}</td></c:if>
					
					<td width="20%">${history.siprice}</td>
						<c:if test="${history.total>0}"><td width="15%">${history.total}</td></c:if>
						<c:if test="${history.total<0}"><td width="15%">${-history.total}</td></c:if>
				</tr>
				<p hidden="hidden">${sum=sum+history.total}</p>
			</c:forEach>
		</table>
	<div class="side_buttonR" style="margin:10px 30px;">총계 : ${sum }</div>
</div>
</div>