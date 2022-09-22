<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>パスワード変更</title>
</head>
<body>
	
	
	<c:if test="${user.userName == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
	パスワードの変更
	
	<br>
	
	パスワードの変更が完了しました。

</body>
<%@include file="footer_mypage.jsp" %>
</html>