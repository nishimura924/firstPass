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
	
	<c:choose>
		<c:when test="${questionOfSet[0].choicePicFlg == 1 }" >
			<%--選択肢の画像ファイル名なし？ --%>
		</c:when>	
		<c:otherwise><br></c:otherwise>
	</c:choose>
	<br>
	
	<form action="Answer.action" method="post">	
		<input type="radio" name="choice" value=${questionOfSet[0].choice1.isCorrect }>ア：${questionOfSet[0].choice1.choice }<br>
		<input type="radio" name="choice" value=${questionOfSet[0].choice2.isCorrect }>イ：${questionOfSet[0].choice2.choice }<br>
		<input type="radio" name="choice" value=${questionOfSet[0].choice3.isCorrect }>ウ：${questionOfSet[0].choice3.choice }<br>
		<input type="radio" name="choice" value=${questionOfSet[0].choice4.isCorrect }>エ：${questionOfSet[0].choice4.choice }<br><br>
		<br>
		
		<c:choose>
			<c:when test="${questionOfSet[0].bookmarkFlg == 1 }" >ブックマーク登録済<br></c:when>	
			<c:otherwise><br></c:otherwise>
		</c:choose>

		<br>
		<c:if test="${answer == null}">
			<input type="submit" value="回答">
		</c:if>
		</form>
	

	<hr>
	
	<c:if test="${answer != null}">
		<c:choose>
			<c:when test="${answer.correct == 1 }">〇：正解！</c:when>
			<c:otherwise>×：不正解！</c:otherwise>
		</c:choose>
		<br>
		■正しい答え：${answer.correctChar }<br>
		${errorMessage }
		<br>
		
		<form action="Result.action" method="post">
			<c:choose>
				<c:when test="${questionOfSet[0].bookmarkFlg == null }" >
					ブックマーク登録<input type="checkbox" name="bookmark" value="1" >
				</c:when>
				<c:when test="${questionOfSet[0].bookmarkFlg == 1 }" >
					ブックマーク<input type="checkbox" name="bookmark" value="1" checked ><br>
					解除時はチェックを外してください。
				</c:when>
				<c:otherwise>
					ブックマーク登録<input type="checkbox" name="bookmark" value="1" >
				</c:otherwise>
			</c:choose>
			<br><br>
			
			＜コメント登録＞<br>
			<input type="text" name="comment" ><br><br>
			
			
			＜過去のコメント＞<br>
			<c:forEach var="commentPast" items="${answer.allComment }">
				${commentPast.commentDate }　　${commentPast.comment }<br>
			</c:forEach>
			
			<input type="submit" name="submitFinish" value="途中終了">
			<input type="submit" name="submitNext" value="次へ">
		</form>
		
	</c:if>
	
</body>
</html>