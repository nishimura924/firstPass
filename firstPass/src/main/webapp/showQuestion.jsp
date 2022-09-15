<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<c:if test="${questionOfSet == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>

 	
	第　${questionOfSet[0].index}　問<br>
	${questionOfSet[0].year }　問${questionOfSet[0].questionNo }<br>
	${questionOfSet[0].genre }<br>
	<br>
	${questionOfSet[0].question }
	<img src="${questionOfSet[0].questionPic}">
	
	<br><br>
	
	<form action="Answer.action" method="post">	
		<input type="radio" name="choice" value=${questionOfSet[0].choice1.isCorrect }>ア：${questionOfSet[0].choice1.choice }<br>
		<input type="radio" name="choice" value=${questionOfSet[0].choice2.isCorrect }>イ：${questionOfSet[0].choice2.choice }<br>
		<input type="radio" name="choice" value=${questionOfSet[0].choice3.isCorrect }>ウ：${questionOfSet[0].choice3.choice }<br>
		<input type="radio" name="choice" value=${questionOfSet[0].choice4.isCorrect }>エ：${questionOfSet[0].choice4.choice }<br><br>
		<input type="submit" value="回答">
	</form>

	<hr>
	
	<c:if test="${answer != null}">
		<c:choose>
			<c:when test="${answer.correct == 1 }">〇：正解！</c:when>
			<c:otherwise>×：不正解！</c:otherwise>
		</c:choose>
		<br>
		■正しい答え：${answer.correctChar }
		
		<form action="Result.action" method="post">	
			<input type="submit" value="次へ">
		</form>
		
	</c:if>
	
</body>
</html>