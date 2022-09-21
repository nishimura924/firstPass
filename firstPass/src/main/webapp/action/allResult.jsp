<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ランキング</title>
	</head>
	<body>


<form action="AllResult.action" method="post">

<p>分野</p>
		<c:if test ="${genreList==null }">
			<jsp:forward page="AllResultQuestion.action"></jsp:forward>
		</c:if>

<c:forEach var="genre" items="${genreList }">
	<input type="checkbox" name="genre" value="${genre }" checked="checked">${genre }
</c:forEach>
<hr>

<P>難易度</P>
<input type="radio" name="difficulty" value="1" checked>normal＆easy
<input type="radio" name="difficulty" value="0" >normalのみ
<hr>
<P>ユーザ実施期間</P>
FROM<input type="date" name="fromDate"  >
${errorMsgFrom }
<br>
TO<input type="date" name="toDate"  >
${errorMsgTo }
<hr>
<P>ソート方法</P>
<input type="radio" name="sort" value="SUM(COUNT_UNIT)" checked>回答数
<input type="radio" name="sort" value="COUNT(IS_CORRECT)" >正答数
<input type="radio" name="sort" value="collectRate" >正答率

<br>
<hr>
<input type="submit" value="ランキング表示">
</form>

<br>
<c:choose>
	<c:when test="${user.userName == null }">ゲスト</c:when>
	<c:otherwise>${user.userName }</c:otherwise>
</c:choose>
さんの順位は、位です。

<table border="1" width="600" cellspacing="0" cellpadding="5">
	<tr>
	<td>ユーザ名</td>
	<td>回答数</td>
	<td>正答数</td>
	<td>正答率</td>

	<c:forEach var="AllResult" items="${list}">
		<tr>
		<td>${AllResult.userId}</td>
		<td>${AllResult.answerCount}問</td>
		<td>${AllResult.correctCount}問</td>
		<td>${Math.round((AllResult.correctCount/AllResult.answerCount)*100)}％</td>
		</tr>
	</c:forEach>
</table>


	</body>
</html>