package com.rmadss.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rmadss.util.UtilConstants;
import java.util.*;

public class LogoutAction extends HttpServlet {

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
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		session.setAttribute("role", null);
		Vector vs=null;
        vs=(Vector)session.getAttribute("vs");
       if(vs!=null)
       {
       if(!vs.isEmpty())
       {
       vs.removeAllElements();
       session.setAttribute("vs",vs);
       }
       }
		// session.invalidate();
		RequestDispatcher rd = request
				.getRequestDispatcher(UtilConstants._LOGOUT);
		rd.forward(request, response);
	}

}
