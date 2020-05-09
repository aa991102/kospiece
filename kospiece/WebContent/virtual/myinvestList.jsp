<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%@page import="dto.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div>내가 보유한 주식</div>
    <div>내가 보유한 포인트 : ${member.deposit}</div>
    <div>
        <table border="1">
            <tr>
                <th>종목</th>
                <th>현재가</th>
                <th>전일비</th>
                <th>등략률</th>
                <th>기사총액</th>
                <th>보유량</th>
                <th></th>
            </tr>
            <c:forEach var="list" items="${mysumlationList}">
            <tr>
                <td>${list.stock.name}</td>
                <td>${list.stock.price}</td>
                <td>${list.stock.dayrate}</td>
                <td>${list.stock.changerate}</td>
                <td>${list.stock.total}</td>
                <td>${list.totalquantity}</td>
                <td>
                <a herf="">
                <form method="post" action="./simulation.do">
                <input type="hidden" name="sname" value="${list.stock.name}">
                <input type="hidden" name="totalquantity" value="${list.totalquantity}">
                <input type="submit" value="들어가기"/>
                </form>
                </a>
                </td>
            </tr>
            </c:forEach>
            <tr>
                <td colspan="7">
                <a herf="#">
                <form method="post" action="./simulation.do">
                <input type="hidden" name="sname"  value="삼성전자">
                <input type="submit" value="추가하기"/>
                </form></a></td>
            </tr>
        </table>
			
    </div>
    <div>총 자산 : ${member.asset}</div>
    
</body>
</html>