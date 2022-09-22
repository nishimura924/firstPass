<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
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

<p>新規登録完了</p>
<p>以下の内容で登録しました。</p>

<form action="RegistUserFinish.action" method="post">

<p>ログイン名</p> 
${userId }
<p>パスワード</p>
非表示
<p>ユーザ名</p>
${userName }

</form>

<%@include file="footer_menu.jsp" %>

</body>
</html>
