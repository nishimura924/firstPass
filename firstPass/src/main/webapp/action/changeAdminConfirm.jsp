<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
	<head>
		<meta charset="UTF-8">
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<title>管理者権限の変更</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body>

	<h2>管理者権限の変更確認</h2>
	
	<br>
	<div class="float_adminChange">
		■ユーザID<br>
		
		${userChangeAdmin.userId }<br><br>
		
		■現在の権限<br>
		
		<c:choose>
			<c:when test= "${userChangeAdmin.adminFlag == 0}" >一般ユーザ<br><br></c:when>
			<c:when test= "${userChangeAdmin.adminFlag == 1}" >管理者<br><br></c:when>
			<c:otherwise><br></c:otherwise>
		</c:choose>
		
		■変更後の権限<br>
		
		<c:choose>
			<c:when test= "${userChangeAdmin.adminFlag == 0}" >管理者<br><br></c:when>
			<c:when test= "${userChangeAdmin.adminFlag == 1}" >一般ユーザ<br><br></c:when>
			<c:otherwise><br></c:otherwise>
		</c:choose>
		
		
		<form action="changeAdminStart.jsp" method="post">
			<input class="decorated-btn click-down" type="submit" value="戻る">
		</form>
		
		<br>
		
		<form action="ChangeAdminConfirm.action" method="post">
			<input type="hidden" name="userId" value=${userChangeAdmin.userId }>
			<input type="hidden" name="userAdmin" value=${userAdmin }>	
			<c:choose>
				<c:when test= "${userChangeAdmin.adminFlag == 0}" >
					<input type="hidden" name="newUserAdmin" value="1">	
				</c:when>
				<c:when test= "${userChangeAdmin.adminFlag == 1}" >
					<input type="hidden" name="newUserAdmin" value="0">
				</c:when>
			</c:choose>
			
			<input class="decorated-btn click-down" type="submit" value="変更">
		</form>
	</div>

	</body>
	<%@include file="footer_admin.jsp" %>
</html>