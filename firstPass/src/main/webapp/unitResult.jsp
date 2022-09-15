<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bean.User,java.util.List" %>

<c:choose>
	<c:when test="${user.userName == null}">ゲスト</c:when>
	<c:otherwise>${user.userName }</c:otherwise>
</c:choose>
さんの今回のテスト結果（難易度：${Condition.difficulty}）

実施日：${answerDate}
<br>
出題数:${questionCount}問
<br>
正答数：${correctCount}問
<br>
正答率：${correctRate}％
<br>



<%@include file="../footer.html" %>