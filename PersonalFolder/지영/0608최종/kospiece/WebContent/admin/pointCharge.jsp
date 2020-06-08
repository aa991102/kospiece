<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
function checkForm(nick,point) {
	
	charge=Number(document.getElementById("point").value)
	point=Number(point)
	
    if(document.getElementById("point").value==""){
    	alert("충전하실 포인트를 입력해주세요")
    	return false;
    }
	
	if(isNaN(charge)){
		alert("숫자로만 정확하게 입력해주세요")
		return false;
	}
	
    alert(nick+"님에게 "+charge+"포인트 충전하여 "+(point+charge)+"포인트가 되었습니다.")

}
</script>

<div class="title">
	<a href="<%= request.getContextPath()%>/admin.do" >관리자 페이지</a>
</div>

<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a>

<div class="admin-user">
	
	<!-- 포인트 충전 시 회원아이디와 보유포인트 나타냄 -->
	<div class="point-box">
		<form name="point-form" method="post" action="pointCharge.do" 
			onsubmit="return checkForm('${userNick}','${point}');">
			<p class="pointCharge-msg">포인트 충전</p>
			<p>${userNick}님의 보유 포인트 : ${point}P</p>
			<p><input type="text" id="point" name="point"/>
				P 충전<br/></p>
			<input type="hidden" name="nick" value="${userNick}"/>
			<p><input type="submit" value="충전" class="pointSubmit"/></p>
		</form>
	</div>
</div>