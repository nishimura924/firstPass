<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<%-- <!DOCTYPE html>--%>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>ユーザガイド</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

	<h2 class="font_box">ユーザガイド</h2>
	
	<h3 class="font_box2">ITパスポート過去問道場とは？</h3>
	<div class="float_userGuide">
	TFC社員向けに、ITパスポート試験勉強をサポートするツールです。
	<br>
	これまでの過去問が年度や分野ごとに回答できるようになっています。
	</div>
	<br>
	<h3 class="font_box2">使い方</h3>
	<div class="float_userGuide">
	トップページの「出題条件選択」を押下
	<br>
	↓
	<br>
	年度、分野、難易度、問題数を選択
	<br>
	↓
	<br>
	「出題開始」のボタンを押下すると、出題がスタートします。
	<br>
	↓
	<br>
	回答画面で選択肢を選択し、「回答」ボタンを押下
	その場で正誤結果を見ることができます。
	<br>
	↓
	<br>
	「次へ」ボタンで次の問題、「途中終了」ボタンで回答を途中で終了できます。
	<br>
	↓
	<br>
	出題問題をすべて回答、または途中終了で、正答結果が表示されます。
	<br>
	<br>
	※難易度
	<br>
	　Normal　・・・　実際の試験と同様、選択肢が４つで出題されます。
	<br>
	　Easy　　・・・　選択肢が２つで出題されます。初心者の方におすすめです。
	</div>
	<br>
	<br>
	<h3 class="font_box2">ユーザ向け機能</h3>
	<div class="float_userGuide">
	本サイトではユーザ登録により以下の機能がご利用いただけます。
	<br>
	　＜コメント機能＞
	<br>
	　　問題ごとにコメントの投稿できる他、他ユーザのコメントを見ることができます。
	<br>
	<br>
	　＜ブックマーク機能＞
	<br>
	　　気になる問題や間違えやすい問題などをブックマーク登録し、出題設定でブックマーク問題を回答することができます。
	<br>
	<br>
	　＜個人の過去実績＞
	<br>
	　　ユーザごとの個人実績を見ることができます。
	<br>
	　　分野ごとの正答率も見ることができ、苦手分野などを確認できます
	<br>
	<br>
	　＜ランキング機能＞
	<br>
	　　ユーザ全体のランキングを見ることができます。
	<br>
	　　※ゲストユーザは閲覧のみ可能
	</div>
	
	
		
</body>
<%@include file="footer_menu.jsp" %>
</html>