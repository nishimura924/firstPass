<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>マイページ</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	
	<body>
		<c:if test="${user==null }">
			<jsp:forward page="access-error.jsp"></jsp:forward>
		</c:if>
		
		<c:if test="${user.adminFlag==1 }">
			<jsp:forward page="admin.jsp"></jsp:forward>
		</c:if>
		
		<h2 class="font_box">マイページ</h2>
		<div class="float_free">
			${user.userName }さんのマイページ<br>
		</div>
		
		<div class="float_menu">
				<nav>
				<ul>
				<li class=”current”><a href="changeUserNameStart.jsp">ユーザ名変更</a></li>
				<li><a href="changePasswordStart.jsp">パスワード変更</a></li>
				<li><a href="../action/PersonalResult.action">過去実績</a></li>
				<li><a href="../action/Logout.action">ログアウト</a></li>
				</ul>
				</nav>
			<%--- <table>
				<tr><td><a href="changeUserNameStart.jsp">ユーザ名変更</a></td>
					<td><a href="changePasswordStart.jsp">パスワード変更</a></td></tr>
				<tr><td><a href="../action/PersonalResult.action">過去実績</a></td>
					<td><a href="../action/Logout.action">ログアウト</a></td></tr>
			</table>--%>
		</div>
		
	</body>
	<%@include file="footer_menu.jsp" %>
</html>