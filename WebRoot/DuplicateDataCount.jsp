<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>






<% String str1="";
String str2="";
 %>



<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
  </head>
  
  <jsp:include page="Header.jsp"></jsp:include>
  
  <body bgcolor="#F5FFFA">
   <center>
     <%
   
      str1=(String)session.getAttribute("str1");
      str2=(String)session.getAttribute("stopwordfiledata");
   
   Vector v1=new Vector();
   Vector v2=new Vector();
   Vector v3=new Vector();
   Vector v4=new Vector();
   
   
   Vector v5=new Vector();
   Vector v6=new Vector();
   Vector v7=new Vector();
   Vector v8=new Vector();
    
   
       
   
    %>
      <form action="./RemoveStopWordAction">
      
   <table align="center">
   
   <tr bgcolor="#B0E0E6">View Data Here </tr><br/>
   
    <tr>
  <td><font size="4" color="#C71585">Data</font> </td></tr>
  <tr>
  <td>
  
        <textarea name='filedata' id='test' rows="20" cols="30"> 
        
        <%
        
        List<String> list = Arrays.asList(str1.split(" "));
 
        Set<String> uniqueWords = new HashSet<String>(list);
        for (String word : uniqueWords) {
        
        
        
        %>
       
        <%=word + ": " + Collections.frequency(list, word) %> 
        
        <% 
        
        
        if(Collections.frequency(list, word)>1)
        {
        v1.add(word + ": " + Collections.frequency(list, word));
        v3.add(word);
        }
        
        
        }%>
        
        
        
        
        
        </textarea>
 </td>
  
   <td>
  
        <textarea name='filedata' id='test' rows="20" cols="30">
        
       <%
        
        List<String> list1 = Arrays.asList(str2.split(" "));
 
        Set<String> uniqueWords1 = new HashSet<String>(list1);
        for (String word1 : uniqueWords1) {
       %>
       
        <%=word1 + ": " + Collections.frequency(list1, word1) %> 
        
        <% 
        if(Collections.frequency(list1, word1)==2)
        {
        v2.add(word1 + ": " + Collections.frequency(list1, word1));
        v4.add(word1);
        }
        
        if(Collections.frequency(list1, word1)==3)
        {
        v2.add(word1 + ": " + Collections.frequency(list1, word1));
        v5.add(word1);
        }
        
        if(Collections.frequency(list1, word1)>3)
        {
        v2.add(word1 + ": " + Collections.frequency(list1, word1));
        v6.add(word1);
        }
        
        }%>
        
        
        
        </textarea>
 </td>
  </tr>
  
  
  
  
  
  
  
  
   <tr>
  <td><font size="4" color="#C71585">Dulicate Data</font> </td></tr>
  <tr>
  <td>
  
        <textarea name='filedata' id='test' rows="10" cols="30"> 
        
        <%
        
       
       
        Enumeration vEnum = v1.elements(); 
        
        while(vEnum.hasMoreElements()) 
        {
        %>
       
        <%= vEnum.nextElement() + " "%> 
        
        <% 
        
        
       }
        
        
        %>
        
        
        
        
        
        </textarea>
 </td>
  
   <td>
  
        <textarea name='filedata' id='test' rows="10" cols="30">
        
       <%
        
       
       
        Enumeration vEnum1 = v2.elements(); 
        
        while(vEnum1.hasMoreElements()) 
        {
        %>
       
        <%= vEnum1.nextElement() + " "%> 
        
        <% 
        
        
       }
        
        
        %>
        
        
        
        </textarea>
 </td>
  </tr>
   </table>
<table border="2">   
   <tr>
  <td><font size="2" color="#C71585"> Repeated Word </font> </td></tr>
  <tr>
  <td>
  
        
        
        <%
        
       
       
        Enumeration vEnum2 = v3.elements(); 
        
        while(vEnum2.hasMoreElements()) 
        {
        %>
       
        <%= vEnum2.nextElement()+","%> 
        
        <% 
        
        
       }
        
        
        %>
        
        
        
        
        
       
 </td>
  
   <td>
  
       
        
       <%
        
       
       
        Enumeration vEnum3 = v6.elements(); 
        
        while(vEnum3.hasMoreElements()) 
        {
        %>
       
        <%= vEnum3.nextElement()+","%> 
        
        <% 
        
        
       }
        
        
        %>
        
        
        
        
 </td>
 
 
  <td>
  
       
        
       <%
        
       
       
        Enumeration vEnum4 = v5.elements(); 
        
        while(vEnum4.hasMoreElements()) 
        {
        %>
       
        <%= vEnum4.nextElement()+","%> 
        
        <% 
        
        
       }
        
        
        %>
        
        
        
        
 </td>
  <td>
  
       
        
       <%
        
       
       
        Enumeration vEnum5 = v4.elements(); 
        
        while(vEnum5.hasMoreElements()) 
        {
        %>
       
        <%= vEnum5.nextElement()+","%> 
        
        <% 
        
        
       }
        
        
        %>
        
        
        
        
 </td>
  </tr>
     
  
  
  
  
  
  
  
  
  
  
  
  
    
      <% 
      
      
       System.out.println(v2.size());
       System.out.println(v4.size());
      
      
      %>
   
   
   
   </table>
   
   </form>
   <br/>
    <jsp:include page="./Footer.jsp"></jsp:include>
    </center>
  </body>
</html>
