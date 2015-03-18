package com.rmadss.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rmadss.bean.ProfileTO;
import com.rmadss.delegate.UserDelegate;
import com.rmadss.exception.ConnectionException;
import com.rmadss.exception.LoginException;
import com.rmadss.util.UtilConstants;

public class LoginAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String role = "";
		String target = "";
		String unknownuser = "unknownuser";
		HttpSession session = request.getSession();

		ProfileTO pro = new ProfileTO();
		Vector<ProfileTO> vpro = new Vector<ProfileTO>();
		String username = request.getParameter(UtilConstants._USERNAME);
		pro.setUserName(username);
		String password = request.getParameter(UtilConstants._PASSWORD);
		pro.setPassword(password);
		try {
			try {
				// role = new SecurityUserDelegate().loginCheck(pro);
				vpro = new UserDelegate().loginCheck(pro);
				Iterator it = vpro.listIterator();
				while (it.hasNext()) {
					pro = (ProfileTO) it.next();
				}
			} catch (ConnectionException e) {
				throw new ServletException(
						"Server busy Plz Try after Some time");
			} catch (LoginException e) {
				RequestDispatcher rd = request
						.getRequestDispatcher(UtilConstants._LOGIN_FAILED_PAGE);
				rd.forward(request, response);
			}
			if (pro.getLogintype().equalsIgnoreCase(UtilConstants._ADMIN)) {
				request.setAttribute("status", "Welcome " + username);
				target = UtilConstants._ADMIN_HOME;
				session.setAttribute(UtilConstants._LOGINUSER, username);
				session.setAttribute(UtilConstants._LOGINID, pro.getUserid());
				System.out.println(pro.getUserid());
				session.setAttribute(UtilConstants._ROLE, pro.getLogintype());
				System.out.println("LoginAction :" + pro.getLogintype());

			} else if (pro.getLogintype().equalsIgnoreCase(UtilConstants._USER)) {
				request.setAttribute("status", "Welcome " + username);
				session.setAttribute(UtilConstants._LOGINUSER, username);
				session.setAttribute(UtilConstants._LOGINID, pro.getUserid());
				session.setAttribute(UtilConstants._ROLE, pro.getLogintype());
				target = UtilConstants._ADMIN_HOME;
			} else {
				request.setAttribute("status", UtilConstants._INVALID_USER);
				target = UtilConstants._LOGIN_FAILED_PAGE;
			}
		} catch (Exception e) {
			request.setAttribute("status", UtilConstants._INVALID_USER);
			target = UtilConstants._LOGIN_FAILED_PAGE;
		}

		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);

	}

}
