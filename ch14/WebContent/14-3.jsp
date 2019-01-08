<%@page import="java.util.ArrayList"%>
<%@page import="sub1.USER"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터베이스 정보
	final String HOST="jdbc:mysql://192.168.0.156:3306/bmj"; //변수 이름이 대문자 -> 상수[고정된 값이다.] 
	final String USER="bmj";
	final String PASS="1234";
	
	Connection conn = null;
	ArrayList<USER> list = new ArrayList<>();
	ResultSet rs = null;
	Statement stmt = null;
	
	try{
	// 1단계 - JDBC 드라이버 로드
	Class.forName("com.mysql.jdbc.Driver");
	
	// 2단계 - 데이터베이스 접속
	conn = DriverManager.getConnection(HOST, USER, PASS);
	
	// 3단계 - 쿼리실행 객체 생성
	stmt = conn.createStatement();
	
	// 4단계 - 쿼리실행
	// SELECT 경우 -> excuteQuery() 실행
	// INSERT, UPDATE, DELECT 경우 -> excuteUadate() 실행
	rs = stmt.executeQuery("SELECT * FROM `USER`;");
	
	// 5단계 - 결과셋 처리(SELECT일 경우) [INSERT, UPDATE, DELECT일경우는 결과셋이 없다.]
	
	
	while(rs.next()){	
		// 자바빈 객체 생성
	 
		USER user = new USER();
		
		// 자바빈 객에게 rs 한 행에 대한 정보설정 
		user.setSeq(rs.getInt(1));
		user.setUid(rs.getString(2));
		user.setName(rs.getString(3));
		user.setHp(rs.getString(4));
		user.setAddr(rs.getString(5));
		user.setPos(rs.getString(6));
		user.setDep(rs.getInt(7));
		user.setRdate(rs.getString(8));
		
		list.add(user);
	}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
	// 6단계 - 데이터베이스 종료
	
	conn.close();
	rs.close();
	stmt.close();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14-3</title>
</head>
<body>
	<h3>직원목록</h3>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>아이디</td>
			<td>이름</td>
			<td>휴대폰</td>
			<td>주소</td>
			<td>직급</td>
			<td>부서번호</td>
			<td>등록일</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
		
		<%
			for(USER user : list){
		%>	
		
		<tr>
			<td><%= user.getSeq() %></td>
			<td><%= user.getUid() %></td>
			<td><%= user.getName() %></td>
			<td><%= user.getHp() %></td>
			<td><%= user.getAddr() %></td>
			<td><%= user.getPos() %></td>
			<td><%= user.getDep() %></td>
			<td><%= user.getRdate().substring(2,10) %></td>
			<td><a href="#">수정</a></td>
			<td><a href="#">삭제</a></td>
		</tr>
		<% } %>
	</table>
</body>
</html>