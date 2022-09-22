<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

	<c:if test="${user != null}">
		<jsp:forward page="registUserError.jsp" />
	</c:if>

<p>新規登録開始</p>
<p>以下を入力してください。</p>

<form action="RegistUserStart.action" method="post">

ユーザID<input type="text" name="userId" value=${userId }>
<br>
パスワード<input type="password" name="userPass"value=${userPass }>
<br>
パスワード（確認用）<input type="password" name="passwordConfirm"value=${passwordConfirm }>
<br>
ユーザ名（半角英数字、8文字以上）<input type="text" name="userName"value=${userName }>
<br>

<p><input type = "submit" value="次へ"></p>
<p>${errorMessage }</p>
</form>
</body>
</html>
