<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script>
    	$(function(){
    		$('#infochangebtn').click(function(){
    			$(location).attr('href','myInfoUpdateForm.jsp');
    		})
    		$('#passchangebtn').click(function(){
    			$(location).attr('href','passwordUpdateForm.jsp');
    		})
    		$('#withdrawalbtn').click(function(){
    			$(location).attr('href','withdrawalForm.jsp');
    		})
    	})
    </script>
    <style>
    	.menubar {display:inline; float:left;}
    	.content {width: 100px;
    				 	  margin: 0 auto;}
    </style>
</head>
<body>

<jsp:include page="../header.jsp"/>


<table class="menubar" border="1">
	<tr>
		<td><a href="#">내 정보</a></td>
	</tr>
	<tr>
		<td><a href="passwordUpdateForm.jsp">비밀번호변경</a></td>
	</tr>
	<tr>
		<td><a href="withdrawalForm.jsp">회원탈퇴</a></td>
	</tr>
</table>


<form>
    <table class="content" border="1">
        <tr>
            <th colspan="100%">내 정보</th>
        </tr>
        <tr>
            <td>아이디</td>
            <td></td>
        </tr>
        <tr>
            <td>닉네임</td>
            <td></td>
        </tr>
        <tr>
            <td>이름</td>
            <td></td>
        </tr>
        <tr>
            <td>연락처</td>
            <td></td>
        </tr>
        <tr>
            <td>이메일</td>
            <td></td>
        </tr>
        <tr>
            <td>주소</td>
            <td></td>
        </tr>
        <tr>
        <th colspan="100%">
            <input type="button" class="infochangebtn" id="infochangebtn" value="내 정보 변경"/>
        </th>
    </tr>
    </table>
</form>
</body>
</html>