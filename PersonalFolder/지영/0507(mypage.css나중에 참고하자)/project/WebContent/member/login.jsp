<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<div class="login">
    <form name="login" id="login" method="post" action="/login.do">
        <table>
        <tr>
            <tr>
                <th>ID : </th>
                <th><input type="text" id="id" name="userid"></th>
            </tr>
                <th>PW : </th>
                <th><input type="password" id="pw" name="password"></th>
            <tr>
                <td colspan="2">
                    <input type="submit" id="user" value="로그인">
                </td>
            </tr>
        </table>
        <a href="join.jsp">회원가입</a>
        <a href="findId.jsp">아이디찾기</a>
        <a href="findPw.jsp">비밀번호찾기</a>
    </form>
</div>

