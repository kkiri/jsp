<%@page import="kr.co.board1.vo.MemberVO"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
	// 1단계
	// 2단계
	
	MemberVO member = (MemberVO)session.getAttribute("member");

	request.setCharacterEncoding("UTF-8");
	String title = request.getParameter("subject");
	String content = request.getParameter("content");
	String uid = member.getId();
	String regip = request.getRemoteAddr();
	
	Connection conn = DBConfig.getConnection();
	
	// 3단계
	PreparedStatement psmt = conn.prepareStatement(SQL.INSERT_BOARD);
	psmt.setString(1, title);
	psmt.setString(2, content);
	psmt.setString(3, uid);
	psmt.setString(4, regip);
	
	// 4단계
	psmt.executeUpdate();
	// 5단계
	// 6단계
	
	psmt.close();
	conn.close();
	
	response.sendRedirect("../list.jsp");	
%>