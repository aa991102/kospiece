<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="title">자유게시판</div>

<div class="boardWrite" id="1">
	<div class="" id="1-1">게시글작성</div>
	<form action="<%=request.getContextPath()%>/board/write.do" method="post">
	<div class="boardWrite-Input" id="1-2">
			<table border=1 width="100%">
				<tr>
					<td width="10%" align="center">말머리</td>
					<td width="90%"><select name="horsehead">
							<option value="일반">선택</option>
							<option value="일반">일반</option>
							<option value="유머">유머</option>
							<option value="정보">정보</option>
					</select></td>
				</tr>
				<tr>
					<td align="center">제목</td>
					<td><input size=50 type="text" name="title" value="${param.title}" /></td>
				</tr>
				<tr>
					<td align="center">상세 내용</td>
					<td><textarea cols="95" rows="10" name="content">${param.content}</textarea></td>
				</tr>
			</table>
	</div>
	<div class="boardWrite-Inputbutton" id="1-3">
		<input type="submit" value="등록" /> <a
			href="<%=request.getContextPath()%>/board.do"><input
			type="button" value="목록으로 가기" /></a>
	</div>
</form>
</div>
</body>
</html>