<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<c:if test="${user.userName == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
	パスワードの変更
	
	<br>
	
	パスワードの変更が完了しました。

</body>
<%@include file="footer_menu.jsp" %>
</html>