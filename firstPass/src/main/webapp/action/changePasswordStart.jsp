<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>パスワード変更</title>
</head>
<body>
	
	
	<c:if test="${user.userName == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
	パスワードの変更<br>
	
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
		${errorMessage }<br>
	
		<input type="submit" value="次へ">
	</form>
	
	

</body>
<%@include file="footer_mypage.jsp" %>
</html>