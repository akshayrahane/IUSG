<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.net.*,java.io.*"%>
<html>
	<head>
		<title>Search Engine</title>
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

	<body bgcolor="#F5FFFA" onload="focusIn();">
		<jsp:include page="Header.jsp"></jsp:include>
		<form action='./KeywordSubmitAction'>
			
</br>
			<center>
				<div style="font-size: 400%">
					<font color='blue'>s</font>
					<font color='red'>e</font>
					<font color='adf'>a</font>
					<font color='blue'>r</font>
					<font color='green'>c</font>
					<font color='red'>h</font>
				</div>


</br>
				<table border="0">
					<tr>
						<td>
							<input type="text" id="search" name="search"
								 autocomplete="off" size="70" />
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
				</table>
				<div id="close" align="right"
					style="position: absolute; left: 100px; top: 10px;">
					<a href="" onclick="hide_divs(); return false"></a>
				</div>
			</center>
		</form>
	</body>
</html>