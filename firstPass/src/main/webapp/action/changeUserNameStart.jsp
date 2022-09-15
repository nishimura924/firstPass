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
	
	ユーザ名の変更<br>
	
	■現在のユーザ名<br>
	${user.userName }
	
	<form action="ChangeUserNameStart.action" method="post">
		■新しいユーザ名<br>
		<input type="text" name="newUserName"><br>
		${errorMessage }<br>
		<input type="submit" value="次へ">
	</form>
	
	＜注意事項＞<br>
	※既に登録済のユーザ名は設定できません。

</body>

</html>