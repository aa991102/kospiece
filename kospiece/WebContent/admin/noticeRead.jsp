<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="admin-notice">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	
	<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
	<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a><br/>
	
	<!-- 클릭한 공지사항 상세보기 -->
	<div class="noticeDetail">
		글번호:${notice.nno}<br/>
		제목:${notice.title}<br/>
		상세내용:${notice.content}<br/>
		게시일:${notice.uploadDate}<br/>
		조회수:${notice.hit}<br/>
		<a href="<%= request.getContextPath()%>/noticeManage.do">목록</a>
	</div>

</div>