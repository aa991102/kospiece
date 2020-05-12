<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	//컨트롤러인 LoginHandler에서
	//에러에 대하 정보를 아래와 같이  Model로 넘겼다
	//request.setAttribute("errors",errors);
	
	//p607 44~51
	/*if(id==null||id.isEmpty()){errors.put("id",Boolean.TRUE);}
		if(password==null||password.isEmpty()){errors.put("password",Boolean.TRUE);
	}*/
	
	
	//View에서 사용시에는
	//Map<String,Boolean> errors = request.getAttribute("errors");
	//${errors.키값}
	%>

	<a href="./index.jsp">HOME</a>
	<hr/>
	<h3>loginForm.jsp(p608)</h3>
	<p>http://localhost/board/login.do</p>
	
	<form name="loginFrm" id="loginFrm" 
			  action="login.do" method="POST">
	<c:if test="${errors.idOrPwNotMatch}">
		아이디 또는 비밀번호가 일치하지 않습니다
	</c:if>
	
		<p>
		*아이디 : <br/>
		<input type="text" name="id" id="id" />
		<c:if test="${errors.id}">ID를 입력하세요</c:if>
		</p>
		<p>
		*암 호 : <br/>
		<input type="password" name="password" id="password" />
		<c:if test="${errors.password}">암호를 입력하세요</c:if>
		</p>
		<input type="submit" value="로그인" />	  
		<input type="reset"  value="취소" />
	</form>
</body>
</html>













