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

<div class="title">
	<a href="<%= request.getContextPath()%>/admin.do" >관리자 페이지</a>
</div>

<a href="<%= request.getContextPath()%>/userList.do" class="user-button">회원관리</a>
<a href="<%= request.getContextPath()%>/noticeManage.do" class="notice-button">공지사항</a>

<div class="boardModify">
	<p class="center">공지사항 수정</p>
	<form name="notice-write" action="noticeModify.do" method="post" onsubmit="return checkForm();">
		<div class="" id="1-3">
		<table width="100%">
			<tr>
				 <tr>
				 <td align="center">제목</td>
				 <td> <input style="width:95%" type="text" name="title" id="title" value="${notice.title}" /></td>
				 </tr>
				 <tr>
				 <td align="center"> 상세내용</td>
				<td><textarea style="width:95%" rows="15" name="content" id="content">${notice.content}</textarea></td>
			</tr>
		</table>
		</div>
		<div class="boardWrite-Inputbutton" id="1-4">
			<input type="hidden" name="no" value="${no}"/>
			<input type="submit" value="변경" class="button"/>
			<input type="reset" value="취소" class="button2"/>
			
		</div>
	</form>
	
</div>