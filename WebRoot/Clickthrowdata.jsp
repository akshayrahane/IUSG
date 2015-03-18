<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Vector"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="Header.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#F5FFFA">
<br/><br/>
<center>
<h3 align="left">The Collected Click Through Data</h3>
<table align="left" style="background-color:#ffffff;color:#000000;padding-bottom:8px;padding-right:5px" width="639" border="1" cellpadding="0" cellspacing="0">
<tr>
<td align="left">
Number of users
</td>
<td>
<%=session.getAttribute("user") %>
</td>
</tr>
<tr>
<td align="left">
Number of test queries
</td>
<td>
<%=session.getAttribute("cnt1") %>
</td>
</tr>
<tr>

<td align="left">
Number of unique queries
</td>
<td>
<%=session.getAttribute("uniqueq") %>
</td>
</tr>
</table>
<%
int cnt=0;
Vector disp=new Vector();
Vector wegiht=new Vector();
Vector user=new Vector();
wegiht=(Vector)session.getAttribute("sr");
if(wegiht!=null){
for(int i=0;i<wegiht.size();i++){
String s=(String)wegiht.get (i);
StringTokenizer st=new StringTokenizer(s,":");
user.add(st.nextToken());
}

for(int i=0;i<user.size()-1;i++){
cnt=1;
for(int j=i+1;j<user.size();j++){

if((user.get(i)).equals(user.get(j))){
user.remove(j);
++cnt;
System.out.println("99999999999999999999"+cnt);
--j;
}
}
disp.add(user.get(i)+":"+cnt);
}
 %>

</table>
<%

for(int i=0;i<disp.size();i++){
String s=(String)disp.get (i);
StringTokenizer st=new StringTokenizer(s,":");
String s1=st.nextToken();

String s2=st.nextToken();
System.out.println(s1+"99999999999999999999"+s2);



//out.println(s1+"::"+Float.parseFloat(s2)/7);
//out.println(s1+":Selected:"+s2+" queries");
} 
}else{
out.println("First Enter as User and then enter as Admin");
}
%>
</center>
</body>

</html>