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
	
	
	<form action="ChangeAdminStart.action" method="post">
		■変更対象の確認<br>
		管理者権限を変更するユーザIDを入力してください。<br>
		<input type="text" name="userId"><br>
		<br>
		
		現在(変更前)の権限を選んでください。<br>
		<input type="radio" name="adminFlag" value="0" checked >一般ユーザ<br>
		<input type="radio" name="adminFlag" value="1">管理者<br>				
		<br>
		${errorMessage }<br>
	
		<input type="submit" value="次へ">
	</form>

</body>
<%@include file="footer_admin.jsp" %>
</html>