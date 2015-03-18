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

	<body>
		<img src="./images/header.JPG" align='top' height='170' width='890' />
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