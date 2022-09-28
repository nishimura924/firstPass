<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>パスワード変更</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

	<h2 class="font_box">パスワードの変更確認</h2>
	<br>
	<div class="float_changeUserPassword">
	パスワードを変更します。よろしいですか？<br>
	※セキュリティ都合上、新しいパスワードは表示しません。<br>
	
	<form action="changePasswordStart.jsp" method="post">
		<input class="decorated-btn click-down" type="submit" value="戻る">
	</form>
	
	<br>
	
	<form action="ChangePasswordConfirm.action" method="post">
		<input type="hidden" name="password" value=${password }>
		<input type="hidden" name="newPassword" value=${newPassword }>
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