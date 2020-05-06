<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="" id="1">
		<div class="" id="1-1">
			자유게시판
		</div>
		<div class="" id="1-2">
			게시글작성
		</div>
		<form action="" name="write">
		<div class="" id="1-3">
			말머리
			<select name="말머리">
			    <option value="">선택</option>
			    <option value="normal">일반</option>
			    <option value="humer">유머</option>
			    <option value="information">정보</option>
			</select><br>
			제목 <input type="text" name="subject" id=""/><br>
			상세내용 <input type="text" name="detail" id=""/><br>
		</div>
		<div class="" id="1-4">
			<input type="submit" value="등록"/>
			<input type="reset" value="취소"/>
		</div>
		</form>
		
	</div>
</body>
</html>