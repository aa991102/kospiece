<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="title">정보수정</div>
<div class="myDiv">
<form>
	<table class="myDivT">
	    <tr>
	        <th>아이디</th>
	        <td>${member.id}</td>
	    </tr>
	    <tr>
	        <th>이름</th>
	        <td>${member.name}</td>
	    </tr>
	    <tr>
	        <th>닉네임</th>
	        <td>
	        	<input type="text" name="changeNickName" class="" value="${user.nickname}"/>
	        	<input type="submit" formaction="/kospiece/.do" value="확인"/>
	        </td>
	    </tr>
	    <tr>
	        <th>이메일</th>
	        <td>
	        	<input type="text" name="changeEmail" class=""/>
	        	<input type="submit" formaction="/kospiece/.do" value="확인"/>
        	</td>
	    </tr>
	    <tr>
	        <th>전화번호</th>
	        <td>
        		<input type="text" name="changePhone" class=""/>
	        	<input type="submit" formaction="/kospiece/.do" value="확인"/>
	        </td>
	    </tr>
	    <tr>
	    	<td colspan="2" align="center">
	    		<input type="submit" formaction="/kospiece/.do" class="" id="" value="변경"/> 
	    		<input type="button" class="" id="" value="취소"/>
	    	</td>
	    </tr>
	</table>
</form>
</div>
</body>
</html>