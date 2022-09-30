<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
	<head>
		<meta charset="UTF-8">
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<title>出題・解答</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	
	<body>
	
	<c:if test="${questionOfSet == null}">
		<jsp:forward page="access-error.jsp" />
	</c:if>
	
	<c:if test="${user.adminFlag == 1}">
		<jsp:forward page="access-error.jsp" />
	</c:if>

 	<div class="float_questionNumber">
		第　${questionOfSet[0].index}　問<br>
	</div>
	
	<div class="float_questionInfo">
		${questionOfSet[0].year }　問${questionOfSet[0].questionNo }<br>
		${questionOfSet[0].genre }<br>
		<br>
	</div>
	
	<div class="float_question">
		${questionOfSet[0].question }
		<br>
			<c:if test="${questionOfSet[0].questionPic != null }" >
				<img src="../img/${questionOfSet[0].questionPic }" />
			</c:if>
	</div>
	<br><br>
	
	<form action="Answer.action" method="post">
	
	<div class="float_choice1">
	<%-- 	<input type="radio" name="choice" value=${questionOfSet[0].choice1.isCorrect } --%>
		<br>
		<input type="radio" name="choice" value="ア"
			<c:if test="${choice == 'ア' }" > checked </c:if> >ア：
			<c:choose>		
				<c:when test="${questionOfSet[0].choicePicFlg == 1 }" >
					<img src="../img/${questionOfSet[0].choice1.choice}" /><br>
				</c:when>
				<c:otherwise>
					${questionOfSet[0].choice1.choice }<br>
				</c:otherwise>
			</c:choose>
	</div>
	
	<div class="float_choice2">
		<br>
		<input type="radio" name="choice" value="イ"			
			<c:if test="${choice == 'イ' }" > checked </c:if> >イ：
			<c:choose>		
				<c:when test="${questionOfSet[0].choicePicFlg == 1 }" >
					<img src="../img/${questionOfSet[0].choice2.choice}" /><br>
				</c:when>
				<c:otherwise>
					${questionOfSet[0].choice2.choice }<br>
				</c:otherwise>
			</c:choose>
	</div>
	
	<c:choose>
	<c:when test="${conditions.difficulty == 0 }" >
	<div class="float_choice3">		
		<br>
		<input type="radio" name="choice" value="ウ"
			<c:if test="${choice == 'ウ' }" > checked </c:if> >ウ：
			<c:choose>
				<c:when test="${questionOfSet[0].choicePicFlg == 1 }" >
					<img src="../img/${questionOfSet[0].choice3.choice}" /><br>
				</c:when>
				<c:otherwise>
					${questionOfSet[0].choice3.choice }<br>
				</c:otherwise>
			</c:choose>	
	</div>			
	<div class="float_choice4">
		<br>			
		<input type="radio" name="choice" value="エ"
			<c:if test="${choice == 'エ' }" > checked </c:if> >エ：
			<c:choose>
				<c:when test="${questionOfSet[0].choicePicFlg == 1 }" >
					<img src="../img/${questionOfSet[0].choice4.choice}" /><br><br>
				</c:when>
				<c:otherwise>
					${questionOfSet[0].choice4.choice }<br><br>
				</c:otherwise>
			</c:choose>
	</div>				
	</c:when>
	<c:otherwise></c:otherwise>

	</c:choose>

		
		<div class="float_bookmarkFlag">
			<c:choose>
				<c:when test="${questionOfSet[0].bookmarkFlg == 1 }" >ブックマーク登録済<br></c:when>	
				<c:otherwise><br></c:otherwise>
			</c:choose>
		</div>
		<br>
		<div class="float_free">
		<c:if test="${answer == null}">
			<input class="decorated-btn click-down" type="submit" value="回答">
		</c:if>
		</div>
		</form>
		
	

	<hr>
	<c:if test="${answer != null}">
			<c:choose>
				<c:when test="${answer.correct == 1 }">
				<div class="float_judgeCorrect">〇：正解！</div>
				</c:when>
				<c:otherwise>
				<div class="float_judgeIncorrect">×：不正解！</div>
				</c:otherwise>
			</c:choose>
			
		<br>

	
	<div class="float_correct">
		■正しい答え：${answer.correctChar }<br>
		<div class="float_errorMsg">
			<p>${errorMessage }</p>
			<br>
		</div>
	</div>
		

		<form action="Result.action" method="post">
			<div class="float_bookmarkRegist">
				<c:if test="${user != null }" >
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
				</c:if>
			</div>
			<br>

				<div class="float_commentRegist">
				<c:if test="${user != null }" >
					＜コメント登録＞<br>
						（500文字超の場合、501文字目以降は削除されます。また改行も削除されます。）<br>
						<textarea name="comment" ></textarea><br><br>

				<div class="float_commentPast">
					＜過去のコメント＞<br>
					<c:forEach var="commentPast" items="${answer.allComment }">
						${commentPast.commentDate }　　${commentPast.userId }　　${commentPast.comment }<br>
					</c:forEach>
				</div>
				</c:if>
			</div>
			<br>		
			<div class="float_free">
			<c:if test="${user != null }" >
				<input class="decorated-btn click-down" type="submit" name="submitComment" value="コメント登録">
			</c:if>
			<input class="decorated-btn click-down" type="submit" name="submitFinish" value="途中終了">
			<input class="decorated-btn click-down" type="submit" name="submitNext" value="次へ">
			</div>
		</form>
	</c:if>
	
</body>
</html>