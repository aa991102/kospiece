<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="title">
	<a href="<%= request.getContextPath()%>/admin.do" >관리자 페이지</a>
</div>

<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a>

<div class="boardContent" id="1">
	<div class="boardContent-buttons" id="1-2">
				<a href="<%= request.getContextPath()%>/noticeManage.do">
				<input type="button" value="목록" class="button"></a>
	</div>
	<div class="" id="1-3">
		<table width="100%">
			<tr>
				<td class="board-title">${notice.nno}. ${notice.title}</td>
			</tr>
			<tr>
				<td class="board-info">
					<a class="board-info-nick">관리자</a>
					 | <a class="board-info-others">조회</a> 
					<a style="color:#ff5656;font-size:1.4rem;">${notice.hit}</a> | 
					<a class="board-info-others">${notice.uploadDate}</a>
				</td>
			</tr>
			<tr>
				<td class="board-content">
					<div class="board-content-div">
						${notice.content}
					</div>
				</td>
			</tr>
		</table>
	</div>

</div>