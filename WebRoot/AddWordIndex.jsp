<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<title>My JSP 'AddWordIndex.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script language="JavaScript"
			src="<%=request.getContextPath()
							+ "/scripts/gen_validatorv31.js"%>"
			type="text/javascript">
</script>
	</head>

	<body bgcolor="#F5FFFA">
		<jsp:include page="Header.jsp"></jsp:include>
		</br>
		</br>
		</br>
		</br>
		<center>
			<font color="red"> <b> <c:if
						test="${requestScope.status!='null'}">

						<c:out value="${requestScope.status}"></c:out>
					</c:if> </b> </font>
		</center>
		<form action="./AddWordIndexAction" name='keyword'>
			<h3>
				<center>
					<font color='red'>Add Index keyword</font>
				</center>
			</h3>
			<center>
				<table>
					<tr>
						<td>
							<b>Enter Word Index Keyword :</b>
						</td>
						<td>
							<input type='text' name='keyword' value="">
						</td>
					</tr>
					<tr>
						<td align='right'>
							<input type='submit' name='submit' value="Add Keyword">
						</td>
						<td align='left'>
							<input type='reset' name='clear' value="Clear">
						</td>
					</tr>
				</table>
			</center>
		</form>
		<script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
var frmvalidator = new Validator("keyword");
frmvalidator.addValidation("keyword", "req", "Please Enter KeyWord");
</script>
		<br>
	</body>
</html>
