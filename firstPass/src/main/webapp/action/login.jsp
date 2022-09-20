<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン</title>
	</head>
	<body>
		<c:if test="${user!=null }">
		<jsp:forward page="login-error.jsp"></jsp:forward>
		</c:if>	
	
	
		ログイン
		<form action="Login.action" method="post">
		<p>ログインID<input type="text" name="userId"></p>
		<p>パスワード<input type="password" name="password"></p>
		
		<p>${errorMsg }</p>
		<p><input type="submit" value="ログイン"></p>
		</form>	
	</body>
	<%@include file="footer_menu.jsp" %>
</html>