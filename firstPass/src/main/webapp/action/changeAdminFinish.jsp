<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
	<head>
		<meta charset="UTF-8">
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<title>管理者権限変更完了</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body>
	
		<h2>管理者権限変更</h2>
		<c:if test="${user.adminFlag != 1}">
			<c:redirect url="index.jsp" />
		</c:if>
	
		<div class="float_finish">
			<br>
			
			管理者権限の変更が完了しました。
		</div>
	
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