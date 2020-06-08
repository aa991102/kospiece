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

<div class="title">
	<a href="<%= request.getContextPath()%>/admin.do" >관리자 페이지</a>
</div>

<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a>

<div class="boardWrite">
	
	<div class="boardWrite-Input">
		<p class="center">공지사항 작성</p>
		<div class="boardWrite" id="1">
		<form action="<%=request.getContextPath()%>/noticeWrite.do" method="post"
		onsubmit="return checkForm();">
			<div class="boardWrite-Input" id="1-2">
					<table width="100%">
						<tr>
							<td align="center">제목</td>
							<td><input style="width:95%" type="text" id="title" name="title"/></td>
						</tr>
						<tr>
							<td align="center">상세 내용</td>
							<td><textarea cols="95" rows="15" id="content" name="content" id="content" style="width:95%"></textarea></td>
						</tr>
					</table>
			</div>
			<div class="boardWrite-Inputbutton" id="1-3">
				<input type="submit" value="등록" class="button"/>
				<input type="reset" value="취소" class="button2"/>
			</div>
		</form>
	</div>
</div>