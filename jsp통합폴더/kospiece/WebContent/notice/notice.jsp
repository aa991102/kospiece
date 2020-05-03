<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="title">공지사항</div>

<div class="notice">
	<form name="notice-search" method ="post" class="notice-search">
	    <select name="search">
  	        <option value="total" selected>전체보기</option>
	        <option value="title">제목</option>
	        <option value="contents">내용</option> 
	    </select>
	    <input type="text" name="user-inform" />
	    <input type="submit" value="검색"/>
	</form>
	<table border="1" width="1000" align="center">
        <tr>
        	<th>글번호</th>
        	<th>제목</th>
        	<th>작성일</th>
        	<th>조회</th>
        </tr>
        <tr>
			<td>1</td>
			<td></td>
			<td></td>
			<td></td>
        </tr>
     </table>
</div>