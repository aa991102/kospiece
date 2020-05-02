<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

회원가입

<form id="join" action="/join.do" method="post" name="join">
아이디<input type="text" name="id" id="id"><br/>
비밀번호<input type="password" name="pw" id="pw"><br/>
비밀번호확인<input type="password" name="pw2" id="pw2"><br/>
이름<input type="text" name="name" id="name"><br/>
닉네임<input type="text" name="nickname" id="nickname"><br/>
이메일<input type="text" name="email1" id="email1">@<input type="text" name="email2" id="email2"><br/>
전화번호<input type="text" name="phone1" id="phone1">-<input type="text" name="phone2" id="phone2">-
<input type="text" name="phone3" id="phone3"><br/>
<input type="submit" value="회원가입"/>

</form>