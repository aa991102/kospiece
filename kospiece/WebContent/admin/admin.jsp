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

<!-- 오늘통계와 전체통계 -->
<div class="admin" id="admin">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>
	<div class="site-statistics">
		<div class="left-statistics">
			today<br/>
			오늘 방문자 수:${sessionScope.todayCount}명<br/>
			신규 회원 수:${stat.todayMember}명<br/>
			신규 게시글 수:${stat.todayPost}개<br/>
		</div>
		<div class="right-statistics">
			total<br/>
			전체 방문자 수:${sessionScope.totalCount}명<br/>
			전체 회원 수:${stat.totalMember}명<br/>
			전체 게시글 수:${stat.totalPost}개<br/>
		</div>
	</div>
	
	<!-- 그래프의 단위기간 고르는 select -->
	<div style="margin:0 auto;">
	<form name="visitForm" action="admin.do" method="post"
		onChange="javascript:visitForm.submit();">
	  <select id="term" name="term">
	  	<option value="week"<c:if test="${term == 'week'}">selected='selected'</c:if>>일주일</option>
	  	<option value="month"<c:if test="${term == 'month'}">selected='selected'</c:if>>한달</option>
	  	<option value="year"<c:if test="${term == 'year'}">selected='selected'</c:if>>일년</option>
	  </select>
	  
	  </form>
	  </div>
	  
	  <!-- 기간별 방문자 차트  -->
	  <div id="chart_div" style="width: 700px; height: 300px; margin:0 auto;"></div>
  
  <!-- 회원관리와 게시글 관리로 이동하는 링크 -->
	<div class="managemant-list">
		<a href="<%= request.getContextPath()%>/userList.do" class="user-management">
		<img src="<%= request.getContextPath()%>/img/user.png"><br/>
		회원관리
		</a>
		<a href="<%= request.getContextPath()%>/noticeManage.do" class="posts-management">
		<img src="<%= request.getContextPath()%>/img/paper.png"><br/>
		게시글관리
		</a>
	</div>
</div>

