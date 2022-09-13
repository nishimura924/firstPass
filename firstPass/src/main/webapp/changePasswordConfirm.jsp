<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

	パスワードの変更確認<br>
	パスワードを変更します。よろしいですか？<br>
	※セキュリティ都合上、新しいパスワードは表示しません。<br>
	
	
	
	<form action="changePasswordStart.jsp" method="post">
		<input type="submit" value="戻る">
	</form>
	
	<form action="changePasswordFinish.jsp" method="post">
		<input type="submit" value="変更">
	</form>

</body>

</html>