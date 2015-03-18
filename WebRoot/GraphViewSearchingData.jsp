<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rmadss.bean.*"%>
<%@ page errorPage="ErrorSearch.jsp"%>
<%!String rankingsearch;%>
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
	var url = "./KeywordSearchAction?search=" + search;
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
	<body onload="focusIn();">
		<jsp:include page="Header.jsp"></jsp:include>
		<%
			int pagedisplayrecors = 10;
			String requestpage = request.getParameter("requestpage");
			Vector<SearchForm> vSearchForms = (Vector<SearchForm>) session
					.getAttribute("vSearchForms");
			int pageNo = 0;
			if (requestpage == null)
				pageNo = 0;
			else
				pageNo = Integer.parseInt(requestpage);
			int totalrecords = vSearchForms.size();
			int totalpages = totalrecords / pagedisplayrecors;
			if (totalrecords % pagedisplayrecors != 0)
				totalpages = totalpages + 1;
			int startrow = (pageNo * pagedisplayrecors) + 1;
			int endrow = (pageNo * pagedisplayrecors) + pagedisplayrecors;
			if (endrow > totalrecords)
				endrow = totalrecords;
		%>
		<center>


			<br>
			<br>
			<div
				style="position: absolute; left: 70px; top: 310px; font-size: 200%">
				<font color='blue'>s</font>
				<font color='red'>e</font>
				<font color='adf'>a</font>
				<font color='blue'>r</font>
				<font color='green'>c</font>
				<font color='red'>h </font> &nbsp&nbsp&nbsp
			</div>
			<form action='./GraphKeywordSubmitAction'>
				<table border="0">
					<tr>
						<td>
							<%
								if (session.getAttribute("searchkeyword") != null) {
							%>
							<input type="text" id="search" name="search"
								onkeyup="searchText()" autocomplete="off" size="70"
								value='<%=session.getAttribute("searchkeyword")%>' />
							<br>
							<%
								} else {
							%>
							<input type="text" id="search" name="search"
								onkeyup="searchText()" autocomplete="off" size="70" value=''
								style="position: absolute; left: 890px; top: 20px;" />
							<%
								}
							%>

						</td>
						<td>
							<input type='image' name='submit' src="./images/images1.jpeg"
								height="30" width="100" />
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
							</div>
							<br>
						</td>
					</tr>
				</table>
			</form>
		</center>

		<center>
			<div style="position: absolute; left: 215px; top: 340px;">
				<%
					out.println("About " + vSearchForms.size() + " results ");
				%><b> <%
 	if (session.getAttribute("elapsedtime") != null) {
 %>(<%
 	out.println(session.getAttribute("elapsedtime"));
 %>&nbsp seconds )<%
 	}
 %> </b>
			</div>
			<table height="100" width="600">
				<%
					try {
						try {
							rankingsearch = (String) request
									.getAttribute("rankingsearch");
						} catch (Exception e) {
						}
						if (vSearchForms.size() != 0) {
							SearchForm searchForm;
							for (int intIndex = startrow - 1; intIndex < endrow; intIndex++) {
								searchForm = (SearchForm) vSearchForms.get(intIndex);
								if ("rankingsearch".equalsIgnoreCase(rankingsearch)) {
				%>
				<tr>
					<td align='left'>
						<b><a
							href="./GraphViewSeachingMetaDataRecordsAction?keyref=<%=searchForm.getKeyref()%>&titledata=<%=searchForm.getTitledata()%>"><%=searchForm.getTitledata()%>
						</a> </b>(<%=searchForm.getRecordscount()%>
						records)
					</td>
				</tr>
				<tr>
					<td align='left'>
						<b> <%=searchForm.getDatadescription()%></b>
					</td>
				</tr>

				<%
					} else {
				%><tr>
					<td align='left'>
						<b><a href="<%=searchForm.getUrls()%>"><%=searchForm.getTitle()%>
						</a> </b>
					</td>
				</tr>
				<tr>
					<td align='left'>
						<b><a href="<%=searchForm.getUrls()%>"><%=searchForm.getUrls()%>
						</a> </b>
					</td>
				</tr>
				<tr>
					<td align='left'>
						<b> <%=searchForm.getTitledata()%></b>
					</td>
				</tr>
				<%
					}
							}
				%>
			</table>
			</br>
			</br>
			</br>
			<table align="center">
				<tr>
					<td width="100%">
						(<%=startrow%>-<%
						if (endrow > totalrecords)
									endrow = totalrecords;
					%><%=endrow%>
						of
						<%=totalrecords%>
						Total)
						<%
						if (pageNo > 0) {
					%>
						<a href="./GraphViewSearchingData.jsp"><b>Previous</b> </a>&nbsp&nbsp
						<a href="./GraphViewSearchingData.jsp?requestpage=<%=pageNo - 1%>"
							style="text-decoration: none"><< &nbsp&nbsp</a>
						<%
							}
						%>
						<%
							// int count=0;
									for (int i = 0; i < totalpages; i++) {
										if ((i == pageNo - 2) || (i == pageNo - 1)
												|| (i == pageNo) || (i == pageNo + 1)
												|| (i == pageNo + 2)) {
											if (!(pageNo == i)) {
						%><a href="./GraphViewSearchingData.jsp?requestpage=<%=i%>"
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
						%><a
							href="./GraphViewSearchingData.jsp?requestpage=<%=pageNo + 1%>">>>
							&nbsp&nbsp</a><a
							href="./GraphViewSearchingData.jsp?requestpage=<%=totalpages - 1%>"><b>Next</b>
						</a>
						<%
							}
						%>
					</td>
				</tr>
				<%
					}

						else {
				%>
				<tr>
					<td height="24" colspan="6">
						<div align="center" class="style3">
							<strong><font color="red">No Records Found</font> </strong>
						</div>
					</td>
				</tr>
				<%
					}
					} catch (Exception e) {
						System.out.println(e);
					}
				%>
			</table>
		</center>
		<form action='./LoginForm.jsp'>
			<div style="position: absolute; left: 890px; top: 20px;">
				<input type='image' name='submit' src="./images/signin.bmp">
			</div>
		</form>
		<form action="./GraphRankingSearchAction">

			<input type="hidden" name="search"
				value='<%=session.getAttribute("searchkeyword")%>' />
			<div style="position: absolute; left: 480px; top: 340px;">
				<input type='image' name='submit' src="./images/ranksearch.jpeg"
					height="40" width="50">
			</div>
		</form>
		<form action="./jspChart0.jsp">

			<input type="hidden" name="searchkeyword"
				value='<%=session.getAttribute("searchkeyword")%>' />
			<div style="position: absolute; left: 780px; top: 310px;">
				<input type='image' name='submit' src="./images/GraphSearch.jpeg"
					height="40" width="50">
			</div>
		</form>
	</body>
</html>