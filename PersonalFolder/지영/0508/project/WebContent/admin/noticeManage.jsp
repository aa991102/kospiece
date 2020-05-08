<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 1.공지사항 리스트가 나오게 변경 -->
<!-- 2.선택한 제목이나 내용에 맞는 공지사항 리스트가 나오게 변경 -->
<!-- 3.글목록에 페이징 추가 -->

<div class="admin-notice">
	<a href="<%= request.getContextPath()%>/admin.do" class="admin-logo">관리자 페이지</a>	
	<a href="<%= request.getContextPath()%>/admin/userManage.jsp" class="user-button">회원관리</a>
	<a href="<%= request.getContextPath()%>/admin/noticeManage.jsp" class="notice-button">공지사항</a><br/>
	
	<form name="user-search" method ="post" class="notice-search">
	    <select name="search">
  	        <option value="total" selected>전체보기</option>
	        <option value="title">제목</option>
	        <option value="contents">내용</option> 
	    </select>
	    <input type="text" name="user-inform" />
	    <input type="submit" value="검색"/>
	</form>
	<table border="1" width="1000" align="center">
        <tr>
        	<th>글번호</th>
        	<th>제목</th>
        	<th>작성일</th>
        	<th>조회</th>
        	<th>수정</th>
        	<th>삭제</th>
        </tr>
     </table>

</div>