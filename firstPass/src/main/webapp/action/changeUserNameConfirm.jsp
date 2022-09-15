<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
 	<c:if test="${user == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
	<c:if test="${newUserName == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
	ユーザ名の変更
	
	<br>
	
	■現在のユーザ名<br>
	
	${user.userName }<br>
	
	■新しいユーザ名<br>
	
	${newUserName }
	
	
	<form action="changeUserNameStart.jsp" method="post">
		<input type="submit" value="戻る">
	</form>
	
	<form action="ChangeUserNameConfirm.action" method="post">
		<input type="hidden" name="newUserName" value=${newUserName }>
		<input type="submit" value="変更">
	</form>

</body>

</html>