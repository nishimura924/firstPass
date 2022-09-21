<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
<c:choose>
	<c:when test="${user.userName == null }">ゲスト</c:when>
	<c:otherwise>${user.userName }</c:otherwise>
</c:choose>
<form>
さんの今回のテスト結果（難易度：
<c:choose>
<c:when test ="${conditions.difficulty =='0'}">normal</c:when>
	
<c:when test ="${conditions.difficulty =='1'}">easy</c:when>
</c:choose>
）
実施日：${summary.get[0]. }
<br>
出題数:${questionCount }問
<br>
正答数：${correctCount }問
<br>
正答率：${correctRate}％

<br>
</form>
</body>
</html>


<%@include file="footer_menu.jsp" %>