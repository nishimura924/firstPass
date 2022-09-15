<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<p>新規登録開始</p>
<p>以下を入力してください。</p>

<form action="RegistUserStart.action" method="post">

ユーザID<input type="text" name="userId">
<br>
パスワード<input type="password" name="userPass">
<br>
パスワード（確認用）<input type="password" name="passwordConfirm">
<br>
ユーザ名<input type="text" name="userName">
<br>

<p><input type = "submit" value="次へ"></p>
<p>${errorMessage }</p>
</form>
</body>
</html>
