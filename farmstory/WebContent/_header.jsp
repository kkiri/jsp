<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>팜스토리</title>
    <link rel="stylesheet" href="/farmstory/css/index.css">
    <link rel="stylesheet" href="/farmstory/css/board.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="/farmstory/js/slider.js"></script>
  </head>
  <body>
    <div id="wrapper">
      <header>
        <a href="/farmstory/index.do" class="logo"><img src="/farmstory/img/logo.png" alt="로고"></a>
        <p>
          <a href="/farmstory/index.do">HOME |</a>
          <c:if test="${member == null}"> 
          <a href="/farmstory/member/login.do">로그인 |</a>
          <a href="/farmstory/member/terms.do">회원가입 |</a>
          </c:if>
          <c:if test="${member != null}">
          <a href="/farmstory/member/logout.do">로그아웃 |</a>
          </c:if>
          <a href="/farmstory/community/qna.do">고객센터</a>
        </p>
        <img src="/farmstory/img/head_txt_img.png" alt="3만원 이상 무료배송">
        <ul class="gnb">
          <li class="${gr=='intro'?'on':''}"><a href="/farmstory/introduction/hello.do?gr=intro">팜스토리소개</a></li>
          <li class="${gr=='market'?'on':''}"><a href="/farmstory/board/list.do?gr=market&cate=market"><img src="/farmstory/img/head_menu_badge.png" />장보기</a></li>
          <li class="${gr=='croptalk'?'on':''}"><a href="/farmstory/board/list.do?gr=croptalk&cate=story">농작물이야기</a></li>
          <li class="${gr=='event'?'on':''}"><a href="/farmstory/board/list.do?gr=event&cate=event">이벤트</a></li>
          <li class="${gr=='community'?'on':''}"><a href="/farmstory/board/list.do?gr=community&cate=notice">커뮤니티</a></li>
        </ul>
      </header>