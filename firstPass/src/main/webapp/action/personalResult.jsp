<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.* ,bean.PersonalResult" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>個人過去実績</title>
	</head>
	<body>
	個人過去実績<br>
			<c:if test ="${PersonalResult==null }">
			<jsp:forward page="../action/PersonalResult.action"></jsp:forward>
			</c:if>
	
	
		「${user.userName }」さんの正答率実績<br>
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
			<%
			PersonalResult[] array = (PersonalResult[])request.getAttribute("PersonalResult");
			
			for(int i= array.length-1; i>=0; i--)
			{
			%>
			<tr>
			<td>第<%=array[i].getCountUnit() %>回目</td>
			<td><%=array[i].getTotalQuestionCount() %></td>
			<td><%=array[i].getDifficulty() %></td>
			<td><%=array[i].getCorrectOnTotal() %>％</td>			
			<td><%=array[i].getCorrectOnStrategy()%>％</td>
			<td><%=array[i].getCorrectOnTech() %>％</td>
			<td><%=array[i].getCorrectOnManage() %>％</td>
			</tr>
			<% 
			}
			%>
		</table>
	</body>
	<%@include file="footer_mypage.jsp" %>
</html>