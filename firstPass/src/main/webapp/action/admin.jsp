<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>管理者メニュ</title>
</head>
<body>

	管理者メニュ
	
	<hr>
	
	<c:if test="${user.adminFlag != 1}">
		<c:redirect url="index.jsp" />
	</c:if>
	
	<table>
		<tr><td><a href="changeUserNameStart.jsp">自身のユーザ名変更</a></td></tr>
		<tr><td><a href="changePasswordStart.jsp">自身のユーザパスワード変更</a></td></tr>
		<tr><td><a href="userList.jsp">ユーザ一覧</a></td></tr>
<%-- 		<tr><td><a href="registQuestionStart.jsp">出題問題追加</a></td></tr> --%>
		<tr><td><a href="changeAdminStart.jsp">管理者権限変更</a></td></tr>
<%--		<tr><td><a href="deleteUserStart.jsp">ユーザ削除</a></td></tr> --%>
	</table>


</body>
<%@include file="footer_menu.jsp" %>
</html>