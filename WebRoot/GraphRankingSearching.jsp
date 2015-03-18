<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'GraphRankingSearching.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<jsp:include page="Header.jsp"></jsp:include>
		<form action='./LoginForm.jsp'>
			<div style="position: absolute; left: 530px; top: 140px;">
				<%if(session.getAttribute("role")==null){ %><input type='image'
					name='submit' src="./images/signin.bmp">
				<% }%>
			</div>
		</form>
		<form action='./KeywordSubmitAction'>
			

			<center>
				<div style="font-size: 700%">
					<font color='blue'>s</font>
					<font color='red'>e</font>
					<font color='adf'>a</font>
					<font color='blue'>r</font>
					<font color='green'>c</font>
					<font color='red'>h </font>
				</div>



				<table border="0">
					<tr>
						<td>
							<input type="text" id="search" name="search"
								onkeyup="searchText()" autocomplete="off" size="70" />
						</td>
						<td>
							<input type='image' name='submit' src="./images/images1.jpeg"
								height="30" width="100">
						</td>
					</tr>
					<tr>
						<td>
							<div id="result"></div>
						</td>
					</tr>
				</table>
				<div id="close" align="right"
					style="position: absolute; left: 100px; top: 10px;">
					<a href="" onclick="hide_divs(); return false"></a>
				</div>
			</center>
		</form>
	</body>
</html>
