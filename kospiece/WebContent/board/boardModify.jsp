<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	function checkForm() {
		if (document.getElementById("title").value == "") {
			alert("제목을 입력해주세요")
			return false;
		}
		if (document.getElementById("content").value == "") {
			alert("내용을 입력해주세요")
			return false;
		}
	}
	function checkDelete() {
		if (confirm("정말 삭제하시겠습니까??") == true){    //확인
		     document.removefrm.submit();
		 }else{   //취소
		     return false;
		 }
	}
</script>
<div class="title">자유게시판</div>

<div class="boardModify" id="1">
	<div class="" id="1-1">자유게시판</div>
	<div class="" id="1-2">게시글수정</div>
	<form
		action="<%=request.getContextPath() %>/board/modify.do?fno=${param.fno}"
		method="post" onsubmit="return checkForm();">
		<div class="" id="1-3">
		<table border=1 width="100%">
		<tr>
		<td width="20%" align="center">말머리</td>
		 <td width="80%"><select name="horsehead" value="${board.horsehead }">
				<option value="일반">선택</option>
				<option value="일반">일반</option>
				<option value="유머">유머</option>
				<option value="정보">정보</option>
			</select></td>
			 <tr>
			 <td align="center">제목</td>
			 <td> <input type="text" name="title" id="title" value="${board.title}" /></td>
			 </tr>
			 <tr>
			 <td align="center"> 상세내용</td>
			<td><textarea cols="90" rows="10" name="content" id="content">${board.content}</textarea></td>
			</tr></table>
		</div>
		<div class="" id="1-4">
			<input type="submit" value="수정" /> <a
				href="<%= request.getContextPath()%>/board/delete.do?fno=${param.fno}">
				<input type="button" value="삭제" id="delete"
				onclick="return checkDelete();">
			</a> 
			<a href="<%= request.getContextPath()%>/board/read.do?pageNo=${param.pageNo}&fno=${param.fno}"><input type="button" value="취소" /></a>
		</div>
	</form>
</div>
