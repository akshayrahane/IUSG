<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<%! String filedata="";
    String s1="";
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <jsp:include page="Header.jsp"></jsp:include>
  
  <body bgcolor="#F5FFFA">
   <center>
     <%
       filedata=(String)session.getAttribute("stopwordfiledata");
       System.out.println("data in jspppppppp>>>>>>>>>>>>>>>>>> is "+filedata);
     %>
     </br></br>       
   <table align="center">
   
   <tr bgcolor="#B0E0E6"><td>View Data After Performing Paragraphs</td> </tr>
   
    <tr>
  <td><font size="4" color="#C71585">Paragraphs</font> </td></tr>
  <tr>
  <td>
  
  <textarea name='paragraphdata' id='test' rows="100" cols="85">
        <%
	     
  	      Map<String,Integer> example = new HashMap<String,Integer>();
	      StringTokenizer stringTokenizer=new StringTokenizer(filedata," ");
           int i=0;	    
             while(stringTokenizer.hasMoreElements())
             {  // Adding some values to the HashMap
                 i=i+1;
                 example.put( stringTokenizer.nextToken(),i);
                // Find out how many key/value pairs the HashMap contains
             }      
	    System.out.println("The HashMap contains "+example.size()+" pairs");
	      for (String key : example.keySet())
	       {
              // Get the String value that goes with the key
             int value = example.get( key );
             // Print the key and value
            System.out.println(key+" = "+value);
          }
    %>   <%  StringTokenizer tokenizer=new StringTokenizer(filedata,"."); 
           
           
           
            %>
	   <%
	   
	      int number=0;
	       
          while(tokenizer.hasMoreTokens())
	      {
	         number=number+1;
	         s1=tokenizer.nextToken();
		     System.out.println("in string tokens >>>>>>>>"+s1);
		  %>
		  <%=s1%>
	   <%  
	          Map<String,Integer> map=null;
	          StringTokenizer stringTokenizer2=new StringTokenizer(s1," ");
	          int variable=0;
	         
	         map=new HashMap<String,Integer>();
 	            
	         
	          while(stringTokenizer2.hasMoreTokens())
	           {
                   
                    String token=stringTokenizer2.nextToken();
 	        


 	                         variable=variable+1;
 	                         map.put(token,variable);
 	           }  
 	           
 	           System.out.println("in paragraph action "+map.size());
 	          for (String paragraphpattern : map.keySet())
	           {
                 for(String key :example.keySet())
	               {
	                int value=example.get(key); 
	              
	               if(paragraphpattern.toString().equalsIgnoreCase(key.toString()))
	                 {
	                          System.out.println("paragraph "+number+" contains the term value  "+key+"   "+value);
	                  
	                  
	                  %>
	                  
	                  paraGraph <%=number%> contains the  term value "<%=key%>"  <%=value%>
	                  <%                 
	                  }
	               }
	          }
	     }   
	    %>
             </textarea>  
    </td>
    </tr>
    
    
   
    
    </table>
   
   
   <br/>
    <jsp:include page="./Footer.jsp"></jsp:include>
    </center>
  </body>
</html>
