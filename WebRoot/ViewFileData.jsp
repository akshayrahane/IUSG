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
      str2=(String)session.getAttribute("str2");
   
   
     System.out.println("data in jsppppppppppppppppppppppppppppppppppppp is "+str1);
     System.out.println("data in jsppppppppppppppppppppppppppppppppppppp is "+str2);
   
   
   
    %>
      <form action="./RemoveStopWordAction">
      
   <table align="center">
   
   <tr bgcolor="#B0E0E6">View Data Here </tr><br/>
   
    <tr>
  <td><font size="4" color="#C71585">Data</font> </td></tr>
  <tr>
  <td>
  
        <textarea name='filedata' id='test' rows="30" cols="50"><%=str1 %> </textarea>
 </td>
  
   <td>
  
        <textarea name='filedata' id='test' rows="30" cols="50"><%=str2 %> </textarea>
 </td>
  </tr>
    
      
   
   
   
   </table>
   
   </form>
   <br/>
    <jsp:include page="./Footer.jsp"></jsp:include>
    </center>
  </body>
</html>
