<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<c:if test="${user == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>

	ユーザ名の変更
	
	<br>
	
	ユーザ名の変更が完了しました。

</body>
<%@include file="footer_menu.jsp" %>
</html>