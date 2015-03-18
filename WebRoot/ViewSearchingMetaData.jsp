<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rmadss.bean.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
	<head>
		<title>AJAX Search</title>
		<style type="text/css">
.normal {
	background-color: #FFFFFF;
	padding: 2px 6px 2px 6px;
}

.over {
	background-color: #3366CC;
	padding: 2px 6px 2px 6px;
}

div#result {
	display: none;
	border: 1px solid #000000;
}
</style>
		<script language="javascript">
var xmlHttp;
function searchText() {
	var search = document.getElementById("search").value;
	var url = "./AJAXSearchServlet?search=" + search;
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlHttp.open("Get", url, true);
	xmlHttp.onreadystatechange = callback;
	xmlHttp.send(null);
}

function callback() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			var result = document.getElementById('result');
			result.innerHTML = '';
			var respText = xmlHttp.responseText;
			if ((respText.length) != 0) {
				show_div('result');
				show_div('close');
				var str = xmlHttp.responseText.split("\n");
				var items;
				for (i = 0; i < str.length - 1; i++) {
					items = '<div onmouseover="javascript:overText(this);" ';
					items += 'onmouseout="javascript:outText(this);" ';
					items += 'onclick="javascript:setText(this.innerHTML);" ';
					items += 'class="normal">' + str[i] + '</div>';
					result.innerHTML += items;
				}
			} else {
				hide_divs();
			}
		}
	}
}

function focusIn() {
	document.getElementById("search").focus();
}

function overText(div_value) {
	div_value.className = 'over';
}

function outText(div_value) {
	div_value.className = 'normal';
}

function setText(value) {
	document.getElementById('search').value = value;
	hide_divs();
	document.getElementById('result').innerHTML = '';
}

function show_div(div_id) {
	document.getElementById(div_id).style.display = 'block';
}

function hide_divs() {
	document.getElementById('result').style.display = 'none';
	document.getElementById('close').style.display = 'none';
}
</script>
	</head>
	<body bgcolor="#FFFAF0" onload="focusIn();">
		<form action='./KeywordSubmitAction'>

			<center>
				<br>
				<br>
				<div
					style="position: absolute; left: 70px; top: 50px; font-size: 200%">
					<font color='blue'>s</font>
					<font color='red'>e</font>
					<font color='adf'>a</font>
					<font color='blue'>r</font>
					<font color='green'>c</font>
					<font color='red'>h</font>
				</div>
				<table border="0">
					<tr>
						<td>
							<%
								if (request.getAttribute("status") != null) {
							%>
							<input type="text" id="search" name="search"
								 autocomplete="off" size="100"
								value='<%=request.getAttribute("status")%>' />
							<%
								} else {
							%>
							<input type="text" id="search" name="search"
								onkeyup="searchText()" autocomplete="off" size="95" value='' />
							<%
								}
							%>
						</td>
						<td>
							<input type='image' name='submit' src="./images/images1.jpeg"
								height="30" width="100">
						</td>
					</tr>
					<tr>
						<td>
							<div id="result"></div>
						</td>
					</tr>
					<tr>
						<td>
							<div id="close" align="right" style="display: none;">
								<a href="" onclick="hide_divs(); return false"></a>
							</div>
						</td>
					</tr>
				</table>
			</center>
		</form>

		<%
			int pagedisplayrecors = 4;
			String pa = request.getParameter("page");
			Vector<SearchForm> vSearchForms = (Vector<SearchForm>) session
					.getAttribute("vSearchForms");
			int pageNo = 0;
			if (pa == null)
				pageNo = 0;
			else
				pageNo = Integer.parseInt(pa);
			int totalrows = vSearchForms.size();
			int totalpages = totalrows / pagedisplayrecors;
			if (totalrows % pagedisplayrecors != 0)
				totalpages = totalpages + 1;
			int startrow = (pageNo * pagedisplayrecors) + 1;
			int endrow = (pageNo * pagedisplayrecors) + pagedisplayrecors;
			if (endrow > totalrows)
				endrow = totalrows;
		%>
		<center>
			<table height="100" width="700">
				<%
					try {
						if (vSearchForms.size() != 0) {
							SearchForm searchForm;
							for (int intIndex = startrow - 1; intIndex < endrow; intIndex++) {
								searchForm = (SearchForm) vSearchForms.get(intIndex);
				%>
				<tr>
					<td align='left'>
						<font size="6" color="blue" ><a href="<%=searchForm.getUrls()%>"><%=searchForm.getTitle()%>
						</a> </font>
					</td>
				</tr>
				<tr>
					<td align='left'>
						<%=searchForm.getDatadescription()%>
						</b>
					</td>
				</tr>
				<tr>
					<td align='left'>
						<b><a href="<%=searchForm.getUrls()%>"><%=searchForm.getUrls()%>
						</a> </b>
					</td>
				</tr>
				<%
					}
				%>
				<%
					}
				%>
			</table>
			<table align="center">
				<tr>
					<td width="100%">
						(<%=startrow%>-<%
						if (endrow > totalrows)
								endrow = totalrows;
					%><%=endrow%>
						of
						<%=totalrows%>
						Total)
						<%
						if (pageNo > 0) {
					%></br>
						</br>
						</br>
						<a href="./ViewSearchingData.jsp">Previous</a>&nbsp&nbsp
						<a href="./ViewSearchingData.jsp?page=<%=pageNo - 1%>"
							style="text-decoration: none"><< &nbsp&nbsp</a>
						<%
							}
						%>
						<%
							// int count=0;
								for (int i = 0; i < totalpages; i++) {
									if ((i == pageNo - 2) || (i == pageNo - 1) || (i == pageNo)
											|| (i == pageNo + 1) || (i == pageNo + 2)) {
										if (!(pageNo == i)) {
						%><a href="./ViewSearchingData.jsp?page=<%=i%>"
							style="text-decoration: none"> <%
 	}
 %> <%=(i + 1)%></a> &nbsp&nbsp
						<%
 	//count++;
 			}
 			//if(count==5)break;
 		}
 %>
						<%
							if (pageNo < totalpages - 1) {
						%><a href="./ViewSearchingData.jsp?page=<%=pageNo + 1%>">>>
							&nbsp&nbsp</a><a
							href="./ViewSearchingData.jsp?page=<%=totalpages - 1%>">Next</a>
						<%
							}
						%>
					</td>
				</tr>
				<%
					} catch (Exception e) {
					}
				%>
			</table>
		</center>
		
	</body>

</html>