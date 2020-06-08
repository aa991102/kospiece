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
<div class="title-sub" >
	<a href="<%= request.getContextPath()%>/main.do">홈</a>
	&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/board.do">자유게시판</a>
	&nbsp;|&nbsp;<a>게시글 수정</a>
</div>
<div class="boardModify" id="1">
	<form
		action="<%=request.getContextPath() %>/board/modify.do?fno=${param.fno}"
		method="post" onsubmit="return checkForm();">
		<div class="" id="1-3">
		<table width="100%">
			<tr>
				<td width="12%" align="center">말머리</td>
			 	<td width="88%"><select name="horsehead" value="${board.horsehead }">
					<option value="일반">선택</option>
					<option value="일반">일반</option>
					<option value="유머">유머</option>
					<option value="정보">정보</option>
				</select>
				</td>
		 	<tr>
			 <td align="center">제목</td>
			 <td> <input style="width:95%" type="text" name="title" id="title" value="${board.title}" /></td>
		 	</tr>
		 	<tr>
		 	 <td align="center"> 상세내용</td>
			 <td><textarea style="width:95%" rows="15" name="content" id="content">${board.content}</textarea></td>
			</tr>
		</table>
		</div>
		<div class="boardWrite-Inputbutton" id="1-4">
			<input type="submit" value="수정" class="button"/> <a
				href="<%= request.getContextPath()%>/board/delete.do?fno=${param.fno}">
				<input type="button" value="삭제" id="delete" class="button2"
				onclick="return checkDelete();">
			</a> 
			<a href="<%= request.getContextPath()%>/board/read.do?pageNo=${param.pageNo}&fno=${param.fno}"><input type="button" value="취소"  class="button"/></a>
		</div>
	</form>
</div>
