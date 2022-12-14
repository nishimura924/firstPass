<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>トップページ</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="float_free">
	こんにちは！
	<c:choose>
		<c:when test="${user == null}">ゲスト さん！</c:when>
		<c:otherwise>${user.userName }　さん！</c:otherwise>
	</c:choose>
	</div>
	
	<div class="indexImg "></div>
	<br>
	<div class="float_menu ">
	<c:choose>
		<%--　一般ユーザ(ログイン済) --%>
		<c:when test="${user.adminFlag == 0}">
					<nav>
					<ul>
					<li class=”current”><a href="myPage.jsp"  >マイページ</a></li>
					<li><a href="selectQuestion.jsp">出題条件選択</a></li>
					<li><a href="allResult.jsp">ランキング</a></li>
					</ul>
					</nav>
			
			<%---<table>
				<tr><td><a href="myPage.jsp" class="btn-square-shadow" class="btn-square-shadow">マイページ</a></td>
					<td><a href="selectQuestion.jsp" class="btn-square-shadow">出題条件設定</a></td>
					<td><a href="allResult.jsp" class="btn-square-shadow">全体ランキング</a></td></tr>
			</table>--%>
		</c:when>
		
		<%--　管理者ユーザ --%>
		<c:when test="${user.adminFlag == 1}">
					<nav>
					<ul>
					<li class=”current”><a href="allResult.jsp" >ランキング</a></li>
					<li><a href="admin.jsp" >管理者メニュ</a></li>
					</ul>
					</nav>
			<%--- <table>
				<tr><td><a href="allResult.jsp" class="btn-square-shadow">ランキング</a></td>
					<td><a href="admin.jsp" class="btn-square-shadow">管理者メニュ</a></td></tr>
			</table>--%>
		</c:when>
		
		<%--ゲストユーザ --%>
		<c:otherwise>
					<nav>
					<ul>
					<li class=”current”><a href="login.jsp" >ログイン</a></li>
					<li><a href="registUserStart.jsp" >新規会員登録</a></li>
					<li><a href="selectQuestion.jsp" >出題条件選択</a></li>
					<li><a href="allResult.jsp" >ランキング</a></li>
					</ul>
					</nav>
			<%-- <table>
				<tr><td><a href="login.jsp" class="btn-square-shadow">ログイン</a></td>
					<td><a href="registUserStart.jsp" class="btn-square-shadow">新規会員登録</a></td>
					<td><a href="selectQuestion.jsp" class="btn-square-shadow">出題条件選択</a></td>
					<td><a href="allResult.jsp" class="btn-square-shadow">ランキング</a><br></td></tr>
			</table>--%>
		</c:otherwise>
	</c:choose>
	</div>
</body>
<%@include file="footer_menu.jsp" %>
</html>