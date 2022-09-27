<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>管理者権限の変更</title>
</head>
<body>

	管理者権限の変更確認<br>
	
	<br>
	
	■ユーザID<br>
	
	${userChangeAdmin.userId }<br><br>
	
	■現在の管理者権限<br>
	
	<c:choose>
		<c:when test= "${userChangeAdmin.adminFlag == 0}" >一般ユーザ<br><br></c:when>
		<c:when test= "${userChangeAdmin.adminFlag == 1}" >管理者<br><br></c:when>
		<c:otherwise><br></c:otherwise>
	</c:choose>
	
	■変更後の管理者権限<br>
	
	<c:choose>
		<c:when test= "${userChangeAdmin.adminFlag == 0}" >管理者<br><br></c:when>
		<c:when test= "${userChangeAdmin.adminFlag == 1}" >一般ユーザ<br><br></c:when>
		<c:otherwise><br></c:otherwise>
	</c:choose>
	
	
	<form action="changeAdminStart.jsp" method="post">
		<input type="submit" value="戻る">
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
		
		<input type="submit" value="変更">
	</form>

</body>
<%@include file="footer_admin.jsp" %>
</html>