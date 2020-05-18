<%@page import="json.data"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.anychart.com/releases/v8/js/anychart-core.min.js"></script>
<script src="https://cdn.anychart.com/releases/v8/js/anychart-treemap.min.js"></script>
<%@ page import="json.data,util.Crawling.*" %>
<!-- 나진 파트 -->

<!-- "추가해야 될 사항" 
1.맵추가(태경님)-클릭했을 때 해당 회사 코드번호 파라미터 넘겨주기
2.회사를 검색하면 해당 회사의 정보 디테일박스에 뜨는 로직 추가 
3.즐겨찾기 로직 연결 (지영님) +js추가(main.js)
4.투자하기 로직 연결 (준님) +js추가(main.js)
5.관심주식 - 해당 회원의 관심주식 리스트에 해당 회사가 존재하는지 확인하는 로직 추가!
 -->
 
 <!-- "필요한 객체"
 1.실시간 주식정보(디테일박스에 나타낼때 필요) - 확인용파라미터:회사코드번호(관심주식 또는 가상투자로 넘어갈때 회사코드번호 넘겨주기)
 2.회원 - 확인용파라미터:회원아이디(관심주식 또는 가상투자로 넘어갈때 회원아이디 넘겨주기)
 3.관심주식
 -->
<script>
$(document).ready(function() { <%CrawlingLoad.Load();%> });
</script>
<div class="main-map">

	<div class="map" id="map">
	<script>
      anychart.onDocumentReady(function() {
		        // create data
		<% data dd = new data(); %>        
        var data = <%=dd.raw()%>

        // create a data tree
        var treeData = anychart.data.tree(eval(data), "as-tree");

        // create a treemap chart visualizing the data tree
        var chart = anychart.treeMap(treeData);
		
        var customColorScale = anychart.scales.ordinalColor();
        customColorScale.ranges([
            {less: -3},
            {from: -3, to: -2},
            {from: -2, to: -1},
            {from: -1, to: 1},
            {from: 1, to: 2},
            {from: 2, to: 3},
            {greater: 3}
        ]);
        
        customColorScale.colors(["#f63538", "#bf4045", "#8b444e" ,"#414554", "#35764e", "#2f9e4f", "#30cc5a"]);
     // set the color scale as the color scale of the chart
        chart.colorScale(customColorScale);

     
        chart.maxDepth(3);
        chart.hintDepth(3);
        chart.maxHeadersHeight("5%");
        // add a color range
        chart.colorRange().enabled(true);
        chart.colorRange().length("30%");
        // add a title for the chart
        chart.title("Kospi 200 Map");

        // specify the container id
        chart.container("map");

        // draw the chart
        chart.draw();

        
      });
    </script>
	</div>
	<div class="map-detail">
		<form name="company-inform" method ="post" class="company-inform">
		    <input type="text" name="company" class="company-name" />
		    <input type="submit" value="검색"/>
		</form>
		<table border="1" width="800" align="center" class="company-detail">
			<tr>
<<<<<<< Updated upstream
				<th colspan="12">회사명 회사코드
=======
				<th colspan="12" id="kk"><b>${info.stockVO.name}</b> 
>>>>>>> Stashed changes
				<!-- 1.로그인했는지체크 2.회원아이디넘기기 *3.회사코드넘기기* -->
				<!-- 즐겨찾기추가는 if문으로 해당 회원이 해당 회사를 즐겨찾기로 갖고있는지 확인 후 별의 색을 결정 & 보유 여부를 파라미터로 넘겨주기 -->
				<img src="img/star.png" onclick="LoginCheck('favoritePlus',${!empty AUTHUSER},'${AUTHUSER.id}')" style="cursor:pointer"/>
				<input type="button" value="투자하기" onclick="LoginCheck('investPlus',${!empty AUTHUSER},'${AUTHUSER.id}')" style="cursor:pointer"/>
				</th>
			</tr>
			<tr>
				<th colspan="2" width="16.6%">전일비</th>
				<td colspan="2" width="16.6%"></td>
				<th colspan="2" width="16.6%">전일비</th>
				<td colspan="2" width="16.6%"></td>
				<th colspan="2" width="16.6%">등락률</th>
				<td colspan="2"></td>
			</tr>
			<tr>
				<th colspan="2">거래량</th>
				<td colspan="2"></td>
				<th colspan="2">거래대금</th>
				<td colspan="2"></td>
				<th colspan="2">52주고가</th>
				<td colspan="2"></td>
			</tr>
			<tr>
				<th colspan="2">시가총액</th>
				<td colspan="2"></td>
				<th colspan="2">업종</th>
				<td colspan="2"></td>
				<th colspan="2">세부업종</th>
				<td colspan="2"></td>
			</tr>
			<tr>
				<th colspan="12">동일 세부업종 현황</th>
			</tr>
			<tr>
				<th colspan="3" width="200">종목</th>
				<th colspan="3" width="200">현재가</th>
				<th colspan="3" width="200">전일비</th>
				<th colspan="3" width="200">등락률</th>
			</tr>
			<tr>
				<td colspan="3">00</td>
				<td colspan="3">00</td>
				<td colspan="3">00</td>
				<td colspan="3">00</td>
			</tr>
		</table>
	</div>
</div>