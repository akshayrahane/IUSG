
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	if (session.getAttribute("user") == null) {

		RequestDispatcher rd = request
				.getRequestDispatcher("./LoginForm.jsp");
		rd.forward(request, response);
	}
%>

<html>
	<head>


		<script language="JavaScript" src="scripts/gen_validatorv31.js"
			type="text/javascript">
</script>
		<script type="text/javascript" src="scripts/general.js">
</script>
		<script type="text/javascript" src="scripts/adi.js">
</script>
		<script type="text/javascript" src="scripts/form_validation.js">
</script>

		<script language="JavaScript" src="images/javascripts.js">
</script>
		<script language="JavaScript" src="images/pop-closeup.js">
</script>




	</head>

	<body bgcolor="#F5FFFA">

		<jsp:include page="Header.jsp"></jsp:include>

		<center>
			<font color="red"> <b> <c:if
						test="${requestScope.status!='null'}">

						<c:out value="${requestScope.status}"></c:out>
					</c:if> </b> </font>
		</center>
		<center>
			<b><h4>
					Add MAIN DATA
				</h4> </b>
		</center>



		<form method='post'
			action="<%=request.getContextPath() + "/AddMainDataAction"%>"
			name="data">


			<table cellspacing=2 align="center" bgcolor="" width="325"
				height="60">

				<tr>
					<td align='right'>
						<b>Keyword :</b>
					</td>
					<td>

						<select name="key" id="select"
							onchange="javascript:if(document.data.key.value=='')
			{alert('Select Any Data Keyword');} 		 else
			{ location.href='./ViewInterfaceKeywordDataAction?key='+document.data.key.value;}">
							<c:choose>
								<c:when test="${requestScope.wordindex ne null}">
								
									<option value="${requestScope.key}">
										${requestScope.wordindex}
									</option>
								</c:when>
								<c:otherwise>
									<option value="">
										--SELECT--
									</option>
									<c:if test="${not empty vSearchForms}">
										<c:forEach var="word" items="${vSearchForms}">

											<option value='${word.key},${word.wordindex}'>
												${word.wordindex}

											</option>

										</c:forEach>
									</c:if>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<td align='right'>
						<b>InterfaceKeyWord :</b>
					</td>
					<td>
						<select name="keyref" id="select">
							<c:forEach var="interface" items="${vSearchFormsInterface}">

								<option value="${interface.key}">
									${interface.wordindex}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align='right'>
						<b>Header :</b>
					</td>
					<td>
						<input type='text' name="titlehead" />
					</td>
				</tr>
				<tr>
					<td align='right'>
						<b>Title Description :</b>
					</td>
					<td>
						<textarea name="datadescription"></textarea>
					</td>
				</tr>
				<tr>
					<td align='right'>
						<b>URL :</b>
					</td>
					<td>
						<input type='text' name="urls" />
					</td>
				</tr>
				<tr>
					<td align='right'>
						<input type=submit value=Add>
					</td>
					<td>
						<input type=submit value=Clear>
					</td>
				</tr>
			</table>
		</form>

		<script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
var frmvalidator = new Validator("data");
frmvalidator.addValidation("titlehead", "req", "Please enter DATA HEADER");
frmvalidator.addValidation("datadescription", "req",
		"Please enter DATA DESCRIPTION ");
frmvalidator.addValidation("urls", "req", "Please enter WEB SITE URL");
</script>
		<br />
		<jsp:include page="Footer.jsp"></jsp:include>
	</body>
</html>
