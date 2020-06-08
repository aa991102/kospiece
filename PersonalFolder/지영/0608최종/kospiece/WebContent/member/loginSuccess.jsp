<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:choose>
	<c:when test="${path=='/kospiece/rank/rank.jsp'}">${path}<jsp:forward page="/rank.do"/></c:when>
	<c:when test="${empty path}"><jsp:forward page="/rank.do"/></c:when>
	<c:when test="${path=='/kospiece/interest.do'}">${path}<jsp:forward page="/interest.do"/></c:when>
	
	<c:when test="${path=='/kospiece/notice/notice.jsp'}">${path}<jsp:forward page="/noticeList.do"/></c:when>
	<c:when test="${path=='/kospiece/notice/noticeDetail.jsp'}"><jsp:forward page="/noticeList.do"/></c:when>
	<c:when test="${path=='/kospiece/board/write.do'}"><jsp:forward page="/board.do"/></c:when>
	
	<c:when test="${path=='/kospiece/board/board.jsp'}">${path}<jsp:forward page="/board.do"/></c:when>
	<c:when test="${path=='/kospiece/comment/write.do'}"><jsp:forward page="/board.do"/></c:when>
	<c:when test="${path=='/kospiece/board/boardContent.jsp'}"><jsp:forward page="/board.do"/></c:when>
	
	<c:when test="${path=='/kospiece/simulationlist.do'}">${path}<jsp:forward page="/simulationlist.do"/></c:when>
	<c:when test="${path=='/kospiece/simulation.do'}">${path}<jsp:forward page="/simulation.do"/></c:when>
	
	<c:when test="${path=='/kospiece/myInterest.do'}"><jsp:forward page="/myInterest.do"/></c:when>
	<c:when test="${path=='/kospiece/mypage.do'}"><jsp:forward page="/mypage.do"/></c:when>
	<c:when test="${path=='/kospiece/myBoardList.do'}"><jsp:forward page="/myBoardList.do"/></c:when>
	<c:when test="${path=='/kospiece/myInfo.do'}"><jsp:forward page="/myInfo.do"/></c:when>
	
	
	
	<c:when test="${path=='/kospiece/comment/write.do'}"><jsp:forward page="/board.do"/></c:when>
	<c:when test="${path=='/kospiece/comment/write.do'}"><jsp:forward page="/board.do"/></c:when>
	
	
	
	<c:otherwise><jsp:forward page="/main.do"/></c:otherwise>
</c:choose>