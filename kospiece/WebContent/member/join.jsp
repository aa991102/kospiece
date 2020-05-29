<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="title">회원가입</div>

<script src="<%= request.getContextPath()%>/js/formchk.js">
</script>
<div class="join">
	<form name="join" onsubmit="formChk(); return false;" action="<%= request.getContextPath()%>/join.do" method="post" name="join">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="mid" id="mid" value="${member.id}"/></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="mpw" id="mpw" value="${member.pw }"/></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="pw2" id="mpw2"/></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="mname" value="${member.name }"/></td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td><input type="text" name="mnick" value="${member.nickname }"/></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="mmail" value="${member.mail }"/></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="mphone" value="${member.phone}"/></td>
			</tr>
			<tr>
				<td colspan="3">
				<input type="submit" value="가입"/>
				<input type="reset" value="취소"/>			
			</tr>
			<c:if test="${!empty errors }"></c:if>
			<tr>
				<c:choose>
					<c:when test="${!empty errors.id }"><td colspan="2">${errors.id}</td></c:when>
					<c:when test="${!empty errors.nick }"><td colspan="2">${errors.nick}</td></c:when>
					<c:when test="${!empty errors.mmail }"><td colspan="2">${errors.mmail}</td></c:when>
					<c:when test="${!empty errors.mphone }"><td colspan="2">${errors.mphone}</td></c:when>
					<c:when test="${!empty errors.emptys }"><td colspan="2">${errors.emptys}</td></c:when>
				</c:choose>
			</tr>
		</table>
	</form>
</div>