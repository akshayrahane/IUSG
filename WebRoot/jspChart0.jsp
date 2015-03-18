<html>


	<BODY BGCOLOR="white">
		<jsp:include page="Header.jsp"></jsp:include>
		<P>
			<center>
				<TABLE WIDTH="95%">
					<TR>
						<TH COLSPAN=2 BGCOLOR=#D0F0FF>
							Graph Chart
						</TH>
					</TR>
					<TR>
						<TD class="b1" align="center" colspan="2">
							<img src="jspChart.jsp?<%=request.getQueryString()%>"
								alt="generation image" width="400" height="350" border="1">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<FORM METHOD="GET" ACTION="./jspChart0.jsp">
								<input type="hidden" name="b2" value="off">
								<table width="100%">
									<tr>
										<td>
											Presentation :
											<SELECT NAME="pres">
												<OPTION VALUE="0">
													Bar
													<OPTION VALUE="3">
														SumBar
														<OPTION VALUE="1">
															Line
															<OPTION VALUE="2">
																Pie
																<OPTION VALUE="5">
																	Area
																	<OPTION VALUE="6">
																		SumArea
																		<OPTION VALUE="7">
																			Mode Point
																			<OPTION VALUE="8">
																				Mode Simple Point
											</SELECT>
										</td>
										<td>
											Legend
											<INPUT TYPE=CHECKBOX NAME="b1" checked>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<INPUT TYPE="submit" Name="Submit" VALUE="Go...">
										</td>
									</tr>
								</table>
							</FORM>

						</td>
					</tr>
				</table>
			</center>
	</body>
</html>
