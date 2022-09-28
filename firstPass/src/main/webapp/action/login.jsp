<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body>
		<c:if test="${user!=null }">
		<jsp:forward page="login-error.jsp"></jsp:forward>
		</c:if>	
	
	
		ログイン
		<div class="float_login">
		
		<form action="Login.action" method="post">
		<p>ログインID<input type="text" name="userId"></p>
		<p>パスワード<input type="password" name="password"></p>
		
		<p>${errorMsg }</p>
		<p><input type="submit" value="ログイン"></p>
		</form>	
		</div>
		
	</body>
	<%@include file="footer_menu.jsp" %>
</html>