<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>個人過去実績</title>
	</head>
	<body>
		「${user.name }」さんの正答率実績<br>
		
		<table>
			<tr>
				<th> </th>
				<th>実施問題数</th>
				<th>難易度</th>
				<th>総合</th>
				<th>ストラテジ系</th>
				<th>テクノロジ系</th>
				<th>マネジメント系</th>			
			</tr>
			<%--DBにデータ入ったら、forEachのコメントアウトを元に戻す --%>
			<%-- <c:forEach var="PersonalResult" items="${list }"> --%>
			<tr>
			
			<td>第${PersonalResult.countUnit }回目</td>
			<td>あ${PersonalResult.totalQuestionCount }</td>
			<td>い${PersonalResult.difficulty }</td>
			<td>う${PersonalResult.collectOnTotal }</td>			
			<td>え${PersonalResult.collectOnStrategy }</td>
			<td>お${PersonalResult.collectOnTech }</td>
			<td>か${PersonalResult.collectOnManage }</td>

			</tr>
		<%-- </c:forEach>--%>
		</table>
	</body>
	<%@include file="footer_menu.jsp" %>
</html>