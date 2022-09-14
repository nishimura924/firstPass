<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>マイページ</title>
	</head>
	<body>
		${user.name }さんのマイページ
		
		<a href="changeUserNameStart.jsp">ユーザ名変更</a><br>
		<a href="changePasswordStart.jsp">パスワード変更</a><br>
		<a href="parsonalResult.jsp">過去実績</a><br>
		<a href="../Logout.action">ログアウト</a><br>
		
		
	</body>
	<%@include file="footer_menu.jsp" %>
</html>