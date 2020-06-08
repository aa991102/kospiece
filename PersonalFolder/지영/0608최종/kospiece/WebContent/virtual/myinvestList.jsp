<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%@page import="dto.MemberVO" %>

<div class="title">보유 주식</div>
<a href="<%= request.getContextPath()%>/simulationlist.do" class="user-button">보유 주식</a>
<a href="<%= request.getContextPath()%>/simulation.do" class="notice-button">주식 주문</a>

<div class="rank">
   <div style="padding:0 10px;">보유 포인트 : ${member.deposit}</div>
   <div>
       <table class="myT">
           <tr>
               <th>종목</th>
               <th>현재가</th>
               <th>전일비</th>
               <th>등락률</th>
               <th>시가총액</th>
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
		               <input type="submit" value="투자하기" class="button" />
	               </form>
               </td>
           </tr>
           </c:forEach>
           <c:if test="${empty mysumlationList}">
	           <tr>
	           	<td colspan="100%" class="center2">보유 주식이 없습니다.</td>
	         	 </tr>
         	 </c:if>
           <tr>
               <td colspan="7">
               <a herf="#">
               <form method="post" action="./simulation.do">
               <input type="hidden" name="sname"  value="삼성전자">
               <input type="submit" value="추가하기" class="button" style="float:right;margin:0 103px"/>
               </form></a></td>
           </tr>
       </table>
		
   </div>
   <div style="padding:0 10px;">총 자산 : ${member.asset}</div>
   <div>
 		<table class="myT">
			<tr>
				<th>일자</th>
				<th>종목</th>
				<th>거래량</th>
				<th>거래시 주식가</th>
				<th>거래금액</th>
			</tr>
			<c:if test="${empty historys}">
				<tr><td colspan="100%" class="center2">거래 내역이 없습니다.</td></tr>
			</c:if>
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
		<div class="side_buttonR" style="padding:0 10px;">총계 : ${sum }</div>	
	</div>
</div>
   