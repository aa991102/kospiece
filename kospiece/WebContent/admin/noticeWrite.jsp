<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
function checkForm() {
    if(document.getElementById("title").value==""){
    	alert("제목을 입력해주세요")
    	return false;
    }
    if(document.getElementById("content").value==""){
    	alert("내용을 입력해주세요")
    	return false;
    }
}
</script>

<div class="admin-notice">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	
	<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
	<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a><br/>
	
	<div class="notice-write">
		<p>공지사항 작성</p>
		<form name="notice-write" action="<%= request.getContextPath()%>/noticeWrite.do" method="post"
			 onsubmit="return checkForm();">
			제목:<input type="text" style="width:650px;" name="title" id="title"/><br/>
			내용:<textarea cols="70" rows="20" name="content" id="content"></textarea><br/>
			<input type="submit" value="등록"/>
			<input type="reset" value="취소"/>
		</form>
	</div>
</div>