<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Vector"%>
<%@page import="com.rmadss.bean.SearchForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="Header.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#F5FFFA">
<br/><br/><center>
<%! int cnt=0; 
int cnt1=0;
SearchForm searchForm=new SearchForm();
%>
<%
Vector user=new Vector();
Vector prod=new Vector();
Vector vvv=new Vector();
Vector uniqueq=new Vector();
Vector uniprod=new Vector();
System.out.println("-------------"+(Vector)session.getAttribute("sr"));
vvv=(Vector)session.getAttribute("sr");

if(vvv!=null){
for(int i=0;i<vvv.size();i++){
String s=(String)vvv.get(i);
StringTokenizer st=new StringTokenizer(s,":");
user.add(st.nextToken());
}

for(int i=0;i<user.size()-1;i++){
for(int j=i+1;j<user.size();j++){
if((user.get(i)).equals(user.get(j))){
user.remove(j);
--j;
}
}
}

////////////////////////////////////////////////////////////
System.out.println(user.size());
cnt++;
Vector<SearchForm> vc=(Vector<SearchForm>)session.getAttribute("vc");
if(vc!=null){
for(int i=0;i<user.size();i++){
uniprod=new Vector();

   %>


 <table border="2"> 
   
  <tr bgcolor="#FFCCCC">
  <td>Title</td>
  <td>URL</td>
  <td>Description</td>
  <td>Click Sequences</td>
 
  </tr>
  
   
     <tr>
    <% 
   for(int ii=0;ii<vc.size();ii++){
   {
   searchForm = (SearchForm) vc.get(ii);
   
   
   
   
  if((user.get(i)).equals(searchForm.getUser()))
  {
 
   %>
   
                       <td align='left'>
						<font color="blue" size="3"><%=searchForm.getTitle()%>
						 </font>
					</td>
                
					<td align='left'>
						<font color="blue" size="3"><%=searchForm.getUrls()%>
						 </font>
					</td>
				   <td align='center'>
						<font color="black" size="3"> <%=searchForm.getDatadescription()%></font>
					</td>
					<td align='center'>
						<font color="red" size="3"> <%=searchForm.getRecordscount()%></font>
					</td>
				
                    
                   <%
                   
                   
                   
                  
  } 
  
  
  
  }
  %>
  
  </tr>
  <% 
  
  
 
  
}
out.println("<br>");
out.println("The UserName:    <b>"+user.get(i)+"</b> searched URLS are:::<br><br>");
}
}
}
    %>


 </table>
 
   <table><tr><br><td>
  
<form action="./RefreshSessionAction">
  <input type="submit" value="Refresh Session value"/> <br/>
       </form>

  </td>
    </tr></table>
</center>
</body>

</html>