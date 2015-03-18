<%@ page import="java.io.*,java.sql.*"%>
<%@ page import="com.rmadss.daoimpl.*"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%
	try {
		String user = new UserDaoImpl().checkUser(request
				.getParameter("userName"));

		if (!user.toString().equals("")) {
			out.println("<font color=red>");
			out.println("Name already taken");
			out.println("</font>");

		} else {

			out.println("<font color=green>");
			out.println("Available");
			out.println("</font>");

		}
	} catch (Exception e) {
		out.println("<font color=green>");
		out.println("Available");
		out.println("</font>");
	}
%>