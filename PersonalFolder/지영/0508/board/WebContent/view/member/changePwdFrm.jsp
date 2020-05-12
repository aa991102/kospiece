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
	<h3>changePwdFrm.jsp(p625)</h3>
	<p>http://localhost/board/changePwd.do</p>
	
	<form name="changePwdFrm" id="changePwdFrm" 
			  action="changePwd.do" method="POST">
		<p>
			*현재암호 : <input type="text" name="curPwd"/><br/>	  
			<c:if test="">
				현재 암호를 입력하세요
			</c:if>
			<c:if test="">
				현재 암호가 일치하지 않습니다
			</c:if>
		</p>
		
		<p>
			*NEW 암호 : <input type="text" name="newPwd"/><br/>	  
			<c:if test="">
				새 암호를 입력하세요
			</c:if>
			<c:if test="">
				현재 암호가 일치하지 않습니다
			</c:if>
		</p>
		
		<input type="submit" value="암호변경" />	  
		<input type="reset"  value="취소" />
	</form>
</body>
</html>













