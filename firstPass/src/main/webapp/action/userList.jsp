<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
	<head>
		<meta charset="UTF-8">
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<title>ユーザ一覧</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
<body>

	<h2>ユーザ一覧</h2>
	
	
	<c:if test="${user.adminFlag != 1}">
		<c:redirect url="index.jsp" />
	</c:if>
	
	<div class="float_userListText">
		ユーザ一覧を出力しますか？<br>
		(パスワードは表示されません)<br><br>
	</div>
	
	<form action="UserList.action" method="post">
		<p><input type = "submit" value="一覧表示"></p>
	</form>
	
	<hr>
	
	
	<c:if test="${adminUserList != null}">
	<div class="float_userList">
	＜一般ユーザ＞<br>
		<table class="common-table col4t">
		<thead>
			<tr>
				<th>ユーザID</th>
				<th>ユーザ名</th>
			</tr>
			
			<c:forEach var="adminUserList" items="${adminUserList }">
				<c:if test="${adminUserList.adminFlag == 0}">
					<tr><td>${adminUserList.userId }</td>
						<td>${adminUserList.userName }</td>
					</tr>
				</c:if>
			</c:forEach>
		</thead>
		</table>
	</div>
	
	<br>
	<div class="float_userAdminList">
	＜管理者ユーザ＞<br>
		<table class="common-table col4t">
		<thead>
			<tr>
				<th>ユーザID</th>
				<th>ユーザ名</th>
			</tr>
			
			<c:forEach var="adminUserList" items="${adminUserList }">
				<c:if test="${adminUserList.adminFlag == 1}">
					<tr><td>${adminUserList.userId }</td>
						<td>${adminUserList.userName }</td>
					</tr>
				</c:if>
			</c:forEach>
		</thead>
		</table>
	</div>
	
	</c:if>

</body>
<%@include file="footer_admin.jsp" %>
</html>