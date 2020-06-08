<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 방문자수 그래프를 위한 구글차트 사용 -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
$( document ).ready(function() {

	google.charts.load('current', {packages: ['corechart', 'line']});
	google.charts.setOnLoadCallback(drawCurveTypes);

	function drawCurveTypes() {
    var data = new google.visualization.DataTable();
    
    data.addColumn('string', '날짜');
    data.addColumn('number', '방문자 수');

    <c:forEach items="${visitor}" var="visitor">
 			data.addRow(['${visitor.term}',${visitor.sum}])
 		</c:forEach>
   
    var options = {
      hAxis: {
        title: '기간'
      },
      vAxis: {
        title: '방문자 수'
      },
      series: {
        1: {curveType: 'function'}
      }
    };
    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
    chart.draw(data, options);
  	}
	});
</script>

<!-- 관리자 메인화면과 회원관리,공지사항으로 연결되는 링크 -->
<div class="title">
	<a href="<%= request.getContextPath()%>/admin.do" >관리자 페이지</a>
</div>

<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a>

<div class="admin" id="admin">
	
	<!-- 오늘통계와 전체통계 -->
	<div class="statistics">
		<div class="stats">
			<div class="left-stat">
				<img src="<%= request.getContextPath()%>/img/people.png"><br/>
				방문자 수
			</div>
			<div class="right-stat">
				today<br/>
				<span class="today">${sessionScope.todayCount}</span><br/>
				/${sessionScope.totalCount}명
			</div>
		</div>
		<div class="stats">
			<div class="left-stat">
				<img src="<%= request.getContextPath()%>/img/plus.png"><br/>
				회원 수
			</div>
			<div class="right-stat">
				today<br/>
				<span class="today">${stat.todayMember}</span><br/>
				/${stat.totalMember}명
			</div>
		</div>
		<div class="stats">
			<div class="left-stat">
				<img src="<%= request.getContextPath()%>/img/post.png"><br/>
				게시글 수
			</div>
			<div class="right-stat">
				today<br/>
				<span class="today">${stat.todayPost}</span><br/>
				/${stat.totalPost}개
			</div>
		</div>
	</div>
	
	<!-- 기간별 방문자 추이 그래프 -->
	<div class="visitor-graph">
		방문자 통계 그래프
		<form name="visitForm" action="admin.do" method="post"
			onChange="javascript:visitForm.submit();">
		  <select id="term" name="term">
		  	<option value="week"<c:if test="${term == 'week'}">selected='selected'</c:if>>일주일</option>
		  	<option value="month"<c:if test="${term == 'month'}">selected='selected'</c:if>>한달</option>
		  	<option value="year"<c:if test="${term == 'year'}">selected='selected'</c:if>>일년</option>
		  </select>
	  </form>
	  <!-- 기간별 방문자 차트  -->
	  <div id="chart_div"></div>
  </div>
  
</div>

