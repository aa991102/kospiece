<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</script>
<div class="title">자유게시판</div>
<div class="title-sub" >
	<a href="<%= request.getContextPath()%>/main.do">홈</a>
	&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/board.do">자유게시판</a>
	&nbsp;|&nbsp;<a>게시글 작성</a>
</div>
<div class="boardWrite" id="1">
	<form action="<%=request.getContextPath()%>/board/write.do" method="post"
	onsubmit="return checkForm();">
	<div class="boardWrite-Input" id="1-2">
			<table width="100%">
				<tr>
					<td width="12%" align="center">말머리</td>
					<td width="88%"><select name="horsehead">
							<option value="일반">선택</option>
							<option value="일반">일반</option>
							<option value="유머">유머</option>
							<option value="정보">정보</option>
					</select></td>
				</tr>
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
		<input type="submit" value="등록" class="button"/> <a
			href="<%=request.getContextPath()%>/board.do"><input
			type="button" value="목록" class="button"/></a>
	</div>
</form>
</div>