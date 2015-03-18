<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="com.rmadss.daoimpl.SearchDAOImpl"%>
<%@page import="com.rmadss.bean.SearchForm"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<jsp:include page="Header.jsp"></jsp:include>
  </head>
  <%!
  static Vector v=new Vector();
 static Vector vs=new Vector();
  SearchForm searchForm=new SearchForm();
  int cnt=0;
   
   %>
  <%
  
  String user=(String)session.getAttribute("user");
  int id=Integer.parseInt(request.getParameter("id"));
  System.out.println("tttttttttt"+id);
  String url=request.getParameter("url");
  System.out.println("tttttttttt"+url);
  v.add(user+":"+url);
  vs.add(user+":"+url);
  System.out.println("abcd:::::::::"+v);
  session.setAttribute("sr",v);
    session.setAttribute("vs",vs);
  SearchDAOImpl pdao=new SearchDAOImpl();
  
  Vector<SearchForm> vd=(Vector<SearchForm>)pdao.getSearchResults(id);


  vs= (Vector)session.getAttribute("vs");
  int count=vs.size();
  
  
  
  searchForm.setRecordscount(count);
  searchForm.setDataindexid(id);
  searchForm.setUser(user);

  
 
  boolean flag=pdao.updateurls(searchForm);
  session.setAttribute("vs",vs);
   %>
    <body bgcolor="#F5FFFA">
   <br>
   <table> 
   
  
   
   
   <% 
   for(int i=0;i<vd.size();i++){
   
   searchForm = (SearchForm) vd.get(i);
 
   %>
   
 <tr>
					<td align='left'>
						<font color="#6495ED" size="5"><a
						href="./searchresults.jsp?url=<%=searchForm.getUrls()%>&id=<%=searchForm.getDataindexid()%>"><%=searchForm.getTitle()%>
						</a> </font>
					</td>
				</tr>
				
				<tr>
					<td align='left'>
						<font color="#000000" size="3"> <%=searchForm.getTitledata()%></font>
					</td>
				</tr>
   
   <%
   
}
    %>
   
   
   
   
   
   
   </table>
 
  <br/><br/>
  
  <%
  cnt++;
  session.setAttribute("cnt1",cnt); %>
  
  
    
    <form action="./ViewSearchingData1.jsp">
  <input type="submit" value="Search Again"/> <br/>
       </form>
  </body>
 
</html>
