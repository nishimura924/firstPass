<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ユーザ一覧</title>
	</head>
	<body>
		<P>難易度</P>
		<input type="checkbox" name="userList" value="user" >一般ユーザ
		<input type="checkbox" name="userList" value="admin" >管理者
		<br>
		<input type="submit" value="検索">
		
		<table border="1" width="600" cellspacing="0" cellpadding="5">
		<tr>
		<td></td>
		<td>ユーザID</td>
		<td>ユーザ名</td>
		<td>管理者</td>
		<c:forEach var="user" items="${list}">
		<tr>
		<td>${user.id}</td>
		<td>${user.userId}</td>
		<td>${user.userName}</td>
		<td>${user.adminFlag}</td>
		</tr>
</c:forEach>
</table>
		
		
	</body>
</html>