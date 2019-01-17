<%@page import="kr.co.board1.vo.MemberVO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

	MemberVO member = (MemberVO)session.getAttribute("member");

	if(member == null){
		//로그인을 안했으면,
		//response.sendRedirect("./login.jsp");
		pageContext.forward("./login.jsp");
	}else{
		//response.sendRedirect("./list.jsp");	
		pageContext.forward("./list.jsp");
	}
	
%>