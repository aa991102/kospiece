<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
function checkForm() {
    if(document.getElementById("title").value==""){
    	alert("제목을 작성해주세요")
    	return false;
    }else if(document.getElementById("content").value==""){
    	alert("내용을 작성해주세요")
    	return false;
    }
}
</script>

<div class="admin-notice">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	
	<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
	<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a><br/>
	
	<div class="notice-write">
		<p>공지사항 변경</p>
		
		<!-- 공지사항 변경 폼 (제목과 내용이 표시되어있음) -->
		<form name="notice-write" action="noticeModify.do" method="post">
			제목:<input type="text" style="width:650px;" id="title" name="title" value="${notice.title}"/><br/>
			내용:<textarea cols="70" rows="20" id="content" name="content" >${notice.content}</textarea><br/>
			<input type="hidden" name="no" value="${no}"/>
			<input type="submit" value="변경"/>
			<input type="reset" value="취소"/>
		</form>
	</div>
</div>