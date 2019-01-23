<%@page import="kr.co.board1.service.BoardService"%>
<%@page import="kr.co.board1.vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.board1.vo.MemberVO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberVO member = (MemberVO)session.getAttribute("member");
	if(member == null){
		pageContext.forward("./login.jsp");
	}
	
	request.setCharacterEncoding("UTF-8");
	String pg = request.getParameter("pg");
	
	BoardService service = BoardService.getInstance();
	
	// Limit로 start 값 계산
	int start = 0;
	
	if(pg == null){
		start = 1;
	}else{
		start = Integer.parseInt(pg);
	}

	start = (start - 1) * 10;	
	
	//페이지번호 계산
	int total = service.getTotal();
	int pageEnd = 0;
	
	if(total % 10 == 0){
		pageEnd = total / 10;
	}else{
		pageEnd = (total / 10) + 1;
	}
	
	// 글 카운터번호 계산
	int count = total - start;
	
	// 페이지 그룹 계산
	int currentPage = Integer.parseInt(pg);
	int currentPageGroup = (int)Math.ceil(currentPage/10.0);
	int groupStart = (currentPageGroup -1) * 10 + 1;
	int groupEnd = currentPageGroup * 10;
	
	if(groupEnd > pageEnd){
		groupEnd = pageEnd;
	}
	
	
	
	ArrayList<BoardVO> list = service.list(start);
	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>글목록</title> 
		<link rel="stylesheet" href="./css/style.css" />
	</head>
	<body>
		<div id="board">
			<h3>글목록</h3>
			<!-- 리스트 -->
			<div class="list">
				<p class="logout"><%= member.getNick()%>님! 반갑습니다. <a href="./proc/logout.jsp">[로그아웃]</a><p>
				<table>
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>글쓴이</td>
						<td>날짜</td>
						<td>조회</td>
					</tr>
					
					<% for(BoardVO vo : list){ %>
					<tr>
						<td><%= count-- %></td>
						<td><a href="./view.jsp?seq=<%= vo.getSeq() %>"><%= vo.getTitle() %></a>&nbsp;[<%= vo.getComment() %>]</td>
						<td><%= vo.getNick() %></td>
						<td><%= vo.getRdate().substring(2, 10)  %></td>
						<td><%= vo.getHit() %></td>
					</tr>
					<% } %>
				</table>
			</div>
			<!-- 페이징 -->
			<nav class="paging">
				<span> 
				
				<% if(groupStart > 1){ %>
				<a href="./list.jsp?pg=<%= groupStart-1 %>" class="prev">이전</a>
				<% } %>
				
				<% for(int current=groupStart; current<=groupEnd; current++) { %>
				<a href="./list.jsp?pg=<%= current %>" class="num"><%= current %></a>
				<% } %>
				
				<% if(groupEnd < pageEnd ) {%>
				<a href="./list.jsp?pg=<%= groupEnd+1 %>" class="next">다음</a>
				<% } %>
				
				</span>
			</nav>
			<a href="./write.jsp" class="btnWrite">글쓰기</a>
		</div>
	</body>

</html>










