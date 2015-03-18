package com.rmadss.action;



import java.io.IOException;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


@SuppressWarnings("serial")
public class ParaGraphAction extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(ParaGraphAction.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path="";
		try{
				  HttpSession httpSession=request.getSession();
		 
		          String filedata=(String) httpSession.getAttribute("stopwordfiledata");
		            
		            System.out.println("in paragraphhhhhhhhhhhhhhhhhhhhh>>>>>>>>>>>>"+filedata);
		            
		            StringTokenizer tokenizer=new StringTokenizer(filedata,"."); 
                                              	
		            while(tokenizer.hasMoreTokens()){
		            	
		                    	String s1=tokenizer.nextToken();
		                   	
		            	System.out.println("in string tokens >>>        >>>>>"+s1);
		                        
		 		             Map<String, Integer> unique = new LinkedHashMap<String, Integer>();
		            	       
		 		                for ( String string : s1.split(" ")) {
	            	            if(unique.get(string) == null)
	            	               unique.put(string, 1);
		            	            else
	            	                unique.put(string,unique.get(string)+1);
		            	      
		 		                   System.out.println("in paragraph actooooooooooooooooooooo>>>>>>>>>>>>>>>>"+unique.keySet());
		 		                
		 		                
		 		                }
		            	        String uniqueString = join(unique.keySet(),",");
		            	     // List<Integer> value = new ArrayList<Integer>(unique.values());
		            	        System.out.println("Output = " + uniqueString);
		
		            	       }
		            
		                 path="./ViewParaGraph.jsp";
		                request.setAttribute("status", "After performinmg paragraphs");
		}
		
		catch (Exception e) {
			logger.error(e);
			logger.info(e);
			path="./userhome.jsp";
			request.setAttribute("status", "Plz try again");
		}
		RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	
	
	
	
	public  String join(Collection<String> s, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        Iterator<String> iter = s.iterator();
        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }
 	

}
