<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録開始</title>
</head>
<body>

	<c:if test="${user != null}">
		<jsp:forward page="registUserError.jsp" />
	</c:if>

<p>新規登録開始</p>
<p>以下を入力してください。</p>

<form action="RegistUserStart.action" method="post">

ユーザID（半角英数字）
<br>
<input type="text" name="userId" value=${userId }>
<br>
パスワード（半角英数字、8文字以上）
<br>
<input type="password" name="userPass"value=${userPass }>
<br>
パスワード（確認用）
<br>
<input type="password" name="passwordConfirm"value=${passwordConfirm }>
<br>
ユーザ名（半角・全角英数字、漢字・かな・カナ、記号（！？ー～＿＊※＠））
<br>
<input type="text" name="userName"value=${userName }>
<br>

<p><input type = "submit" value="次へ"></p>
<p>${errorMessage }</p>
</form>

<%@include file="footer_menu.jsp" %>
</body>
</html>
