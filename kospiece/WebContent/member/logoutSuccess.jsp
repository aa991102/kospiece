<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<c:choose>
	<c:when test="${path=='/kospiece/rank/rank.jsp'}"><jsp:forward page="/rank.do"/></c:when>
	<c:when test="${path=='/kospiece/board/board.jsp'}"><jsp:forward page="/board.do"/></c:when>
	<c:when test="${path=='/kospiece/notice/notice.jsp'}"><jsp:forward page="/noticeList.do"/></c:when>
	<c:when test="${path=='/kospiece/simulationlist.do'}"><jsp:forward page="/simulationlist.do"/></c:when>
	<c:when test="${path=='/kospiece/simulation.do'}"><jsp:forward page="/simulation.do"/></c:when>
	<c:when test="${path=='/kospiece/interest.do'}"><jsp:forward page="/interest.do"/></c:when>
	<c:when test="${path=='/kospiece/myInterest.do'}"><jsp:forward page="/myInterest.do"/></c:when>
	<c:when test="${path=='/kospiece/mypage.do'}"><jsp:forward page="/mypage.do"/></c:when>
	<c:when test="${path=='/kospiece/board/write.do'}"><jsp:forward page="/write.do"/></c:when>
	<c:when test="${path=='/kospiece/mypage/myBoard.jsp'}"><jsp:forward page="/myBoardList.do"/></c:when>
	<c:when test="${path=='/kospiece/mypage/myInfo.jsp'}"><jsp:forward page="/myInfo.do"/></c:when>
	
	<c:when test="${path=='/kospiece/board/board.jsp'}">${path}</c:when>
	<c:when test="${path=='/kospiece/board/board.jsp'}">${path}</c:when>
	<c:when test="${path=='/kospiece/board/board.jsp'}">${path}</c:when>
	<c:when test="${path=='/kospiece/board/board.jsp'}">${path}</c:when>
	<c:when test="${path=='/kospiece/board/board.jsp'}">${path}</c:when>
	<c:otherwise><jsp:forward page="/main.do"/></c:otherwise>
</c:choose>