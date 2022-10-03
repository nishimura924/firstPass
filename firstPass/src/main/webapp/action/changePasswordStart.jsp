<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>パスワード変更開始</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	
	
	<c:if test="${user.userName == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
	<h2 class="font_box">パスワードの変更</h2>
	<br>
	<div class="float_changeUserPassword">
	
	<form action="ChangePasswordStart.action" method="post">
		■現在のパスワード<br>
		現在のパスワードを入力してください。<br>
		<input type="password" name="password"><br>
		<br>
		■新しいパスワード<br>
		新しいパスワードを入力してください。<br>
		<input type="password" name="newPassword"><br>
		<br>
		確認のため、新しいパスワードをもう一度入力してください。<br>
		<input type="password" name="newPasswordCheck"><br>
		<br>
		<div class="float_errorMsg">
		${errorMessage }<br>
		</div>
	
		<input class="decorated-btn click-down" type="submit" value="次へ">
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