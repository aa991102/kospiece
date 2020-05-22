<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="title">비밀번호변경</div>

<script src="<%= request.getContextPath()%>/js/formchk.js">
</script>
<div class="findPw">

	<form name="FindUpdatePw" onsubmit="formChk();" action="<%= request.getContextPath()%>/findpw.do" method="post" name="FindUpdatePw.jsp">
		<table style="margin:0 auto;">
		<input type="hidden" name="mid" value="${id}" />    
			<tr>
				<th style="border-top:none;">비밀번호</th>
				<td><input type="password" name="mpw" id="mpw" value="${member.pw }"/></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="pw2" id="mpw2"/></td>
			</tr>
		</table>
		${ errors.emptys } 
		${ errors.notm }
		<div class="infoBtn-wrapper">
     	<input type="submit" class="infoBtn" value=" 변경"/>
     	<input type="reset" class="infoBtn" value="취소"/>
   	</div>
	</form>
</div>