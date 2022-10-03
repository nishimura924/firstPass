<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- <!DOCTYPE html>--%>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="style.css">
<title>ユーザ名変更開始</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	
	
	<c:if test="${user == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
	<h2 class="font_box">ユーザ名の変更</h2>
	<br>
	<div class="float_changeUserName">
	
	■現在のユーザ名<br>
	${user.userName }
	
	<form action="ChangeUserNameStart.action" method="post">
		■新しいユーザ名<br>
		<input type="text" name="newUserName"><br>
		<div class="float_errorMsg">
		${errorMessage }<br>
		</div>
		<input class="decorated-btn click-down" type="submit" value="次へ">
	</form>
	</div>
	<div class="float_free">
	＜注意事項＞<br>
	※既に登録済のユーザ名は設定できません。
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