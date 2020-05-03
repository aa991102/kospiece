<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>KOSPIECE</title>
    <!-- 
    * 4.28수정사항
    1. css 파일 경로에 ContextPath추가(프로젝트 진행 시 server.xml의 수정을 고려하여 css파일 적용할 수 있게 하기 위함)
    request.getContextPath() : 컨텍스트패스를 리턴
    2. style 외부파일 적용 시 type태그 추가
    출처 1 : https://aboooks.tistory.com/147
    출처 2 : https://www.w3schools.com/tags/tag_link.asp
    css파일의 경우 type="text/css"
	js파일의 경우 type="text/javascript"
	xml파일의 경우 type="application/xml"
    -->
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/normalize.css" type="text/css">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/kospiece.css" type="text/css">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/admin.css" type="text/css">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/member.css" type="text/css">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/board.css" type="text/css">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/service.css" type="text/css">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/mypage.css" type="text/css">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/include.css" type="text/css">
    <!--웹 폰트 사용-->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900&display=swap" rel="stylesheet">
    <!-- 자바스크립트 -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="<%= request.getContextPath()%>/js/header_sub.js"></script>
</head>
<body>

<!-- 헤더 -->
<header class="header">

    <!-- LOGO -->
    <h1><a href="<%= request.getContextPath()%>/main.jsp" class="logo" >KOSPIECE</a></h1>

    <!-- 상단메뉴바 -->
    <nav class="nav">
        <!-- 맵, 실시간순위, 자유게시판, 공지사항, 가상투자, 마이페이지 -->
        <ul class="list">
            <li class="list-item">
                <a href="<%= request.getContextPath()%>/main.jsp">맵</a>
            </li>
            <li class="list-item">
                <a href="<%= request.getContextPath()%>/rank/rank.jsp">실시간순위</a>
            </li>
            <li class="list-item">
                <a href="<%= request.getContextPath()%>/board/board.jsp">자유게시판</a>
            </li>
            <li class="list-item">
                <a href="<%= request.getContextPath()%>/notice/notice.jsp">공지사항</a>
            </li>
            <li class="list-item">
                <a class="virtual" id="virtual" href="<%= request.getContextPath()%>/virtual/invest.jsp">가상투자</a>
                <ul class="sub-list-virtual">
                    <li class="sub-item-virtual"><a href="#" class="a-item">보유주식</a></li>
                    <li class="sub-item-virtual"><a href="#" class="a-item">주식주문</a></li>
                </ul>
            </li>
            <li class="list-item">
                <a class="my" id="my" href="<%= request.getContextPath()%>\mypage\mypage.jsp">MYPAGE</a>
                <ul class="sub-list-my">
                    <li class="sub-item-my"><a href="<%= request.getContextPath()%>\mypage\myFavorite" class="a-item">관심주식</a></li>
                    <li class="sub-item-my"><a href="<%= request.getContextPath()%>\mypage\myPost" class="a-item">내 게시글</a></li>
                    <li class="sub-item-my"><a href="<%= request.getContextPath()%>\mypage\myInfo" class="a-item">내 정보 관리</a></li>
                </ul>
            </li>
        </ul>

        <!-- 포인트랭킹,로그인 -->
        <ul class="list2">
            <li class="list-item2">
                <div class="rank">
                    <a class="rankNum">1</a>
                    &nbsp;&nbsp;
                    <a class="rankNick">인생은 한방</a>
                </div>
            </li>
            <li class="list-item_log">
                <a href="<%= request.getContextPath()%>/member/login.jsp" rel="noopener">🚪&nbsp;&nbsp;login</a>
            </li>
        </ul>

    </nav>


</header>
<div class="clear"></div>
<div class="main">
