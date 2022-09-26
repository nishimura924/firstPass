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
<c:choose>
<c:when test="${difficulty==null }">
<input type="radio" name="difficulty" value="1" checked>normal＆easy
<input type="radio" name="difficulty" value="0" >normalのみ
</c:when>
<c:when test="${difficulty==1 }">
<input type="radio" name="difficulty" value="1" checked>normal＆easy
<input type="radio" name="difficulty" value="0" >normalのみ
</c:when>
<c:when test="${difficulty==0 }">
<input type="radio" name="difficulty" value="1" >normal＆easy
<input type="radio" name="difficulty" value="0" checked>normalのみ
</c:when>
</c:choose>

<hr>

<P>ユーザ実施期間</P>
<c:choose>
<c:when test="${fromDate==null }">
FROM<input type="date" name="fromDate" value="2022-01-01" >
</c:when>
<c:when test="${fromDate!=null }">
FROM<input type="date" name="fromDate" value=${fromDate } >
</c:when>
</c:choose>

<c:choose>
<c:when test="${toDate==null }">
TO<input type="date" name="toDate" value="2100-01-01" >
</c:when>
<c:when test="${toDate!=null }">
TO<input type="date" name="toDate" value=${toDate } >
</c:when>
</c:choose>
<hr>

<c:choose>
<c:when test="${sort==null }">
<P>ソート方法</P>
<input type="radio" name="sort" value="COUNT(COUNT_UNIT)" checked>回答数
<input type="radio" name="sort" value="SUM(IS_CORRECT)" >正答数
<input type="radio" name="sort" value="collectRate" >正答率
</c:when>
<c:when test="${sort.equals('COUNT(COUNT_UNIT)')}">
<P>ソート方法</P>
<input type="radio" name="sort" value="COUNT(COUNT_UNIT)" checked>回答数
<input type="radio" name="sort" value="SUM(IS_CORRECT)" >正答数
<input type="radio" name="sort" value="collectRate" >正答率
</c:when>
<c:when test="${sort.equals('SUM(IS_CORRECT)')  }">
<P>ソート方法</P>
<input type="radio" name="sort" value="COUNT(COUNT_UNIT)" >回答数
<input type="radio" name="sort" value="SUM(IS_CORRECT)" checked>正答数
<input type="radio" name="sort" value="collectRate" >正答率
</c:when>
<c:when test="${sort.equals('collectRate')  }">
<P>ソート方法</P>
<input type="radio" name="sort" value="COUNT(COUNT_UNIT)" >回答数
<input type="radio" name="sort" value="SUM(IS_CORRECT)" >正答数
<input type="radio" name="sort" value="collectRate" checked>正答率
</c:when>
</c:choose>

<br>
<hr>
<input type="submit" value="ランキング表示">
</form>

<br>

<table border="1" width="600" cellspacing="0" cellpadding="5">
	<tr>
	<td>順位</td>
	<td>ユーザ名</td>
	<td>回答数</td>
	<td>正答数</td>
	<td>正答率</td>
	
	<c:forEach var="AllResult" items="${list}" varStatus="status">
		</tr>
		<td>${AllResult.rank}位</td>
		<td>${AllResult.userId}</td>
		<td>${AllResult.answerCount}問</td>
		<td>${AllResult.correctCount}問</td>
		<td>${Math.round(AllResult.correctRate)}％</td>
	</c:forEach>
</table>


	</body>
	<%@include file="footer_menu.jsp" %>
</html>