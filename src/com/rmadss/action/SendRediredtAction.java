package com.rmadss.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendRediredtAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		    HttpSession session = request.getSession(); 
		   
		    String url = request.getParameter("url");
		    System.out.println("tttttttttt" + url);
		    String un = (String) session.getAttribute("user");
			Vector<String> v = (Vector<String>)session.getAttribute("sr");
			int cnt=(Integer)session.getAttribute("cnt1");
			v.add(un + ":" + url);
			System.out.println("abcd:::::::::" + v);
			session.setAttribute("sr", v);
			System.out.println("vector size" + v.size());
			cnt++;
            session.setAttribute("cnt1",cnt);
			response.sendRedirect(request.getParameter("urlredirect"));
	}
}
