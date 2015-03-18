package com.rmadss.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rmadss.delegate.SearchDelegate;
import com.rmadss.util.UtilConstants;

public class AddWordIndexAction extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		boolean flag = false;
		String path = "";
		try {
			flag = new SearchDelegate().insertSearchKeyword(request
					.getParameter("keyword"));
			if (flag) {
				request.setAttribute("status", UtilConstants._ADD_KEYWORD);
				path = UtilConstants._ADD_KEYWORD_PAGE;
			} else {
				request.setAttribute("status", UtilConstants._ADD_NOT_KEYWORD);
				path = UtilConstants._ADD_KEYWORD_PAGE;
			}
		} catch (Exception e) {
			request.setAttribute("status", e.getMessage());
			path = UtilConstants._ADD_KEYWORD_PAGE;
		}
		rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
