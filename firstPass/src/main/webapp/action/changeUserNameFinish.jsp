<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>ユーザ名変更</title>
</head>
<body>
	
	
	<c:if test="${user == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>

	ユーザ名の変更
	
	<br>
	
	ユーザ名の変更が完了しました。

</body>
	<c:choose>
		<c:when test="${user.adminFlag == 0}" >
			<%@include file="footer_mypage.jsp" %>	
		</c:when>		
		<c:otherwise>
			<%@include file="footer_admin.jsp" %>	
		</c:otherwise>
	</c:choose>
</html>