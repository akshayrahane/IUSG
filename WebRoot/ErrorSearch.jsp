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
				<font color='red'>h </font> &nbsp&nbsp&nbsp
			</div>
			<form action='./KeywordSubmitAction'>
				<table border="0">
					<tr>
						<td>

							<input type="text" id="search" name="search"
								onkeyup="searchText()" autocomplete="off" size="70"
								value='Records not found' />
							<br>


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
			<h3>
				Your search - <font color='red'><%=request.getParameter("search") %></font> - did not match any documents.

				Suggestions: <br> *  Make sure all words are spelled correctly.<br> * Try
				different keywords.<br> * Try more general keywords.
			</h3>
		</center>
		<form action='./LoginForm.jsp'>
			<div style="position: absolute; left: 890px; top: 20px;">
				<input type='image' name='submit' src="./images/signin.bmp">
			</div>
		</form>
	</body>
</html>