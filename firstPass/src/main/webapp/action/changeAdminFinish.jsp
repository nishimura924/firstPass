<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>管理者権限変更</title>
</head>
<body>
	
	管理者権限変更<br><br>
	
	<c:if test="${user.adminFlag != 1}">
		<c:redirect url="index.jsp" />
	</c:if>

	
	<br>
	
	管理者権限の変更が完了しました。

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