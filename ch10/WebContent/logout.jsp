<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>
</head>
<body>
	<h3>로그아웃 처리</h3>
	<%
		session.invalidate();  // invalidate = 무효화
		response.sendRedirect("./login.jsp");
	
	%>
</body>
</html>