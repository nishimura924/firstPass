<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ランキング</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body>
<h2 class="font_box">ランキング</h2>

<div class="float_conditions">
	<form action="AllResult.action" method="post">
	
	<p>分野</p>
			<c:if test ="${genreList==null }">
				<jsp:forward page="AllResultQuestion.action"></jsp:forward>
			</c:if>
	
			<c:choose>
				<c:when test="${list == null }" >
					<c:forEach var="genre" items="${genreList }">
						<input type="checkbox" name="genreCheck" value="${genre }" checked="checked">${genre }
					</c:forEach>
				</c:when>
				<c:otherwise>
					<% boolean isChecked = false;  %>
			 		<c:forEach var="genre" items="${genreList }">
			 			<% isChecked = false;  %>
						<c:forEach var="genreCheck" items="${genreCheck }">
							<c:if test="${genre == genreCheck }">
								<input type="checkbox" name="genreCheck" value="${genre }" checked="checked">${genre }
								<% isChecked = true; %>
							</c:if>
						</c:forEach>
						<% if (! isChecked) {%>
							<input type="checkbox" name="genreCheck" value="${genre }" >${genre }
						<% } %>	 
					</c:forEach>
				</c:otherwise>
				
			</c:choose>
			
			<input type="hidden" name="genreList" value="${genreList }">
			
			<br>
			<div class="float_errorMsg">
			${errorMsg}
			</div>
	<hr>
	
	<p>難易度</p>
	<c:choose>
	<c:when test="${difficulty==null }">
	<input type="radio" name="difficulty" value="1" checked>Normal＆Easy
	<input type="radio" name="difficulty" value="0" >Normalのみ
	</c:when>
	<c:when test="${difficulty==1 }">
	<input type="radio" name="difficulty" value="1" checked>Normal＆Easy
	<input type="radio" name="difficulty" value="0" >Normalのみ
	</c:when>
	<c:when test="${difficulty==0 }">
	<input type="radio" name="difficulty" value="1" >Normal＆Easy
	<input type="radio" name="difficulty" value="0" checked>Normalのみ
	</c:when>
	</c:choose>
	
	<hr>
	
	<p>ユーザ実施期間</p>
	<c:choose>
	<c:when test="${fromDate == null }">
	From <input type="date" name="fromDate" value="2022-01-01" >
	</c:when>
	<c:when test="${fromDate != null }">
	From <input type="date" name="fromDate" value=${fromDate } >
	</c:when>
	</c:choose>
	
	<div class="float_errorMsg">
	${errorMsgFrom}
	</div>
	
	<c:choose>
	<c:when test="${toDate == null }">
	To 　 <input type="date" name="toDate" value="2100-01-01" >
	</c:when>
	<c:when test="${toDate != null  }">
	To 　 <input type="date" name="toDate" value=${toDate } >
	</c:when>
	</c:choose>
	
	<div class="float_errorMsg">
	${errorMsgTo}
	</div>
	<hr>
	
	
	<c:choose>
	<c:when test="${sort==null }">
	<p>ソート方法</p>
	<input type="radio" name="sort" value="COUNT(COUNT_UNIT)" checked>回答数
	<input type="radio" name="sort" value="SUM(IS_CORRECT)" >正答数
	<input type="radio" name="sort" value="collectRate" >正答率
	</c:when>
	<c:when test="${sort.equals('COUNT(COUNT_UNIT)')}">
	<p>ソート方法</p>
	<input type="radio" name="sort" value="COUNT(COUNT_UNIT)" checked>回答数
	<input type="radio" name="sort" value="SUM(IS_CORRECT)" >正答数
	<input type="radio" name="sort" value="collectRate" >正答率
	</c:when>
	<c:when test="${sort.equals('SUM(IS_CORRECT)')  }">
	<p>ソート方法</p>
	<input type="radio" name="sort" value="COUNT(COUNT_UNIT)" >回答数
	<input type="radio" name="sort" value="SUM(IS_CORRECT)" checked>正答数
	<input type="radio" name="sort" value="collectRate" >正答率
	</c:when>
	<c:when test="${sort.equals('collectRate')  }">
	<p>ソート方法</p>
	<input type="radio" name="sort" value="COUNT(COUNT_UNIT)" >回答数
	<input type="radio" name="sort" value="SUM(IS_CORRECT)" >正答数
	<input type="radio" name="sort" value="collectRate" checked>正答率
	</c:when>
	</c:choose>
	
	<br>
	<hr>
	<input class="decorated-btn click-down" type="submit" value="ランキング表示">
	</form>
</div>

<div class="float_ranking">
	<table class="common-table col4t">
		<thead>
		<tr>
			<th>順位</th>
			<th>ユーザ名</th>
			<th>回答数</th>
			<th>正答数</th>
			<th>正答率</th>
		</tr>
		<c:forEach var="AllResult" items="${list}" varStatus="status">
			<tr>
			<td>${AllResult.rank}位</td>
			<td>${AllResult.userId}</td>
			<td>${AllResult.answerCount}問</td>
			<td>${AllResult.correctCount}問</td>
			<td>${Math.round(AllResult.correctRate)}％</td>
			</tr>
		</c:forEach>
		</thead>
	</table>
</div>

</body>
<%@include file="footer_menu.jsp" %>
</html>