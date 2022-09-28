<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
	<head>
		<meta charset="UTF-8">
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<title>管理者メニュ</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
<body>

	<h2>管理者メニュ</h2>
	
	
	<c:if test="${user.adminFlag != 1}">
		<c:redirect url="index.jsp" />
	</c:if>
	
	<div class="float_menu">
		<table>
			<tr><td><a href="changeUserNameStart.jsp">自身のユーザ名変更</a></td></tr>
			<tr><td><a href="changePasswordStart.jsp">自身のユーザパスワード変更</a></td></tr>
			<tr><td><a href="userList.jsp">ユーザ一覧</a></td></tr>
	<%-- 		<tr><td><a href="registQuestionStart.jsp">出題問題追加</a></td></tr> --%>
			<tr><td><a href="changeAdminStart.jsp">管理者権限変更</a></td></tr>
	<%--		<tr><td><a href="deleteUserStart.jsp">ユーザ削除</a></td></tr> --%>
			<tr><td><a href="../action/Logout.action">ログアウト</a></td></tr>
	
		</table>
	</div>


</body>
<%@include file="footer_menu.jsp" %>
</html>