<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>3-3</title>
</head>
<body>
	<h3>3.회원가입 처리</h3>
	<%
		String name = request.getParameter("name");
   		String gender = request.getParameter("gender");
   		String[] hobbies = request.getParameterValues("hobby");
   		String addr = request.getParameter("addr");
	%>
	<table border="1">
			<tr>
				<td>이름</td>
				<td><%= name %></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><%= gender %></td>
			</tr>
			<tr>
				<td>취미</td>
				<td>
					<% for(String hobby : hobbies){
						out.print(hobby+",");} %>
					
				</td>
			</tr>
			<tr>
				<td>지역</td>
				<td><%= addr %></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="회원가입" />
				</td>
			</tr>
		</table>
</body>
</html>