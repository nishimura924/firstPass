<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録確認</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<c:if test="${user != null}">
	<jsp:forward page="registUserError.jsp" />
</c:if>
<c:if test="${userId == null}">
	<jsp:forward page="access-error.jsp" />
</c:if>
<c:if test="${userName == null}">
	<jsp:forward page="access-error.jsp" />
</c:if>

	<h2 class="font_box">新規登録確認</h2>
		<div class="float_registUser">
			<p>以下の内容でよろしいでしょうか。</p>

			<form>
				<p>ログイン名:${userId }</p>
				<p>パスワード:非表示</p>
				<p>ユーザ名:${userName }</p>
			</form>


			<form action="RegistUserConfirm.action" method="post">
				<input type="hidden" name="userId" value=${userId }>
				<input type="hidden" name="userPass" value=${userPass }>
				<input type="hidden" name="userName" value=${userName }>
				<input type="hidden" name="adminFlag" value="0">

				<p><input type = "submit" value="登録"></p>
				<p>${registError }</p>
			</form>

			<form action="registUserStart.jsp" method="post">
				<input type="submit" value="戻る">
			</form>
		</div>

<%@include file="footer_menu.jsp" %>

</body>
</html>
