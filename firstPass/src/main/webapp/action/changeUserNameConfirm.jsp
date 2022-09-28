<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>ユーザ名変更</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	
	
 	<c:if test="${user == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
	<c:if test="${newUserName == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
	ユーザ名の変更
	<div class="float_changeUserName">
	<br>
	
	■現在のユーザ名<br>
	
	${user.userName }<br>
	
	■新しいユーザ名<br>
	
	${newUserName }
	
	
	<form action="changeUserNameStart.jsp" method="post">
		<input type="submit" value="戻る">
	</form>
	
	<form action="ChangeUserNameConfirm.action" method="post">
		<input type="hidden" name="userName" value=${userName }>
		<input type="hidden" name="newUserName" value=${newUserName }>
		<input type="submit" value="変更">
	</form>
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