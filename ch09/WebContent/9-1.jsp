<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>9-1</title>
</head>
<body>
	<h3>1.쿠키</h3>
	<%
		//쿠키생성
		Cookie ck1 = new Cookie("name", "홍길동");
		ck1.setMaxAge(60*3); //3분 [유효기간이 3분이다. 3분안에 전송할 수 있고 그 이후엔 못해]
		
		response.addCookie(ck1);
	%>
	<h4>쿠키 생성해서 클라이언트로 전송완료!</h4>
</body>
</html>