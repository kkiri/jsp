<%@page import="org.json.simple.JSONObject"%>
<%@page import="jdk.nashorn.api.scripting.JSObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//데이터베이스 연결 및 쿼리실행
	final String HOST="jdbc:mysql://192.168.0.126:3306/bmj"; //변수 이름이 대문자 -> 상수[고정된 값이다.] 
	final String USER="bmj";
	final String PASS="1234";
	
	// 파라미터 수신
	request.setCharacterEncoding("UTF-8");
	String nick = request.getParameter("nick");
	
	// 1단계
	Class.forName("com.mysql.jdbc.Driver");
	
	// 2단계
	Connection conn = DriverManager.getConnection(HOST, USER, PASS);
	
	// 3단계
	Statement stmt = conn.createStatement();
	
	// 4단계
	ResultSet rs = stmt.executeQuery("SELECT count(*) FROM `JSP_MEMBER` WHERE nick='"+nick+"'");
	
	// 5단계
	int count = 0;
	if(rs.next()){
		count = rs.getInt(1);
	}
	
	// 6단계
	rs.close();
	stmt.close();
	conn.close();

	// JSON 데이터 생성 및 출력
	JSONObject json = new JSONObject();
	json.put("result", count);
	out.print(json);
%>