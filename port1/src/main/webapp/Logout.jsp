<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style_Top.css" media="all">
<title>ログアウト</title>
</head>
<body>
<%
	session.invalidate();
%>
	<p>ログアウトしました。</p>
	<p><a href="Login.jsp">ログイン</a></p>
</body>
</html>
