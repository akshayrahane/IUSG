<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/new1.css">
	</head>
	<body>
		<div class="header">
				<a href="home.html"><img src="img/explore_logo.png" alt="eXplore"/></a>
				<h1>Welcome to eXplore</h1>
			</div>
			
		<c:choose>
			<c:when test="${sessionScope.role=='admin'}">
				<jsp:include page="./AdminMenu.jsp" />
			</c:when>
			<c:when test="${sessionScope.role=='user'}">
				<jsp:include page="./UserMenu.jsp" />
			</c:when>
			<c:when test="${sessionScope.role eq null}">
				<jsp:include page="./Menu.jsp" />
			</c:when>
		</c:choose>

	</body>
</html>