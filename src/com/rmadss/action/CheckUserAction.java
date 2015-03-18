package com.rmadss.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rmadss.delegate.SearchDelegate;
import com.rmadss.delegate.UserDelegate;
import com.rmadss.util.UtilConstants;

public class CheckUserAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("userName");
		String searchString = "";
		try {
			String user = new UserDelegate().checkUser(username);
			if ((user.trim()).equals("")) {
				searchString = "<font color=red>Name already taken</font>";
				response.getWriter().write(searchString);
			} else {
				searchString = "<font color=red>Available</font>";
				response.getWriter().write(searchString);
			}
		} catch (Exception e) {
			searchString = "<font color=red>Available</font>";
			response.getWriter().write(searchString);
		}
	}
}
