package com.rmadss.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rmadss.delegate.SearchDelegate;
import com.rmadss.util.UtilConstants;

public class KeywordSearchAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String search = request.getParameter("search");
			System.out.println(search);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			if (!((search.trim()).equals(""))) {
				String searchString = new SearchDelegate().searchData(search);
				System.out.println(searchString);
				response.getWriter().write(searchString);
			} else {
				System.out.println("Length of string" + ("".length()));
				response.getWriter().write("");
			}

		} catch (Exception e) {
			System.out.println("Length of string" + ("".length()));
			response.getWriter().write("");
		}
	}
}
