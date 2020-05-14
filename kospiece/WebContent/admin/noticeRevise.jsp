<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 1.클릭한 공지사항의 제목과 내용 셋팅하기 -->
<!-- 2.등록누르면 해당 공지사항의 내용 변경하기 -->

<div class="admin-notice">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	
	<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
	<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a><br/>
	<div class="notice-write">
		<p>공지사항 변경</p>
		<form name="notice-write">
			제목:<input type="text" style="width:650px;"/><br/>
			내용:<textarea cols="70" rows="20"></textarea><br/>
			<input type="submit" value="변경"/>
			<input type="reset" value="취소"/>
		</form>
	</div>
</div>