<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Vector"%>
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
for(int i=0;i<user.size();i++){
uniprod=new Vector();
out.println("The UserName:    <b>"+user.get(i)+"</b> searched URLS are:::<br><br>");
for(int j=0;j<vvv.size();j++){
String s=(String)vvv.get(j);
s=s.trim();
StringTokenizer st=new StringTokenizer(s,":");
String u=st.nextToken();
u=u.trim();
String product=st.nextToken();
if(((String)user.get(i)).equals(u))
uniprod.add(product);
}


%>

<table border="2" width="250" height="97">  <tr bgcolor="cyan"><td align='center'>URLs</td></tr>
<% 

for(int x=0;x<uniprod.size();x++){

%>
<tr><td align='left'>
<% 


out.println(uniprod.get(x)+"<br>");
uniqueq.add(uniprod.get(x));
%>
</td></tr>
<%
}
 %>
 </table>
<%
out.println("<br>");

}
}
//out.println("No of Users:"+user.size());
session.setAttribute("user",user.size());
out.println("<br>");
//out.println("No of Test Queries:"+session.getAttribute("cnt1"));
//out.println("No of UniqueQuires:"+uniprod);
System.out.println("11111=="+uniqueq.size());
for(int m=0;m<uniqueq.size()-1;m++){

for(int n=m+1;n<uniqueq.size();n++){

if(((String)uniqueq.get(m)).equals((String)uniqueq.get(n))){

uniqueq.remove(n);
--n;
}
}
}
out.println("<br>");
//out.println("No Of Uniquequires:"+uniqueq.size());
session.setAttribute("uniqueq",uniqueq.size());
session.setAttribute("unique==",uniqueq);
System.out.println("22222"+uniqueq.size());
%>
</center>
</body>

</html>