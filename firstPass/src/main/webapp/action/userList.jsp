<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>ユーザ一覧</title>
</head>
<body>

	ユーザ一覧
	
	<hr>
	
	<c:if test="${user.adminFlag != 1}">
		<c:redirect url="index.jsp" />
	</c:if>
	
	ユーザ一覧を出力しますか？<br>
	(パスワードは表示されません)<br><br>
	
	<form action="UserList.action" method="post">
		<p><input type = "submit" value="一覧表示"></p>
	</form>
	
	<hr>
	
	
	<c:if test="${adminUserList != null}">
		＜一般ユーザ＞<br>
		<table>
			<tr><td>ユーザID</td><td>ユーザ名</td></tr>
			
			<c:forEach var="adminUserList" items="${adminUserList }">
				<c:if test="${adminUserList.adminFlag == 0}">
					<tr><td>${adminUserList.userId }</td>
						<td>${adminUserList.userName }</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		
		<br>
		＜管理者ユーザ＞<br>
		<table>
			<tr><td>ユーザID</td><td>ユーザ名</td></tr>
			
			<c:forEach var="adminUserList" items="${adminUserList }">
				<c:if test="${adminUserList.adminFlag == 1}">
					<tr><td>${adminUserList.userId }</td>
						<td>${adminUserList.userName }</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	
	</c:if>

</body>
<%@include file="footer_admin.jsp" %>
</html>