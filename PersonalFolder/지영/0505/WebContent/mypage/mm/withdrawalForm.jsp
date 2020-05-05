<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="title">회원탈퇴</div>
<div class="myDiv">
<form>
	<table class="myDivT">
	    <tr>
	        <th>아이디</th><td>hongid</td>
	    </tr>
	    <tr>
	        <th>비밀번호</th>
	        <td>
	            <input type="password" class="passcheck" id="passcheck"/>
	            <input type="button" class="deletebtn" id="deletebtn" value="✕"/>
	            <input type="button" class="seebtn" id="seebtn" value="보기"/>
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2" align="center">
	            <input type="submit" class="smbtn" id="smbtn" value="탈퇴"/>
	            <input type="button" value="취소"/>
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2">
	            - 비밀번호 확인 후 아이디는 즉시 탈퇴됩니다.<br/>
	            - 탈퇴 후에는 기존의 아이디와 데이터는 복구할 수 없으니 신중하게 선택해주세요.
	        </td>
	    </tr>
	</table>
</form>
</div>
</body>
</html>