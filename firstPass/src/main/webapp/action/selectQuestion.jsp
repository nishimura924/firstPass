<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>出題条件選択</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	
	<body>

		<c:if test="${user!=null && user.adminFlag == 1}">
				<jsp:forward page="access-error.jsp"></jsp:forward>
		</c:if>
		
		<c:if test ="${yearList==null || genreList==null }">
			<jsp:forward page="ShowSelectQuestion.action"></jsp:forward>
		</c:if>
		
		
		<h2 class="font_box">出題条件選択</h2>
		<div class="float_free">
		<c:choose>
			<c:when test="${user == null}">ゲスト</c:when>
			<c:otherwise>${user.userName }</c:otherwise>
		</c:choose>
		
		さん、条件を選んで出題開始してください。<br>
		</div>
		
		<div class="float_free">
		<c:if test ="${user == null }">
			ログインがお済でない方は<a href="login.jsp">こちら</a>
		</c:if>
		</div>
		
		<div class="float_conditions">
			<form action="SelectQuestion.action" method="post">
				<c:if test="${user != null }">
					ブックマークの問題のみ出題する<input type="checkbox" name="bookmark" value="1">
					<hr>
				</c:if>
				
				年度
				<c:forEach var="year" items="${yearList }">
					　<input type="checkbox" name="year" value="${year }" checked="checked">${year }
				</c:forEach>
				<hr>
				
				分野
				<c:forEach var="genre" items="${genreList }">
					　<input type="checkbox" name="genre" value="${genre }" checked="checked">${genre }
				</c:forEach>
				<hr>
				
				難易度
				<input type="radio"	name="difficulty" value="0" checked="checked">Normal
				<input type="radio"	name="difficulty" value="1" >Easy
				<hr>
				
				問題数
				<select name="questionCount">
					<option value="100">100</option>
					<option value="50">50</option>
					<option value="10">10</option>
				</select>
				<hr>
				
				<div class="float_errorMsg">
					<p>${errorMsg }</p>
				</div>
	
				<input class="decorated-btn click-down" type="submit" value="出題開始">
			</form>
		</div>
	
	</body>
	<%@include file="footer_menu.jsp" %>
</html>