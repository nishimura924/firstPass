<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>得点結果・分析</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body>
	<h2 class="font_box">得点結果・分析</h2>
	
	<c:if test="${questionCount == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
<form>
<div class="float_result">
<c:choose>
	<c:when test="${user.userName == null }">ゲスト</c:when>
	<c:otherwise>${user.userName }</c:otherwise>
</c:choose>
さんの今回のテスト結果（難易度：
<c:choose>
<c:when test ="${conditions.difficulty =='0'}">Normal</c:when>
	
<c:when test ="${conditions.difficulty =='1'}">Easy</c:when>

<c:when test ="${conditions.difficulty ==null}">出力エラー</c:when>
</c:choose>
）


	<br>
	出題数：${questionCount }問
	<br>
	正答数：${correctCount }問
	<br>
	正答率：${correctRate}％
	
	<br>
</div>
	<hr>
 	<c:if test="${user != null}">
		<a href="myPage.jsp">マイページへ戻る</a>
	</c:if>
	<a href="index.jsp">トップページへ戻る</a>
</form>
</body>
</html>

