package com.rmadss.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class StemmingAction extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(StemmingAction.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path="";
		
		try{
			HttpSession session=request.getSession(true);
			 String stopwordfiledata=(String) session.getAttribute("stopwordfiledata");
			 
			   System.out.println("in Stemming action class "+stopwordfiledata);	
			 
			    
			  StringTokenizer stringTokenizer=new StringTokenizer(stopwordfiledata," ");
			  
			  List<String> stemwordslist=new ArrayList<String>();
     			while(stringTokenizer.hasMoreTokens()){
					stemwordslist.add(stringTokenizer.nextToken());
					}
			    session.setAttribute("stemwordslist", stemwordslist);
				path="./AfterStemming.jsp";
				request.setAttribute("status", " After performinmg stemming");
			
		}catch (Exception e) {
			logger.error(e);
			logger.info(e);
			path="./userhome.jsp";
			request.setAttribute("status", " Plz try again");
		}
		RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
