<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 1.방문자수 회원수 게시글수 구하는 식 추가하기 -->

<div class="admin">
	<a href="admin.jsp" class="admin-logo">관리자 페이지</a>
	<div class="site-statistics">
		<div class="left-statistics">
			today<br/>
			방문자 수:0명<br/>
			신규 회원 수:0명<br/>
			신규 게시글 수:0개<br/>
		</div>
		<div class="right-statistics">
			total<br/>
			전체 회원 수:0명<br/>
			전체 게시글 수:0개<br/>
		</div>
	</div>
	<div class="managemant-list">
		<a href="userManage.jsp" class="user-management">
		<img src="../img/user.png"><br/>
		회원관리
		</a>
		<a href="noticeManage.jsp" class="posts-management">
		<img src="../img/paper.png"><br/>
		게시글관리
		</a>
	</div>
</div>

