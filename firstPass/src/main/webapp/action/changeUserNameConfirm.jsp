<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>ユーザ名変更確認</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	
	
 	<c:if test="${user == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
	<c:if test="${newUserName == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
	<h2 class="font_box">ユーザ名の変更</h2>
	<div class="float_changeUserName">
	<br>
	
	■現在のユーザ名<br>
	
	${user.userName }<br>
	
	■新しいユーザ名<br>
	
	${newUserName }
	
	
	<form action="changeUserNameStart.jsp" method="post">
		<input class="decorated-btn click-down" type="submit" value="戻る">
	</form>
	
	<form action="ChangeUserNameConfirm.action" method="post">
		<input type="hidden" name="userName" value=${userName }>
		<input type="hidden" name="newUserName" value=${newUserName }>
		<input class="decorated-btn click-down" type="submit" value="変更">
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