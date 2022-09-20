<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>マイページ</title>
	</head>
	<body>
		${user.userName }さんのマイページ<br>
		
		<a href="changeUserNameStart.jsp">ユーザ名変更</a><br>
		<a href="changePasswordStart.jsp">パスワード変更</a><br>
		<a href="../action/PersonalResult.action">過去実績</a><br>
		<a href="../action/Logout.action">ログアウト</a><br>

		
		
	</body>
	<%@include file="footer_menu.jsp" %>
</html>