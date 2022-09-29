<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録完了</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<c:if test="${user == null}">
	<jsp:forward page="registUserError.jsp" />
</c:if>
<c:if test="${userId == null}">
	<jsp:forward page="access-error.jsp" />
</c:if>
<c:if test="${userName == null}">
	<jsp:forward page="access-error.jsp" />
</c:if>

	<h2 class="font_box">新規登録完了</h2>
		<div class="float_finish">
			<p>以下の内容で登録しました。</p>

			<form action="RegistUserFinish.action" method="post">

				<p>ログイン名</p> 
				${userId }
				<p>パスワード</p>
				非表示
				<p>ユーザ名</p>
				${userName }

			</form>
		</div>
<%@include file="footer_menu.jsp" %>

</body>
</html>
