<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<script language="JavaScript" src="scripts/gen_validatorv31.js"
			type="text/javascript">
</script>
		<script language="JavaScript" type="text/javascript"
			src="scripts/ts_picker.js">
</script>
		<script language="JavaScript1.1" src="scripts/pass.js">
</script>
		<script type="text/javascript" src="scripts/image.js">
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
		<form name='cases' method='post'
			action='./AddWordIndexDocumentsAction'>
			<center> <b>ADD MAIN WORD INDEX DATA</b>
			<table>
				<tr bgcolor='white'>
					<td align='right'>
						<b> KeyWord :</b>
					</td>
					<td align='left'>
						<select name='key'>
							<c:forEach var="word" items="${vSearchForms}">
								<option value='${word.key}'>
									${word.wordindex}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<b>Enter Description :</b>
					</td>
					<td align='left'>
						<textarea type='text' name='indexdescription' value=''></textarea>
					</td>
				</tr>
				<tr>
					<td align='right'>
						<b>Title:</b>
					</td>
					<td align='left'>
						<textarea type='text' name='datatitle' value=''></textarea>
					</td>
				</tr>
				<tr>
					<td align='right'>
						<b>SITE URL:</b>
					</td>
					<td align='left'>
						<textarea type='text' name='urls' value=''></textarea>
					</td>
				</tr>
				<tr>
					<td align='right'>
						<input type='submit' name='submit' value='submit' />
					</td>
					<td align='left'>
						<input type='reset' name='reset' value='reset' />
					</td>
				</tr>
			</table>
			</center>
		</form>
	</body>
</html>
