package com.rmadss.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rmadss.bean.SearchForm;
import com.rmadss.daoimpl.SearchDAOImpl;



public class SearchResultAction1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		    HttpSession session = request.getSession(); 
		   
			String target ="";
		    
		   
		    
		    
		    try{
				
				
	             
		    	  SearchDAOImpl pdao=new SearchDAOImpl();
		    	  
		    	 Vector<String> vv=(Vector<String>)session.getAttribute("vvuser");
		    	 
		    	 
		    	  Vector<SearchForm> vc=(Vector<SearchForm>)pdao.getSearchURLs();   	 
		    	 
				System.out.println("in LoginAction Role is.........."+vc);

				if (!vc.isEmpty())
				{
					request.setAttribute("vc",vc);
					
					request.setAttribute("status","this is Search Information");
					
					target = "./SearchProposed.jsp";
					
					
					session.setAttribute("vc",vc);
					
					
				}
				
				else
				{
					request.setAttribute("status", "Search Information is not there");
					target = "./SearchProposed.jsp";
				}
			}catch (Exception e) {
			
				e.printStackTrace();
				
			}
			
				RequestDispatcher rd = request.getRequestDispatcher(target);
				rd.forward(request, response);
					
		  }

	}
