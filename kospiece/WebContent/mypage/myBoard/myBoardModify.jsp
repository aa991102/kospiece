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
</script>
<div class="title">내 게시글</div>
	<div class="" id="1">
		
		<div class="" id="1-2">
			게시글수정
		</div>
		<form action="<%=request.getContextPath() %>/myBoardModify.do?fno=${param.fno}" method="post"
		onsubmit="return checkForm();">
		<div class="" id="1-3">
			말머리
			<select name="horsehead" value="${board.horsehead }">
			    <option value="일반">선택</option>
			    <option value="일반">일반</option>
			    <option value="유머">유머</option>
			    <option value="정보">정보</option>
			</select><br>
			제목 <input type="text" name="title" id="title" value="${board.title}"/><br>
			상세내용 <textarea name="content" rows="5" cols="30" id="content">${board.content}</textarea>
		</div>
		<div class="" id="1-4">
			<input type="submit" value="등록"/>
			<input type="reset" value="취소"/>
		</div>
		</form>
</div>
