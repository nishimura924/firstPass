<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<p>新規登録完了</p>
<p>以下の内容で登録しました。</p>

<form action="RegistUserFinish.action" method="post">

<p>ログイン名</p> 
${userId }
<p>パスワード</p>
${userPass }
<p>ユーザ名</p>
${userName }

</form>

<%@include file="../footer.html" %>

</body>
</html>
