<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="title">가상투자</div>
<div class="rank">
    <form method="POST" action="./simulation.do">
    	종목 검색 : <input type="text" name="sname"/>
    	<input type="submit" value="검색하기">
   	</form>
    
    
    <div>업체 상세 정보
    	<c:if test="${empty MyStock}">
    	<p>회사명을 올바르게 입력하세요.</p></c:if>
    	<c:if test="${!empty MyStock}">
	    	<table class="rank" border="1">
	            <tr>
	                <td>종목 : ${MyStock.stock.name}</td>
	                <td>업종 : ${MyStock.stock.field}</td>
	                <td>세부업종 : ${MyStock.stock.detail}</td>
	                <td>현재가 :${MyStock.stock.price}</td>
	                <td>전일비 :${MyStock.stock.dayrate}</td>
	            </tr>
	            <tr>
	                <td>등락률 : ${MyStock.stock.changerate}</td>
	                <td>거래량 : ${MyStock.stock.volume}</td>
	                <td>거래대금(백만) : ${MyStock.stock.dealprice}</td>
	                <td>시가총액(억):${MyStock.stock.total}</td>
	                <td>52주고가 : ${MyStock.stock.high52}</td>
	            </tr>
	    		<tr>
	    		<td colspan="5">보유량 : ${MyStock.totalquantity} </td>
	    		</tr>
	    	</table>
	   		<div>현재 보유 포인트 : ${MyStock.mdeposit}</div>
    	</c:if>
    </div>
    
    <form metho="POST" action="./invest.do">
    	수량 : <input type="number" name="quantity"/>
	    <input type="hidden" name="sname" value="${MyStock.stock.name}"/>
	    <input type="hidden" name="totalquantity" value="${MyStock.totalquantity}"/>
	    <input type="submit" value="적용"/>
    </form>
    	
    	<p>${errors}</p>
   
</div>