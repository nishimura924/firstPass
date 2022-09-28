<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>

<%-- <!DOCTYPE html>--%>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録開始</title>
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>

	<c:if test="${user != null}">
		<jsp:forward page="registUserError.jsp" />
	</c:if>

	<h2 class="font_box">新規登録開始</h2>
	<div class="float_registUser">

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
		<p><input class="decorated-btn click-down"　type = "submit" value="次へ"></p>
		
		<div class="float_float_errorMsg">
			<p>${errorMessage }</p>
		</div>
		</form>
	</div>

<%@include file="footer_menu.jsp" %>
</body>
</html>
