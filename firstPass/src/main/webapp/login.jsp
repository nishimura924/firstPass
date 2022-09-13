<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン</title>
	</head>
	<body>
		<h1>ログイン</h1>
		<form action="Login.action" method="post">
		<p>ログインID<input type="text" name="userId"></p>
		<p>パスワード<input type="password" name="password"></p>
		
		<p>${errorMsg }</p>
		<p><input type="submit" value="ログイン"></p>
		</form>	
	</body>
	<%@include file="footer_menu.jsp" %>
</html>