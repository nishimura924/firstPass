<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
	<head>
		<meta charset="UTF-8">
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<title>管理者権限変更</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body>
	
	
	<h2>管理者権限変更</h2><br><br>
	
	<c:if test="${user.adminFlag != 1}">
		<c:redirect url="index.jsp" />
	</c:if>
	
	<div class="float_adminChange">
		<form action="ChangeAdminStart.action" method="post">
			■変更対象の確認<br>
			管理者権限を変更するユーザIDを入力してください。<br>
			<input type="text" name="userId"><br>
			<br>
			
			現在(変更前)の権限を選んでください。<br>
			<input type="radio" name="adminFlag" value="0" checked >一般ユーザ<br>
			<input type="radio" name="adminFlag" value="1">管理者<br>				
			<br>
			
			<div class="float_errorMsg">
				${errorMessage }<br>
			</div>
		
			<input class="decorated-btn click-down" type="submit" value="次へ">
		</form>
	</div>

</body>
<%@include file="footer_admin.jsp" %>
</html>