<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%@page import="dto.MemberVO" %>

<div class="title">내가 보유한 주식</div>
	
<div class="rank">
   <div>내가 보유한 포인트 : ${member.deposit}</div>
   <div>
       <table class="rank" border="1">
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
	               <form method="post" action="./simulation.do">
		               <input type="hidden" name="sname" value="${list.stock.name}">
		               <input type="hidden" name="totalquantity" value="${list.totalquantity}">
		               <input type="submit" value="투자하기"/>
	               </form>
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
			<c:forEach var="history" items="${historys}">
				<tr>
					<td>${history.date}</td>
					<td>${history.sname}</td>
					<td>${history.siquantity}</td>
					<td>${history.siprice}</td>
					<td>${history.total}</td>
				</tr>
			</c:forEach>
		</table>	
</div>
   