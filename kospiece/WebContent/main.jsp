<%@page import="json.data"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.anychart.com/releases/v8/js/anychart-core.min.js"></script>
<script src="https://cdn.anychart.com/releases/v8/js/anychart-treemap.min.js"></script>
<%@ page import="json.*,util.Crawling.*" %>

<script>
/*  크롤링파일실행*/
$(document).ready(function() { <%CrawlingLoad.Load();%> });
</script>
<!-- TREND CHART -->
<c:if test="${!empty chart }">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

  function drawChart() {
    var data = google.visualization.arrayToDataTable([${chart}], true);

    var options = {
 	     legend: 'none',
 	     bar: { groupWidth: '80%' }, // Remove space between bars.
	     candlestick: {
 	            fallingColor: { strokeWidth: 0, fill: '#0400FF' }, // red
 	            risingColor: { strokeWidth: 0, fill: '#FF0400' }   // green
   	     }
 	};

    var chart = new google.visualization.CandlestickChart(document.getElementById('chart_div'));

    chart.draw(data, options);
  }
</script>
</c:if>

<div class="main-map">
<% trans.Data(); %>
	<div class="map" id="map">
	<script>
      anychart.onDocumentReady(function() {
		        // create data
		<% data dd = new data(); %>      
        var data = <%= trans.Data()%>

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
        chart.maxHeadersHeight("30%");
        // add a color range
        chart.colorRange().enabled(true);
        chart.colorRange().length("30%");
        // add a title for the chart
        chart.title("Kospi 200 Map");
        /* adjust the font size of labels
        according to the size of tiles */
        chart.labels().adjustFontSize(true);
        chart.labels().fontColor("white")
     // configure the font of headers
        chart.normal().headers().fontColor("#000000");
        chart.normal().headers().adjustFontSize(true);
        chart.normal().headers().fontWeight('bold');
        chart.hovered().headers().fontColor("#990000");
     // enable HTML for labels
        chart.labels().useHtml(true);

        // configure labels
        chart.labels().format(
          "<span style='font-weight:bold'>{%name}</span><br>{%value}"
        );

        // configure tooltips
        chart.tooltip().format(
          "등락률: {%value}\%\n시가총액(억): {%size}"
        );
        // specify the container id
        chart.container("map");
        chart.headersDisplayMode("clip");
        
        //온클릭구현!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        chart.listen("pointClick", function(e) {
        	var name = e.point.get("name");
        	if(name!="kospi200")
        		{
        		document.getElementById("sname").value=name;
        		$(submit).trigger( "click" );
        		}
        });

/*         state.get */
        // draw the chart
        chart.draw();

        
      });
   
      /* document.getElementById('kk').innerHTML = state.get("name"); */
    </script>
	</div>
	
<!-- 로그인 했는지 검사하는 T/F 변수 선언 -->
<input type="hidden" id="login" value="${!empty AUTHUSER}">
	
	<div class="map-detail">
		<form id = "company-inform",name="company-inform" method ="post" class="company-inform" action="./search.do">
		    <input type="text" id = "sname" name="sname"/>
		    <input id="submit" type="submit" value="검색" class="button" style="vertical-align:top;"/>
		</form>
		<table width="800" align="center" class="myT">
			<tr>
				<th colspan="12"><b style="font-size:2.5rem">${info.stockVO.name}</b> 
				<!-- 1.로그인했는지체크 2.회원아이디넘기기 *3.회사코드넘기기* -->
		
				<form action="./simulation.do" style="float:right;">
					<input type="hidden" name="sname" value="${info.stockVO.name}"/>
					<input type="submit" value="투자하기" class="button"/>
				</form>
				
				<!-- 즐겨찾기추가는 if문으로 해당 회원이 해당 회사를 즐겨찾기로 갖고있는지 확인 후 별의 색을 결정 & 보유 여부를 파라미터로 넘겨주기 -->
		
				<!-- 로그인상태로 빈 하얀 별모양을 누르면 해당 회사가 관심주식으로 추가된다  -->
				<!-- 로그인 안한 상태에서는 로그인페이지로 가도록 alert 띄움(js로 구현) -->
				<c:if test="${info.stockVO.interest==0||info.stockVO.interest==null}">
					<form name="interestPlus" id="interestPlus" style="float:right;margin:5px;"
						method="post" action="interest.do" >
						<input type="hidden" name="interest" value="plus">
						<input type="hidden" name="form" value="main">
						<input type="hidden" name="sname" value="${info.stockVO.name}">
						<input type="hidden" name="sno" value="${info.stockVO.no}">
						<input type="image" class=star-img name="PlusImg" 
							src="<%= request.getContextPath()%>/img/star.png" style="cursor:pointer">
					</form>
				</c:if>
				
				<!-- 로그인상태로 노란 별모양을 누르면 해당 회사가 관심주식에서 삭제된다  -->
				<!-- 로그인 안한 상태에서는 로그인페이지로 가도록 alert 띄움(js로 구현) -->
				<c:if test="${info.stockVO.interest>0}">
					<form name="interestDelete" id="interestDelete"
							method="post" action="interest.do" style="float:right;margin:5px;">
						<input type="hidden" name="interest" value="delete">
						<input type="hidden" name="form" value="main">
						<input type="hidden" name="sname" value="${info.stockVO.name}">
						<input type="hidden" name="sno" value="${info.stockVO.no}">
						<input type="image" class="star-img" name="DeleteImg"
							src="<%= request.getContextPath()%>/img/star-click.png" style="cursor:pointer">
					</form>
				</c:if>
				
				</th>
			</tr>
			<tr>
				<th colspan="2" width="10%" class="mainTH">현재가</th>
				<td colspan="2" width="15%">${info.stockVO.price}</td>
				<th colspan="2" width="10%" class="mainTH">전일비</th>
				<td colspan="2" width="15%">${info.stockVO.dayrate}</td>
				<th colspan="2" width="10%" class="mainTH">등락률</th>
				<td colspan="2" width="25%">${info.stockVO.changerate}</td>
			</tr>
			<tr>
				<th colspan="2" class="mainTH">거래량</th>
				<td colspan="2">${info.stockVO.volume}</td>
				<th colspan="2" class="mainTH">거래대금</th>
				<td colspan="2">${info.stockVO.dealprice}</td>
				<th colspan="2" class="mainTH">52주고가</th>
				<td colspan="2">${info.stockVO.high52}</td>
			</tr>
			<tr>
				<th colspan="2" class="mainTH">시가총액</th>
				<td colspan="2" class="mainTH">${info.stockVO.total}</td>
				<th colspan="2" class="mainTH">업종</th>
				<td colspan="2" class="mainTH">${info.stockVO.field}</td>
				<th colspan="2" class="mainTH">세부업종</th>
				<td colspan="2" class="mainTH">${info.stockVO.detail}</td>
			</tr>
			<c:if test="${!empty error }"><tr><th colspan="12">${error}</th></tr></c:if>
			<c:if test="${empty error }">
				<c:if test="${!empty errors}"><tr><td colspan="12">${errors}</td></tr></c:if>
				<c:if test="${empty errors }">
					<tr>
						<th colspan="12" class="mainTH" style="text-align:center;height:50px;">동일 세부업종  현황 </th>
					</tr>
					<tr>
						<th colspan="3" width="200" class="mainTH" style="padding:0 30px;">종목</th>
						<th colspan="3" width="200" class="mainTH" style="text-align:right;">현재가</th>
						<th colspan="3" width="200" class="mainTH" style="text-align:right;">전일비</th>
						<th colspan="3" width="200" class="mainTH" style="text-align:right;padding:0 30px;">등락률</th>
					</tr>
						<c:forEach var="list" items="${info.list}">
							<tr>
								<td colspan="3" style="padding:0 30px;">${list.name}</td>
								<td colspan="3" style="text-align:right;">${list.price}</td>
								<td colspan="3" style="text-align:right;">${list.dayrate}</td>
								<td colspan="3" style="text-align:right;padding:0 30px;">${list.changerate}%</td>
							</tr>
						</c:forEach>
				</c:if>
			</c:if>
		</table>
	</div>
	<div style="margin:0 auto;padding:0 0 0 30px;">
		<div class="rank" id="chart_div" style="width: 1000px; height: 400px;"></div>
	</div>
</div>