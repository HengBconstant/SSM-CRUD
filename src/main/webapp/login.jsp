<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
	<h1>请登录</h1>
	<form action="${APP_PATH }/shiro/login" method="post">
		用户名：<input name="user">
		密码：<input type="password" name="pwd">
		<input type="submit">
	</form>
</body>
</html>