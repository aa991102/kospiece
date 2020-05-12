<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가상투자 하자</title>
</head>
<body>
    <form method="POST" action="">종목 검색 : <input type="text" name="sname"/><input type="submit" value="검색하기"></form>
    <div>업체 상세 정보
    
    	<table border="1">
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
    		
    	</table>
    
    
    </div>
    <div>현재 보유 포인트 : ${MyStock.totalquantity}</div>
    <form> 수량 : <input type="number" name="quantity"/>
    <input type="hidden" name="sno" value="${MyStock.stock.no}"/>
    <input type="hidden" name="sprice" value="${MyStock.stock.price}"/>
     <input type="submit" value="적용"/></form>
</body>
</html>