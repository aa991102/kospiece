<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="join">
		<form action="<%= request.getContextPath()%>/find.do" method="post">
			<table>
				<tr>
					<th>사용자 이름 : </th>
					<td><input type="text" name="mname"/></td>
				</tr>
				<tr>
					<th>e-mail : </th>
					<td><input type="text" name="mmail"/></td>
				</tr>
				<tr>
					<th>아 이 디 : </th>
					<td><input type="text" name="mid"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="인증"/>
						<input type="reset" value="취소"/>
					</td>
					
				</tr>
				
			</table>
				<c:if test="${!empty result}">
					${result}
				</c:if>
		</form>
	</div>
</body>
</html>