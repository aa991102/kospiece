<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<div class="title">로그인</div>

<div class="login">
    <form name="login" id="login" method="post" action="<%= request.getContextPath()%>/login.do">
        <table>
        <tr>
            <tr>
                <th>ID : </th>
                <td><input type="text" id="id" name="id"></td>
            </tr>
            <tr>
                <th>PW : </th>
                <td><input type="password" id="pw" name="pw"></td>
            </tr>
            <tr>
                <th colspan="2">
                    <input type="submit" id="user" value="로그인">
                </th>
            </tr>
        </table>
        <a href="<%= request.getContextPath()%>/join.do">회원가입</a>
        <a href="<%= request.getContextPath()%>/findId.jsp">아이디찾기</a>
        <a href="<%= request.getContextPath()%>/findPw.jsp">비밀번호찾기</a>
    </form>
</div>

