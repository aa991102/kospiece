<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="title">가상투자</div>
<div class="rank">
    <table class="rank" border="0">
	    <tr>
	    	<td width="50%" >업체 상세 정보</td>
	    	<td>
	    		<form method="POST" action="./simulation.do">
		    	종목 검색 : <input type="text" name="sname"/>
		    	<input type="submit" value="검색하기">
	   			</form>
	    	</td>
	    </tr>
    </table>
   	<c:if test="${empty MyStock}"><p>회사명을 올바르게 입력하세요.</p></c:if>
   	<c:if test="${!empty MyStock}">
    	<table class="rank" border="1">
	        <tr>
				<th colspan="2">종목	</th>
				<td colspan="10">${MyStock.stock.name}</td>
			</tr>
			<tr>
				<th colspan="2" width="16.6%">현재가</th>
				<td colspan="2" width="16.6%">${MyStock.stock.price}</td>
				<th colspan="2" width="16.6%">전일비</th>
				<td colspan="2" width="16.6%">${MyStock.stock.dayrate}</td>
				<th colspan="2" width="16.6%">등락률</th>
				<td colspan="2">${MyStock.stock.changerate}</td>
			</tr>
			<tr>
				<th colspan="2">거래량</th>
				<td colspan="2">${MyStock.stock.volume}</td>
				<th colspan="2">거래대금</th>
				<td colspan="2">${MyStock.stock.dealprice}</td>
				<th colspan="2">52주고가</th>
				<td colspan="2">${MyStock.stock.high52}</td>
			</tr>
			<tr>
				<th colspan="2">시가총액</th>
				<td colspan="2">${MyStock.stock.total}</td>
				<th colspan="2">업종</th>
				<td colspan="2">${MyStock.stock.field}</td>
				<th colspan="2">세부업종</th>
				<td colspan="2">${MyStock.stock.detail}</td>
			</tr>
    		<tr>
	    		<th colspan="2">보유량</th>
	    		<td colspan="4">${MyStock.totalquantity} </td>
	    		<th colspan="2">현재 보유 포인트</th>
	    		<td colspan="4">${MyStock.mdeposit} </td>
    		</tr>
    		<tr>
	    		<td colspan="6"></td>
	    		<th colspan="2">거래량</th>
	    		<td colspan="4">
		    		<form metho="POST" action="./invest.do">
				    	<input type="number" name="quantity"/>
					    <input type="hidden" name="sname" value="${MyStock.stock.name}"/>
					    <input type="hidden" name="totalquantity" value="${MyStock.totalquantity}"/>
					    <input type="submit" value="적용"/>
	    			</form>
    			</td>
    		</tr>
    	</table>
   	</c:if>
    <p>${errors}</p>
    <div class="side_buttonR"><a href="./simulationlist.do"><input type="button" value="목록으로 가기" style="cursor:pointer"/></a></div>
   	<div class="scroll_div">
	   <table class="rank" border="1">
			<tr>
				<th>일자</th>
				<th>종목</th>
				<th>거래량</th>
				<th>거래시 주식가</th>
				<th>거래금액</th>
			</tr>
			<c:if test="${empty historys}">
				<tr><td colspan="5">거래 내역이 없습니다.</td></tr>
			</c:if>
			
			<c:set var="sum" value="${0}"/>
			<c:forEach var="history" items="${historys}">
				<tr>
					<td width="25%">${history.date}</td>
					<td>${history.sname}</td>
					<td width="10%">${history.siquantity}</td>
					<td width="20%">${history.siprice}</td>
					<td width="20%">${history.total}</td>
				</tr>
				<p hidden="hidden">${sum=sum+history.total}</p>
			</c:forEach>
		</table>
	<div class="side_buttonR">총계 : ${sum }</div>
	</div>
</div>