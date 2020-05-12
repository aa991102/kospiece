<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- WriteArticleHandler에서 
		 db에 insert 성공시의 해당글번호가 newArticleNo에 넣어주고 아래코드에서 모델로 넘겨주었다
		 request.setAttribute("newArticleNo",newArticleNo); 
--%>
	${ ctxPath = pageContext.request.contextPath; }
	<a href="./index.jsp">HOME</a>
		<hr/>
		<h3>회원가입폼 joinForm.jsp(p600)</h3>
		<p>http://localhost/board/join.do</p>
		
	<h1>글등록 성공</h1>
	<a href="${ctxPath}/article/list.do">글 목록보기</a>
	<a href="${ctxPath}/article/read.do?no=${newArticleNo}">등록한 글 상세내용 조회</a>
</body>
</html>