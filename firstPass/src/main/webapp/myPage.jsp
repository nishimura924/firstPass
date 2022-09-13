<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>マイページ</title>
	</head>
	<body>
		<h1>${user.name }さんのマイページ</h1>
		
		<a href="">ユーザ名変更</a><br>
		<a href="">パスワード変更</a><br>
		<a href="">過去実績</a><br>
		<a href="">ログアウト</a><br>
		
		
	</body>
	<%@include file="footer_menu.jsp" %>
</html>