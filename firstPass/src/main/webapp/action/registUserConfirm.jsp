<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<c:if test="${userId == null}">
	<jsp:forward page="access-error.jsp" />
</c:if>
<c:if test="${userName == null}">
	<jsp:forward page="access-error.jsp" />
</c:if>

<p>新規登録確認</p>
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
<a href="registUserStart.jsp">戻る</a>
</form>

</body>
</html>
