<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 1.해당 공지사항의 상세내용 출력하는 로직 -->

<div class="title">공지사항</div>

<div class="noticeDetail">
	글번호:${notice.nno}<br/>
	제목:${notice.title}<br/>
	상세내용:${notice.content}<br/>
	게시일:${notice.uploadDate}<br/>
	조회수:${notice.hit}<br/>
	<a href="<%= request.getContextPath()%>/noticeList.do">목록</a>
</div>

